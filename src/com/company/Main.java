package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "");
            Statement statement = connection.createStatement();
            statement.execute("drop table question1");
            statement.execute("create table question1(NAME VARCHAR(10),AGE INTEGER,SEX VARCHAR(1))");
            Scanner input = new Scanner(System.in);
            System.out.println("enter the no of records you want to enter");
            int no_of_records = input.nextInt();
            for (int i = 0; i < no_of_records; i++) {
                System.out.println("name");
                String name = input.next();
                System.out.println("age");
                int age = input.nextInt();
                System.out.println("sex");
                String sex = input.next();
                PreparedStatement preparedStatement=connection.prepareStatement("insert into question1 values (?,?,?);");
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setString(3,sex);
                preparedStatement.executeUpdate();
            }
              ResultSet resultSet = statement.executeQuery("select * from question1");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1)+"  "+resultSet.getInt(2)+"  "+ resultSet.getString(3));
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
