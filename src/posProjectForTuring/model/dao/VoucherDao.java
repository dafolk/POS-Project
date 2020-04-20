package posProjectForTuring.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import posProjectForTuring.model.Voucher;

/**
 *
 * @author hp
 */
public class VoucherDao {
    Dao dao = Dao.getDao();
    
    public void insertVoucher(Voucher voucher){
        try {
            PreparedStatement st = dao.conn.prepareStatement("INSERT INTO voucher(transaction_id, product_id, quantity, price) "
                                                            + "VALUES(?, ?, ?, ?);");
            
            st.setInt(1, voucher.getTransactionId());
            st.setInt(2, voucher.getProductId());
            st.setInt(3, voucher.getQuantity());
            st.setInt(4, voucher.getPrice());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Voucher> getAllVouchers(){
        List<Voucher> vouchers = new ArrayList<>();
        Voucher voucher;
        
        try{
            Statement st = dao.conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM voucher;");
            
            while(result.next()){
                int id = result.getInt("id");
                int transactionId = result.getInt("transaction_id");
                int productId = result.getInt("product_id");
                int quantity = result.getInt("quantity");
                int price = result.getInt("price");
                
                voucher = new Voucher(id, transactionId, productId, quantity, price);
                vouchers.add(voucher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vouchers;
    }
    
    public List<Voucher> getVouchersByTransactionIds(int startId, int endId){
        List<Voucher> vouchers = new ArrayList<>();
        Voucher voucher;
        
        try{
            PreparedStatement st = dao.conn.prepareStatement("SELECT * FROM voucher WHERE transaction_id >= ? and transaction_id <=?;");
            st.setInt(1, startId);
            st.setInt(2, endId);
            
            ResultSet result = st.executeQuery();
            
            while(result.next()){
                int id = result.getInt("id");
                int transactionId = result.getInt("transaction_id");
                int productId = result.getInt("product_id");
                int quantity = result.getInt("quantity");
                int price = result.getInt("price");
                
                voucher = new Voucher(id, transactionId, productId, quantity, price);
                vouchers.add(voucher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vouchers;
    }
}
