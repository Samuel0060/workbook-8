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
  Scanner scanner = new Scanner(System.in);

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


  try {
   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);


   int choice;
   do {
    displayHomeScreen();
    choice = scanner.nextInt();

    switch (choice) {
     case 1:
      displayAllProducts(connection);
     case 2:
      displayAllCustomers(connection);
     default:
      System.out.println("Please pick 1, 2, or 0");
    }
   } while (choice != 0);
  } catch (SQLException e) {
   e.printStackTrace();
  }
  scanner.close();
 }


 private static void displayAllProducts(Connection connection) {
// create statement
  PreparedStatement preparedStatement = null;
  ResultSet results = null;

  try {
   preparedStatement = connection.prepareStatement("SELECT productId, productName, UnitPrice, unitsInStock FROM products");
   results = preparedStatement.executeQuery();
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

// 2. Execute your query
  } catch (SQLException e) {
   e.printStackTrace();
  } finally {
// 3. Close the connection
   if (results != null) {
    try {
     results.close();
    } catch (SQLException e) {
     e.printStackTrace();
    }
   }
   if (preparedStatement != null) {
    try {
     preparedStatement.close();
    } catch (SQLException e) {
     e.printStackTrace();
    }
   }
   if (connection != null) {
    try {
     connection.close();
    } catch (SQLException e) {
     e.printStackTrace();
    }
   }
  }
 }

 private static void displayAllCustomers(Connection connection) {
  PreparedStatement preparedStatement = null;
  ResultSet results = null;

  try {
   preparedStatement = connection.prepareStatement("SELECT contactName, companyName, city, country, phone FROM customers ORDER BY country");
   results = preparedStatement.executeQuery();
   System.out.printf("%-40s %-40s %-40s %-40s %-15s%n", "Contact Name", "Company Name", "City", "Country", "Phone Number");
   System.out.println("------------------------------------------------------------------");

// process the results
   while (results.next()) {

    String contactName = results.getString("Contact Name");
    String companyName = results.getString("Company Name");
    String city = results.getString("City");
    String country = results.getString("Country");
    String phoneNumber = results.getString("Phone Number");
    System.out.printf("%-40s %-40s %-40s %-40s %-15s%n", contactName, companyName, city, country, phoneNumber);
   }

// 2. Execute your query
  } catch (SQLException e) {
   e.printStackTrace();
  } finally {
// 3. Close the connection
   if (results != null) {
    try {
     results.close();
    } catch (SQLException e) {
     e.printStackTrace();
    }
   }
   if (preparedStatement != null) {
    try {
     preparedStatement.close();
    } catch (SQLException e) {
     e.printStackTrace();
    }
   }
   if (connection != null) {
    try {
     connection.close();
    } catch (SQLException e) {
     e.printStackTrace();
    }
   }
  }
 }


 private static void displayHomeScreen() {
  System.out.println("""
          What do you want to do?
            1) Display all products
            2) Display all customers
            0) Exit
          Select an option:  
          """);
 }
}



