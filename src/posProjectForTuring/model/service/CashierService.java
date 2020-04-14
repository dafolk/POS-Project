/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Cashier;

/**
 *
 * @author hp
 */
public interface CashierService {
    public void insertCashier(Cashier cashier);
    public void updateCashier(Cashier cashier);
    public void deleteCashier(int cashierId);
    public List<Cashier> getAllCashiers();
    public Cashier getCashierById(int cashierId);
    public Cashier getCashierByUsername(String username);
}
