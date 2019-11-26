package posProjectForTuring.model;

import java.util.Date;

public class Product {
    int id;
    String name;
    int unitPrice;
    int sellingPrice;
    int categroy;
    int supplier;
    int stock;
    Date lastUpdated;

    public Product() {
    }

    public Product(int id, String name, int unitPrice, int sellingPrice, int categroy, int supplier, int stock, Date lastUpdated) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.categroy = categroy;
        this.supplier = supplier;
        this.stock = stock;
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getCategroy() {
        return categroy;
    }

    public void setCategroy(int categroy) {
        this.categroy = categroy;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    
}
