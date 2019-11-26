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
                                                                + "VALUES (?,?,?,?,?,?);");
            
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

    public List<Cashier> getAllCashiers() {
        List<Cashier> cashiers = new ArrayList<Cashier>();
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
               
               Cashier cashier = new Cashier(id, name, username, password, phoneNo, address);
               /*
               cashier.setId(id);
               cashier.setName(name);
               cashier.setUsername(username);
               cashier.setPassword(password);
               cashier.setPhoneNo(phoneNo);
               cashier.setAddress(address);
               */
               cashiers.add(cashier);
           }
           result.close();
           st.close();
            
        } catch (SQLException ex){
            Logger.getLogger(CashierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cashiers;
    }
}
