package posProjectForTuring.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import posProjectForTuring.model.Transaction;
import posProjectForTuring.model.dao.TransactionDao;
import posProjectForTuring.model.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{
    TransactionDao transactionDao = new TransactionDao();
    
    @Override
    public void insertTransaction(Transaction transaction) {
        this.transactionDao.insertTransaction(transaction);
    }

    @Override
    public int getLastTransationId() {
        return this.transactionDao.getLastTransationId();
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        return this.transactionDao.getTransactionById(transactionId);
    }

    @Override
    public List<Transaction> getTransactionByInterval(Timestamp startDate, Timestamp endDate) {
        return this.transactionDao.getTransactionByInterval(startDate, endDate);
    }
    
}
