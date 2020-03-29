package warehouse_api.service;

import warehouse_api.model.entity.Category;
import warehouse_api.repository.CategoryDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CategoryService {

    @EJB
    private CategoryDao categoryDao;

    public List<Category> all() {
        return categoryDao.findAll();
    }

    public Category save(Category category) {
        return categoryDao.persist(category);
    }
}
