package com.inventory.model;

import java.sql.Date;

public class Purchase {
    private int id;
    private int productId;
    private int companyId;
    private int quantity;
    private Date date;
    private double unitPrice;
    private double totalPrice;
    private boolean status;

    public Purchase() {
    }

    public Purchase(int id, int productId, int companyId, int quantity, Date date, double unitPrice, double totalPrice) {
        this.id = id;
        this.productId = productId;
        this.companyId = companyId;
        this.quantity = quantity;
        this.date = date;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
