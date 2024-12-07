package object_pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

class PooledObject {
    private Connection conn;

    PooledObject(Connection conn) {
        this.conn = conn;
    }

    Connection getConn() {
        return conn;
    }

    void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean isClosed() {
        try {
            return conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}

abstract class ObjectPool<T> {
    private long deadTime;
    private Hashtable<T, Long> lock, unlock;

    ObjectPool() {
        deadTime = 50000;
        lock = new Hashtable<>();
        unlock = new Hashtable<>();
    }

    abstract T create();

    abstract boolean validate(T t);

    abstract void dead(T t);

    synchronized T takeOut() {
        long now = System.currentTimeMillis();
        T t;
        if (unlock.size() > 0) {
            Enumeration<T> e = unlock.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlock.get(t)) > deadTime) {
                    unlock.remove(t);
                    dead(t);
                    t = null;
                } else {
                    if (validate(t)) {
                        unlock.remove(t);
                        lock.put(t, now);
                        return t;
                    } else {
                        unlock.remove(t);
                        dead(t);
                        t = null;
                    }
                }
            }
        }
        t = create();
        lock.put(t, now);
        return t;
    }

    synchronized void takeIn(T t) {
        lock.remove(t);
        unlock.put(t, System.currentTimeMillis());
    }
}

class JDBCConnectionPool extends ObjectPool<Connection> {

    String dsn, usr, pwd;

    JDBCConnectionPool(String driver, String dsn, String user, String pwd) {
        super();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.dsn = dsn;
        this.usr = user;
        this.pwd = pwd;
    }

    Connection create() {
        try {
            return DriverManager.getConnection(pwd, usr, dsn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean validate(Connection t) {
        try {
            return (!((Connection) t).isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    void dead(Connection t) {
        try {
            ((Connection) t).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
