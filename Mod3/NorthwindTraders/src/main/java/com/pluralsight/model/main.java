package com.pluralsight.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
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

  BasicDataSource dataSource = new BasicDataSource();
  dataSource.setUrl("jdbc:mysql://localhost:3306/Northwind");
  dataSource.setUsername(username);
  dataSource.setPassword(password);

  try {
   Connection connection = dataSource.getConnection();
   int choice;
   do {
    displayHomeScreen();
    choice = scanner.nextInt();

    switch (choice) {
     case 1:
      displayAllProducts(connection);
      break;
     case 2:
      displayAllCustomers(connection);
      break;
     case 3:
      displayCategories(connection);
      break;
     case 0:
      System.out.println("Exiting database, goodbye!");
      break;
     default:
      System.out.println("Please pick 1, 2, or 0");
    }
   } while (choice != 0);
  } catch (SQLException e) {
   e.printStackTrace();
  } finally {
   if (connection != null) connection.close();
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
  }
 }

 private static void displayAllCustomers(Connection connection) {
  PreparedStatement preparedStatement = null;
  ResultSet results = null;

  try {
   preparedStatement = connection.prepareStatement("SELECT contactName, companyName, city, country, phone FROM customers ORDER BY country");
   results = preparedStatement.executeQuery();
   System.out.printf("%-25s %-30s %-20s %-20s %-20s%n", "Contact Name", "Company Name", "City", "Country", "Phone Number");
   System.out.println("---------------------------------------------------------------------------------------------");

// process the results
   while (results.next()) {

    String contactName = results.getString("ContactName");
    String companyName = results.getString("CompanyName");
    String city = results.getString("City");
    String country = results.getString("Country");
    String phoneNumber = results.getString("Phone");
    System.out.printf("%-25s %-30s %-20s %-20s %-40s%n", contactName, companyName, city, country, phoneNumber);
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
  }
 }

 private static void displayCategories(Connection connection) {
  PreparedStatement preparedStatement = null;
  ResultSet results = null;

  try{
  preparedStatement = connection.prepareStatement("SELECT * FROM categories ORDER BY categoryID");
  results = preparedStatement.executeQuery();
  System.out.printf("%-12s %-25s %-50s %-20s%n", "CategoryID", "CategoryName", "Description", "Picture");
  System.out.println("-------------------------------------------------------------------------");

  while(results.next()) {
   int categoryID = results.getInt("CategoryID");
   String categoryName = results.getString("CategoryName");
   String description = results.getString("Description");
   byte[] pictureData = results.getBytes("Picture");
   System.out.printf("%-12s %-25s %-50s %-20s%n", categoryID, categoryName, description, pictureData);
  }
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
  }
 }




 private static void displayHomeScreen() {
  System.out.println("""
          What do you want to do?
            1) Display all products
            2) Display all customers
            3) Display all categories
            0) Exit
          Select an option:  
          """);
 }
}



