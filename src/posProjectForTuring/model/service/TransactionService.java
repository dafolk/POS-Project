package posProjectForTuring.model.service;

import java.sql.Date;
import java.util.List;
import posProjectForTuring.model.Transaction;

public interface TransactionService {
    public void insertTransaction(Transaction transaction);
    public int getLastTransationId();
    public Transaction getTransactionById(int transactionId);
    public List<Transaction> getTransactionByMonth(Date startDate, Date endDate);
}
