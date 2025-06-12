package com.pluralsight.model;

public class Product {
    int productID;
    String productName;
    int supplierID;
    int categoryID;
    String quantityPerUnit;
    double unitPrice;
    int unitsInStock;
    int unitsOnOrder;
    int reOrderLevel;
    int discontinued;

    public Product(int productID, String productName, int supplierID, int categoryID, String quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reOrderLevel, int discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reOrderLevel = reOrderLevel;
        this.discontinued = discontinued;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public int getReOrderLevel() {
        return reOrderLevel;
    }

    public int getDiscontinued() {
        return discontinued;
    }
}
