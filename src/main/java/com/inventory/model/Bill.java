package com.inventory.model;

public class Bill {
	private int id;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double taxPercent;
    private double totalAmount;
    private String createdAt;

    public Bill(int id, int productId, int quantity, double unitPrice, double taxPercent, double totalAmount, String createdAt) {
        this.setId(id);
        this.setProductId(productId);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
        this.setTaxPercent(taxPercent);
        this.setTotalAmount(totalAmount);
        this.setCreatedAt(createdAt);
    }

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
