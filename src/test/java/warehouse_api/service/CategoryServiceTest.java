package warehouse_api.service;

import org.junit.Assert;
import org.junit.Test;
import warehouse_api.config.BaseTest;
import warehouse_api.model.entity.Category;
import warehouse_api.repository.CategoryDao;

import javax.naming.NamingException;
import java.util.List;

public class CategoryServiceTest extends BaseTest {

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
}
