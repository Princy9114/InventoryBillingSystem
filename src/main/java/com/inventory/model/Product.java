package com.inventory.model;

public class Product {
	private int id;
    private String name;
    private int companyId;
    private int categoryId;
    private double unitPrice;
    private double purchasePrice;
    private double gst;
    private String description;
    private int status;

    public Product() {}

    public Product(int id, String name, int companyId, int categoryId, double unitPrice, double purchasePrice, double gst, String description, int status) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.purchasePrice = purchasePrice;
        this.gst = gst;
        this.description = description;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    public double getGst() { return gst; }
    public void setGst(double gst) { this.gst = gst; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
