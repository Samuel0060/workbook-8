package com.pluralsight.Data;
import org.apache.commons.dbcp2.BasicDataSource;

import com.pluralsight.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    BasicDataSource dataSource;

    private ProductDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getAll() {
        String sql = """
                SELECT *
                FROM Products;
                """;

        List<Product> products = new ArrayList<>();


        try (
            Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            try
                (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    int productId = resultSet.getInt("productId");
                    String productName = resultSet.getString("ProductName");
                    int supplierID = resultSet.getInt("SupplierID");
                    int categoryID = resultSet.getInt("categoryID");
                    String quantityPerUnit = resultSet.getString("QuantityPerUnit");
                    double unitPrice = resultSet.getDouble("UnitPrice");
                    int unitsInStock = resultSet.getInt("UnitsInStock");
                    int unitsInOrder = resultSet.getInt("UnitsInOrder");
                    int reOrderLevel = resultSet.getInt("ReOrderLevel");
                    int discontinued = resultSet.getInt("Discontinued");
                    Product product = new Product(
                            productId,
                            productName,
                            supplierID,
                            categoryID,
                            quantityPerUnit,
                            unitPrice,
                            unitsInStock,
                            unitsInOrder,
                            reOrderLevel,
                            discontinued);
                    products.add(product);
//                System.out.printf("%-4d %-40s %7.2f %6d%n", productId, productName, UnitPrice, unitsInStock);
                }
            } catch (SQLException e) {
                System.out.println("Error finding this product");
                e.printStackTrace();
            }
            return products;
        }
    }
}