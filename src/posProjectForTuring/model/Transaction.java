package posProjectForTuring.model;

import java.util.Date;

/**
 *
 * @author hp
 */
public class Transaction {
    int id;
    int cashierId;
    Date date;
    int total;
    int payAmount;
    int changeAmount;
    
    public Transaction(){
        
    }

    public Transaction(int id, int cashierId, Date date, int total, int payAmount, int changeAmount) {
        this.id = id;
        this.cashierId = cashierId;
        this.date = date;
        this.total = total;
        this.payAmount = payAmount;
        this.changeAmount = changeAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }
    
    
}
