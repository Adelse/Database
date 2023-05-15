package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static class DB{
        public static ResultSet resultSet() throws SQLException {
            String url = "jdbc:postgresql://localhost:5432/ChessTest";
            String user = "postgres";
            String password = "1";
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM position");
            return resultSet;
        }
    }

    public static void main(String[] args) throws SQLException {
        ResultSet resultSet = DB.resultSet();

        while (resultSet.next()) {
            System.out.print(resultSet.getInt("kingX"));
            System.out.print(resultSet.getInt("kingY"));
            System.out.print(resultSet.getInt("rookX"));
            System.out.print(resultSet.getInt("rookY"));
            System.out.print(resultSet.getInt("bishopX"));
            System.out.print(resultSet.getInt("bishopY"));
            System.out.println(resultSet.getString("result"));
        }
        System.out.println(123);
    }
}
