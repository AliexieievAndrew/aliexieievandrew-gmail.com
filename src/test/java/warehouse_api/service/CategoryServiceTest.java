package warehouse_api.service;

import org.junit.Assert;
import org.junit.Test;
import warehouse_api.config.BaseTestConf;
import warehouse_api.model.dto.CategoryCreateDto;
import warehouse_api.model.entity.Category;
import warehouse_api.repository.CategoryDao;

import javax.naming.NamingException;
import java.util.List;

public class CategoryServiceTest extends BaseTestConf {

    @Test
    public void testContext() throws Exception{
        CategoryService categoryService = (CategoryService) ctx.lookup("java:global/classes/CategoryService");
        CategoryDao categoryDao = (CategoryDao) ctx.lookup("java:global/classes/CategoryDao");

        Assert.assertNotNull(categoryService);
        Assert.assertNotNull(categoryDao);
    }

    @Test
    public void testAll() throws NamingException {
        CategoryService categoryService = (CategoryService) ctx.lookup("java:global/classes/CategoryService");

        List<Category> all = categoryService.all();

        Assert.assertNotNull(all);
        Assert.assertTrue(all.size() > 0);
    }

    @Test
    public void testSave() throws NamingException {
        String testName = "Test Name";

        CategoryService categoryService = (CategoryService) ctx.lookup("java:global/classes/CategoryService");

        Category category = new Category();
        category.setCategoryName(testName);

        categoryService.save(category);
        Category categoryFromDb = categoryService.categoryByName(testName);

        Assert.assertTrue(testName.equals(categoryFromDb.getCategoryName()));
    }

    @Test
    public void testCreate() throws Exception {
        String categoryName = "testCreate";

        CategoryCreateDto testCreate = new CategoryCreateDto(categoryName);

        CategoryService categoryService = (CategoryService) ctx.lookup("java:global/classes/CategoryService");
        categoryService.create(testCreate);

        Category category = categoryService.categoryByName(categoryName);

        Assert.assertNotNull(categoryName);
        Assert.assertEquals(categoryName, category.getCategoryName());
    }
}
