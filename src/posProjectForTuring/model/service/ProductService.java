package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Product;

public interface ProductService {
    public void insertProduct(Product Product);
    public List<Product> getAllProducts();
    public Product getProductById(int productId);
    public void updateProduct(Product product);
    public void deleteProduct(int productId);
}
