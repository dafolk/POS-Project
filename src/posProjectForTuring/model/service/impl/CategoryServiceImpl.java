package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Category;
import posProjectForTuring.model.dao.ProductDao;
import posProjectForTuring.model.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    ProductDao productDao = new ProductDao();
    
    @Override
    public void insertCategory(Category category) {
        productDao.insertCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return productDao.getAllCategories();
    }
    
}
