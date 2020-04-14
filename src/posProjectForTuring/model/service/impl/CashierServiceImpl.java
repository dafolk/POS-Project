/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Cashier;
import posProjectForTuring.model.dao.CashierDao;
import posProjectForTuring.model.service.CashierService;

/**
 *
 * @author hp
 */
public class CashierServiceImpl implements CashierService {
    
    CashierDao cashierDao = new CashierDao();
    
    @Override
    public void insertCashier(Cashier cashier) {
        cashierDao.insertCashier(cashier);
    }

    @Override
    public List<Cashier> getAllCashiers() {
        return cashierDao.getAllCashiers();
    }

    @Override
    public Cashier getCashierById(int cashierId) {
        return this.cashierDao.getCashierById(cashierId);
    }

    @Override
    public void updateCashier(Cashier cashier) {
        this.cashierDao.updateCashier(cashier);
    }

    @Override
    public void deleteCashier(int cashierId) {
        this.cashierDao.deleteCashier(cashierId);
    }

    @Override
    public Cashier getCashierByUsername(String username) {
        return this.cashierDao.getCashierByUsername(username);
    }
    
}
