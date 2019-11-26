package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Product;

public interface ProductService {
    public void insertProduct(Product Product);
    public List<Product> getAllProducts();
    public String getCategoryName(int categoryId);
    public String getSupplierName(int supplierId);
}
