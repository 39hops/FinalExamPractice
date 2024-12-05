package dao;

import java.util.ArrayList;
import java.util.List;

class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}

interface Dao<T> {
    T getById(int id);

    void remove(T t);

    void update(T t, T u);
}

class Database {
    public static List<User> users = new ArrayList<>();
}

class UserDaoImpl implements Dao<User> {
    @Override
    public User getById(int id) {
        for (User u : Database.users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void remove(User t) {
        Database.users.remove(t);
    }

    @Override
    public void update(User t, User u) {
        for (int i = 0; i < Database.users.size(); i++) {
            if (Database.users.get(i) == t) {
                Database.users.remove(i);
                Database.users.add(u);
                return;
            }
        }

    }

}