package warehouse_api.service;

import warehouse_api.model.dto.CategoryCreateDto;
import warehouse_api.model.entity.Category;
import warehouse_api.repository.CategoryDao;
import warehouse_api.service.exception.BusinessException;

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

    public Category categoryByName(String name) {
        return categoryDao.categoryByName(name);
    }

    public Category create(CategoryCreateDto createDto) throws BusinessException {
        Category categoryByName = categoryByName(createDto.getCategoryName());

        if (categoryByName == null) {
            Category category = new Category();
            category.setCategoryName(createDto.getCategoryName());

            return save(category);
        } else {
            throw new BusinessException(categoryByName.getCategoryName() + " already exist");
        }
    }
}
