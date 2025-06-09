package com.pluralsight;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class main {

 public static void main(String[] args) throws SQLException {
  // load the MySQL Driver
//  Class.forName("com.mysql.cj.jdbc.Driver");
// 1. open a connection to the database
// use the database URL to point to the correct database
  Connection connection;
  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","yearup");


// create statement
// the statement is tied to the open connection
  Statement statement = connection.createStatement();

// define your query
  String query = "SELECT * FROM products ";

// 2. Execute your query
  ResultSet results = statement.executeQuery(query);

// process the results
  while (results.next()) {
   String products = results.getString("ProductName");
   System.out.println(products);
  }
// 3. Close the connection
  connection.close();
 }
}
