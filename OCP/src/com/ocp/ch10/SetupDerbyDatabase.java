package com.ocp.ch10;

/**
 * Created by helangovan on 2/17/16.
 */
import java.sql.*;

public class SetupDerbyDatabase {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:derby:zoo;create=true";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {


            //todo MUST READ
//            ResultSet.TYPE_FORWARD_ONLY - can move forward in result sets
//                    TYPE_SCROLL_INSENSITIVE - can move either ways
//                    TYPE_SCROLL_SENSITIVE - can move either ways and also show any latest db updates
            //ResultSet.CONCUR_READ_ONLY - just read only data and cant update it
            //ResultSet.CONCUR_UPDATABLE - not only read but can also update data via resultset
//            conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
//            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//            conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            // stmt.executeUpdate("DROP TABLE animal");
            // stmt.executeUpdate("DROP TABLE species");

            stmt.executeUpdate("CREATE TABLE species ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "num_acres DECIMAL)");

            stmt.executeUpdate("CREATE TABLE animal ("
                    + "id INTEGER PRIMARY KEY, "
                    + "species_id integer REFERENCES species (id), "
                    + "name VARCHAR(255), "
                    + "date_born TIMESTAMP)");

            stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
            stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");

            stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001-05-06 02:15:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002-08-15 09:12:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002-09-09 10:36:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010-06-08 01:24:00')");
            stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005-11-12 03:44:00')");

            ResultSet rs = stmt.executeQuery("select count(*) from animal");
            rs.next();
            System.out.println(rs.getInt(1));
        }
    }
}
