package warehouse_api.config;

import junit.framework.TestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.*;


public class BaseTestConf extends TestCase {
    private static final String FIXTURES_PATH = "/fixtures_test/";

    protected Context ctx;
    protected EJBContainer ejbContainer;

    private DataSource dataSource;

    @BeforeClass
    public void setUp() throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>();
//        properties.put(EJBContainer.APP_NAME, "tbi-test");
        properties.put(EJBContainer.MODULES, new File("target/classes")); //Deploying app
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish"); // glassfish domain.xml

        ejbContainer = EJBContainer.createEJBContainer(properties);
        System.out.println("Opening the test container");

        ctx = ejbContainer.getContext();
        dataSource = (DataSource) ctx.lookup("jdbc/warehouse");

        initTestDb();
    }

    @AfterClass
    public void tearDown() throws Exception {
        cleanTestDb();
        ejbContainer.close();

        System.out.println("Test container is closed");
    }

    private void initTestDb() throws Exception {
        System.out.println("started init test DB");

        operatedTestDB(dataSource.getConnection(), DatabaseOperation.CLEAN_INSERT);

        System.out.println("finished init test DB");
    }

    private void cleanTestDb() throws Exception{
        System.out.println("started  cleaning test DB");

        operatedTestDB(dataSource.getConnection(), DatabaseOperation.DELETE_ALL);

        System.out.println("finished cleaning test DB");
    }

    private IDataSet getDataSet() throws Exception {
        List<String> items = Arrays.asList(getFixtures());
        InputStream[] streams = new InputStream[getFixtures().length];

        for (int i = 0; i < items.size(); i++) {
            streams[i] = getFixtureStream(items.get(i));
        }

        IDataSet[] dataSets = new IDataSet[streams.length];

        for (int i = 0; i < streams.length; i++) {
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            dataSets[i] = builder.build(streams[i]);
        }

        return new CompositeDataSet(dataSets);
    }

    private InputStream getFixtureStream(String fixture) {
        return getClass().getResourceAsStream(FIXTURES_PATH + fixture);
    }

    private String[] getFixtures() {
        return new String[]{"category.xml","user.xml", "customer.xml", "item.xml", "details.xml"};
    } //"item.xml"

    private void operatedTestDB(Connection connection, DatabaseOperation operation) throws Exception {
        IDatabaseConnection iDatabaseConnection = new DatabaseConnection(connection);
        DatabaseConfig dbConfig = iDatabaseConnection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
        IDataSet data = getDataSet();

        ReplacementDataSet rDataSet = new ReplacementDataSet(data);
        rDataSet.addReplacementObject("[create_date]", new Date());

        operation.execute(iDatabaseConnection, rDataSet);

        iDatabaseConnection.close();
        connection.close();
    }
}
