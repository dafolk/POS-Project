package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Supplier;
import posProjectForTuring.model.dao.ProductDao;
import posProjectForTuring.model.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    ProductDao productDao = new ProductDao();
    
    @Override
    public void insertSupplier(Supplier supplier) {
        productDao.insertSupplier(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return productDao.getAllSuppliers();
    }
    
}
