package posProjectForTuring.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import posProjectForTuring.controller.CurrentDateTime;
import posProjectForTuring.model.Transaction;

/**
 *
 * @author hp
 */
public class TransactionDao {
    Dao dao = Dao.getDao();
    
    public void insertTransaction(Transaction transaction){
        try {
            PreparedStatement st = dao.conn.prepareStatement("INSERT INTO `transaction`(cashier_id, date, total, pay_amount, change_amount) VALUES(?, ?, ?, ?, ?);");
            
            st.setInt(1, transaction.getCashierId());
            st.setTimestamp(2, CurrentDateTime.get());
            st.setInt(3, transaction.getTotal());
            st.setInt(4, transaction.getPayAmount());
            st.setInt(5, transaction.getChangeAmount());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getLastTransationId(){
        int lastTransactionId = 0;
        
        try {
            Statement st = dao.conn.createStatement();
            ResultSet result = st.executeQuery("SELECT id FROM `transaction`  \n" +
                                                "ORDER BY id DESC  \n" +
                                                "LIMIT 1;");
            
            while(result.next()){
                lastTransactionId = result.getInt("id");
            }
            
            result.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastTransactionId;
    }
    
    public Transaction getTransactionById(int transactionId){
        Transaction transaction = new Transaction();
        
        try {
            PreparedStatement st = dao.conn.prepareStatement("SELECT * FROM `transaction` WHERE id=?");
            st.setInt(1, transactionId);
            
            ResultSet result = st.executeQuery();
            
            while(result.next()){
                transaction.setId(result.getInt("id"));
                transaction.setCashierId(result.getInt("cashier_id"));
                transaction.setDate(result.getDate("date"));
                transaction.setTotal(result.getInt("total"));
                transaction.setPayAmount(result.getInt("pay_amount"));
                transaction.setChangeAmount(result.getInt("change_amount"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return transaction;
    }
    
    public List<Transaction> getTransactionByMonth(Date startDate, Date endDate){
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction;
        
        try {
            PreparedStatement st = dao.conn.prepareStatement("SELECT * FROM `transaction` WHERE date BETWEEN ? AND ?;");
            st.setDate(1, startDate);
            st.setDate(2, endDate);
            
            ResultSet result = st.executeQuery();
            
            while(result.next()){
                int id = result.getInt("id");
                int cashierId = result.getInt("cashier_id");
                Date date = result.getDate("date");
                int total = result.getInt("total");
                int payAmount = result.getInt("pay_amount");
                int changeAmount = result.getInt("change_amount");
                
                transaction = new Transaction(id, cashierId, date, total, payAmount, changeAmount);
                transactions.add(transaction);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return transactions;
    }
}
