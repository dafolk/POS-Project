package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Product;
import posProjectForTuring.model.dao.ProductDao;
import posProjectForTuring.model.service.ProductService;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao = new ProductDao();
    
    @Override
    public void insertProduct(Product Product) {
        this.productDao.insertProduct(Product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public void updateProduct(Product product) {
        this.productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        this.productDao.deleteProduct(productId);
    }

    @Override
    public void restockProduct(Product product) {
        this.productDao.restockProduct(product);
    }
    
}