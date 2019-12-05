package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Category;

public interface CategoryService {
    public void insertCategory(Category category);
    public List<Category> getAllCategories();
    public int getCategoryId(String categoryName);
    public String getCategoryName(int categoryId);
    public void updateCategory(Category category);
    public void deleteCategory(int categoryId);
}
