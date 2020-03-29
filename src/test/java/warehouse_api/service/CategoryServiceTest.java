package warehouse_api.service;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import warehouse_api.model.entity.Category;
import warehouse_api.repository.CategoryDao;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CategoryServiceTest extends TestCase {

    private  Context  ctx;
    private  EJBContainer ejbContainer;

    @BeforeClass
    public  void setUp() {
        Map<String, Object> properties = new HashMap<String, Object>();
//        properties.put(EJBContainer.APP_NAME, "tbi-test");
        properties.put(EJBContainer.MODULES, new File("target/classes")); //Deploying app
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish"); // glassfish domain.xml


        ejbContainer = EJBContainer.createEJBContainer(properties);
        System.out.println("Opening the container" );
        ctx = ejbContainer.getContext();
    }
//
    @AfterClass
    public void tearDown() {
        ejbContainer.close();
        System.out.println("Closing the container" );
    }

    @Test
    public void testAll() throws NamingException {
        CategoryService categoryService = (CategoryService) ctx.lookup("java:global/classes/CategoryService");

        List<Category> all1 = categoryService.all();
        CategoryDao categoryDao = (CategoryDao) ctx.lookup("java:global/classes/CategoryDao");
        List<Category> all = categoryDao.findAll();
        System.out.println("TEST");
    }

}
