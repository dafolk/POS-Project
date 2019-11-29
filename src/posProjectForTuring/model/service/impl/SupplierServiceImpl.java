package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Supplier;
import posProjectForTuring.model.dao.ProductDao;
import posProjectForTuring.model.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    ProductDao productDao = new ProductDao();
    
    @Override
    public void insertSupplier(Supplier supplier) {
        this.productDao.insertSupplier(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return productDao.getAllSuppliers();
    }

    @Override
    public String getSupplierName(int supplierId) {
        return productDao.getSupplierName(supplierId);
    }

    @Override
    public int getSupplierId(String supplierName) {
        return productDao.getSupplierId(supplierName);
    }

    @Override
    public void deleteSupplier(int supplierId) {
        this.productDao.deleteSupplier(supplierId);
    }
    
}
