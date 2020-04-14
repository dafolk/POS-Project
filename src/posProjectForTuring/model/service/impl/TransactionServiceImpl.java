package posProjectForTuring.model.service.impl;

import java.sql.Date;
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
    public List<Transaction> getTransactionByMonth(Date startDate, Date endDate) {
        return this.transactionDao.getTransactionByMonth(startDate, endDate);
    }
    
}
