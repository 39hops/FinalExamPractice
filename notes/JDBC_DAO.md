# JDBC

- Java Database API
- Standard Java library that allows Java programs to access DBMSs
- Using these interfaces and classed, programmers can write applications that connect to DBMSs and send queries through SQL
- JDBC is platform independent
- Used to connect to DBMS as long as a driver exists for that DBMS
- You can access any datasources, from relational databases NoSQL to spreadsheets and flat files

## JDBC API

- Comprised of two packages:

  - java.sql.*;

    - Provides the API for accessing and processing data
        stored in a data source using Java
  - javax.sql.*;
    - Provides the API for server-side data source access and processing
    - Extends those capabilities to cater to enterprise-level applications with a focus on connection pooling and distributed transactions

- Establishing a DB connection can be resource-intensive
- Becomes a bottleneck for scaling
- Constantly opening and closing connections becomes more expensive and impacts the performance of the app

```java
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/countries?" + "user=root&amp;password=root");
```

### Initialization

- The application, using a DB driver or an API requests a connection to the db. Often done through a connection string

### Drive Loading

- The appropriate database driver or client library is loaded into memory

### Network Establishment

- A network connection is established between the application and the DB server

### Auth

- The DB server authenticates the credentials provided by the application

### Initialize a Session

- Once authenticated, the server sets up a session for the application

### Handshake

- The application and the database server perform a handshake to negotiate communication parameters and ensure compatibility

### Data Exchange

- The application can now send SQL queries or commands to the database server, and the server responds with the requested data or results

### Connection Pool

- A connection pool is a cache of database connections that are reused, rather than being opened and closed for a new request or transactions
- When a database connection is requests be an app, instead of creating a new connection every time, the connection pool provides a pre-established and maintained set of connections
- The application can borrow a connection from the pool when needed and return it to the pool when the operation is completed
- Connection pooling is based on an object pool design pattern

### Object Pooling

- Design pattern is used when the cost (time, resources, Network, and Memory) of creating new objects is higher
- The application creates an object in advance and place them in Pool or Container
- Whenever our app requires such objects, it acquires them from the pool rather than creating a new one

### Types of Drivers

- Type 1

  - JDBC-ODBC Bridge Driver

- Type 2
  - Native-API Driver
- Type 3
  - Network Protocol Driver (Middleware Driver)
- Type 4:
  - Thin Driver (Fully Java driver) - Widely Used

## JDBC vs ODBC

- JDBC:
  - JDBC is an application programming interface (API) for the Java programming language that defines how a client may access a database
  - It is a Java-based data access technology used for Java database connectivity

- ODBC:
  - Open Database Connectivity (ODBC) interface is a C programming language interface developed by Microsoft to access databases
  - ODBC is a low-level, high-performance interface that is designed for relational data stores

## Type 1 JDBC - ODBC Bridge Driver

- The Bridge allows applications written in Java to use the JDBC API with ODBC drivers
- The driver converts JDBC method calls into ODBC function calls
- The ODBC driver needs to be installed on the client machine

## Type 2 - Native API Driver

- Native API is the same as Type 1, except ODBC is replaced with Vendor Native Libraries (Oracle, MySQL, Postgres)
- Native Libraries are a set of functions written in mostly C/C++
- Native API Driver converts JDBC calls into vendor specific calls

## Type 3 - Network Protocol Driver

- Uses a middleware (application server) that converts JDBC calls into vendor specific calls
- Platform Independent
- Middleware Server may use Type - 1, 2, 4

## Type 4 - Thin Driver

- Driver converts JDBC calls directly into DB specific calls
- Written in Java
- Uses DB specific Native Protocols to talk with DB
- Developed in Java, hence known as Pure Java Driver

## How to Choose

- If you are using one type of DB in your app, use Type - 4
- Multiple DBs use Type - 3
- If Type - 3 or Type - 4 is not available, use Type - 2
- If nothing else is available, use Type - 1
