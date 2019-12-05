package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Category;
import posProjectForTuring.model.dao.ProductDao;
import posProjectForTuring.model.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    ProductDao productDao = new ProductDao();
    
    @Override
    public void insertCategory(Category category) {
        this.productDao.insertCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return productDao.getAllCategories();
    }

    @Override
    public String getCategoryName(int categoryId) {
        return productDao.getCategoryName(categoryId);
    }

    @Override
    public int getCategoryId(String categoryName) {
        return productDao.getCategoryId(categoryName);
    }

    @Override
    public void deleteCategory(int categoryId) {
        this.productDao.deleteCategory(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        this.productDao.updateCategory(category);
    }
    
}
