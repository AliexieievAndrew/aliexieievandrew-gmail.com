package warehouse_api.service;

import org.junit.Test;
import warehouse_api.config.BaseTest;
import warehouse_api.model.entity.Category;
import warehouse_api.repository.CategoryDao;

import javax.naming.NamingException;
import java.util.List;

public class CategoryServiceTest extends BaseTest {

    @Test
    public void testAll() throws NamingException {
        CategoryService categoryService = (CategoryService) ctx.lookup("java:global/classes/CategoryService");

        List<Category> all1 = categoryService.all();
        CategoryDao categoryDao = (CategoryDao) ctx.lookup("java:global/classes/CategoryDao");
        List<Category> all = categoryDao.findAll();
        System.out.println("size:" + all.size());
        System.out.println("TEST");
    }
}
