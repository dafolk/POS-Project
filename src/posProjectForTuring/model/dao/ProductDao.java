/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posProjectForTuring.model.dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import posProjectForTuring.controller.CurrentDateTime;
import posProjectForTuring.model.Category;
import posProjectForTuring.model.Product;
import posProjectForTuring.model.Supplier;

public class ProductDao {
    Dao dao = Dao.getDao();
    
    public void insertCategory(Category category){
        try {
            PreparedStatement st = dao.conn.prepareStatement("INSERT INTO category(name) VALUES(?);");
            
            st.setString(1, category.getName());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertSupplier(Supplier supplier){
        try {
            PreparedStatement st = dao.conn.prepareStatement("INSERT INTO category(name, address, contact_no) "
                    + "VALUES(?,?,?);");
            
            st.setString(1, supplier.getName());
            st.setString(2, supplier.getAddress());
            st.setString(3, supplier.getContactNo());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertProduct(Product product){
        try {
            PreparedStatement st = dao.conn.prepareStatement("INSERT INTO product(name, unit_price, selling_price, category, "
                    + "supplier, stock, last_updated) "
                    + "VALUES(?,?,?,?,?,?,?);");
            
            st.setString(1, product.getName());
            st.setInt(2, product.getUnitPrice());
            st.setInt(3, product.getSellingPrice());
            st.setInt(4, product.getCategroy());
            st.setInt(5, product.getSupplier());
            st.setInt(6, product.getStock());
            st.setTimestamp(7, CurrentDateTime.get());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<Category>();
        
        try {
            Statement st = dao.conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM category;");
            
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                
                Category category = new Category(id, name);
                categories.add(category);
            }
            result.close();
            st.close();            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    public String getCategoryName(int categoryId){
        String categoryName = null;
        
        try {
            Statement st = dao.conn.createStatement();
            ResultSet result = st.executeQuery("SELECT name FROM category WHERE id='"+categoryId+"';");
            
            while(result.next()){
                categoryName = result.getString("name");
            }
            
            result.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categoryName;
    }
    public List<Supplier> getAllSuppliers(){
        List<Supplier> suppliers = new ArrayList<Supplier>();
        Supplier supplier;
        
        try {
            Statement st = dao.conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM supplier;");
            
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String address = result.getString("address");
                String contactNo = result.getString("contact_no");
                
                supplier = new Supplier(id, name, address, contactNo);
                suppliers.add(supplier);
            }
            st.close();
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }
    public String getSupplierName(int supplierId){
        String supplierName = null;
        
        try {
            Statement st = dao.conn.createStatement();
            ResultSet result = st.executeQuery("SELECT name FROM supplier WHERE id='"+supplierId+"';");
            
            while(result.next()){
                supplierName = result.getString("name");
            }
            
            result.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return supplierName;
    }
    
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<Product>();
        Product product;
        
        try {
            Statement st = dao.conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM product;");
            
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                int unitPrice = result.getInt("unit_price");
                int sellingPrice = result.getInt("selling_price");
                int category = result.getInt("category");
                int supplier = result.getInt("supplier");
                int stock = result.getInt("stock");
                Date lastUpdated = result.getTimestamp("last_updated");
                
                product = new Product(id, name, unitPrice, sellingPrice, category, supplier, stock, lastUpdated);
                products.add(product);
            }
            st.close();
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
}