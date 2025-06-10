package com.pluralsight;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static javax.swing.UIManager.getInt;


public class main {

 public static void main(String[] args) throws SQLException, ClassNotFoundException {


  if (args.length != 2) {
   System.out.println(
           "Application needs two arguments to run: " +
                   "java com.pluralsight.UsingDriverManager <username> <password>");
   System.exit(1);
  }

  // get the username and password from the command line args
  String username = args[0];
  String password = args[1];

// 1. open a connection to the database
// use the database URL to point to the correct database
  Connection connection;
  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",username,password);


// create statement
// the statement is tied to the open connection
  PreparedStatement preparedStatement = connection.prepareStatement("SELECT productId, productName, UnitPrice, unitsInStock FROM products");

// 2. Execute your query
  ResultSet results = preparedStatement.executeQuery();
  System.out.printf("%-4s %-40s %7s %6s%n", "ID", "Product Name", "Price", "Stock");
  System.out.println("------------------------------------------------------------------");

// process the results
  while (results.next()) {

   int productId = results.getInt("productId");
   String productName = results.getString("ProductName");
   double UnitPrice = results.getDouble("UnitPrice");
   int unitsInStock = results.getInt("UnitsInStock");

   System.out.printf("%-4d %-40s %7.2f %6d%n", productId, productName, UnitPrice, unitsInStock);
  }
// 3. Close the connection
  results.close();
  preparedStatement.close();
  connection.close();
 }
}
