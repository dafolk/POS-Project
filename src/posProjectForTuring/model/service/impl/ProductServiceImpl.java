package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Product;
import posProjectForTuring.model.dao.ProductDao;
import posProjectForTuring.model.service.ProductService;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDao();
    
    @Override
    public void insertProduct(Product Product) {
        productDao.insertProduct(Product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public String getCategoryName(int categoryId) {
        return productDao.getCategoryName(categoryId);
    }

    @Override
    public String getSupplierName(int supplierId) {
        return productDao.getSupplierName(supplierId);
    }
    
}