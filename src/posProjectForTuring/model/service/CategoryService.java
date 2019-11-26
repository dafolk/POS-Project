package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Category;

public interface CategoryService {
    public void insertCategory(Category category);
    public List<Category> getAllCategories();
}
