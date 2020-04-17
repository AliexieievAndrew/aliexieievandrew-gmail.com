package warehouse_api.config;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseTest  extends TestCase {
    protected Context ctx;
    protected  EJBContainer ejbContainer;

    @BeforeClass
    public  void setUp() {
        Map<String, Object> properties = new HashMap<String, Object>();
//        properties.put(EJBContainer.APP_NAME, "tbi-test");
        properties.put(EJBContainer.MODULES, new File("target/classes")); //Deploying app
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish"); // glassfish domain.xml

        ejbContainer = EJBContainer.createEJBContainer(properties);
        ctx = ejbContainer.getContext();
        System.out.println("Opening the test container" );
    }

    @AfterClass
    public void tearDown() {
        ejbContainer.close();
        System.out.println("Closing the test container");
    }

    private void initTestDb() {}

    private void cleanTestDb() {}
}
