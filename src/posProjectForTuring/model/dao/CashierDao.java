package posProjectForTuring.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import posProjectForTuring.model.Cashier;

public class CashierDao {
    Dao dao = Dao.getDao();
    
    public void insertCashier(Cashier cashier) {
        try {
            PreparedStatement st = dao.conn.prepareStatement("INSERT INTO cashier(name, username, password, phone_no, address) "
                                                                + "VALUES (?,?,?,?,?);");
            
            st.setString(1, cashier.getName());
            st.setString(2, cashier.getUsername());
            st.setString(3, cashier.getPassword());
            st.setString(4, cashier.getPhoneNo());
            st.setString(5, cashier.getAddress());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(CashierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCashier(Cashier cashier){
        try {
            PreparedStatement st = dao.conn.prepareStatement("UPDATE cashier SET name = ?, username = ?, phone_no = ?, address = ?"
                                                            + "WHERE id = ?;");
            
            st.setString(1, cashier.getName());
            st.setString(2, cashier.getUsername());
            st.setString(3, cashier.getAddress());
            st.setString(4, cashier.getPhoneNo());
            st.setInt(5, cashier.getId());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCashier(int cashierId){
        try {
            PreparedStatement st = dao.conn.prepareStatement("DELETE FROM cashier WHERE id = ?;");
            st.setInt(1, cashierId);
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cashier> getAllCashiers() {
        List<Cashier> cashiers = new ArrayList<>();
        Cashier cashier;
        
        try{
           Statement st = dao.conn.createStatement();
           ResultSet result = st.executeQuery("SELECT * FROM cashier;");
           
           while(result.next()){
               int id = result.getInt("id");
               String name = result.getString("name");
               String username = result.getString("username");
               String password = result.getString("password");
               String phoneNo = result.getString("phone_no");
               String address = result.getString("address");
               
               cashier = new Cashier(id, name, username, password, phoneNo, address);
               
               cashiers.add(cashier);
           }
           result.close();
           st.close();
            
        } catch (SQLException ex){
            Logger.getLogger(CashierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cashiers;
    }
    
    public Cashier getCashierById(int cashierId){
        Cashier cashier = new Cashier();
        
        try {
            PreparedStatement st = dao.conn.prepareStatement("SELECT * FROM cashier WHERE id=?;");
            st.setInt(1, cashierId);
            
            ResultSet result = st.executeQuery();
            
            while(result.next()){
                cashier.setId(result.getInt("id"));
                cashier.setName(result.getString("name"));
                cashier.setUsername(result.getString("username"));
                cashier.setPassword(result.getString("password"));
                cashier.setPhoneNo(result.getString("phone_no"));
                cashier.setAddress(result.getString("address"));
            }
            st.close();
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cashier;
    }
    
    public Cashier getCashierByUsername(String username){
        Cashier cashier = new Cashier();
        
        try {
            PreparedStatement st = dao.conn.prepareStatement("SELECT * FROM cashier WHERE username=?;");
            st.setString(1, username);
            
            ResultSet result = st.executeQuery();
            
            while(result.next()){                
                cashier.setId(result.getInt("id"));
                cashier.setName(result.getString("name"));
                cashier.setUsername(result.getString("username"));
                cashier.setPassword(result.getString("password"));
                cashier.setPhoneNo(result.getString("phone_no"));
                cashier.setAddress(result.getString("address"));
            }
            st.close();
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cashier;
    }
}
