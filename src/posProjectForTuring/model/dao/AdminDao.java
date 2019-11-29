package posProjectForTuring.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import posProjectForTuring.model.Admin;

public class AdminDao{
    Dao dao = Dao.getDao();
    
    public void insertAdmin(Admin admin){
        try {
            PreparedStatement st = this.dao.conn.prepareStatement("INSERT INTO admin (name, username, password, phone_no, address) "
                                                            + "VALUES (?,?,?,?,?);");
            
            st.setString(1, admin.getName());
            st.setString(2, admin.getUsername());
            st.setString(3, admin.getPassword());
            st.setString(4, admin.getPhoneNo());
            st.setString(5, admin.getAddress());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<Admin>();
        try{
           Statement st = dao.conn.createStatement();
           ResultSet result = st.executeQuery("SELECT * FROM admin;");
           
           while(result.next()){
               int id = result.getInt("id");
               String name = result.getString("name");
               String username = result.getString("username");
               String password = result.getString("password");
               String phoneNo = result.getString("phone_no");
               String address = result.getString("address");
               
               Admin admin = new Admin(id, name, username, password, phoneNo, address);
               admins.add(admin);
           }
           result.close();
           st.close();
            
        } catch (SQLException ex){
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admins;
    }
}
