package warehouse_api.config;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.dbunit.*;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.operation.DatabaseOperation;
//import org.flywaydb.core.Flyway;
//import org.junit.After;
//import org.junit.Before;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceUnit;
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;

public class DBUnitConfig { // extends DataSourceBasedDBTestCase

//    @PersistenceUnit(unitName = "warehouse_1")
//    private EntityManagerFactory entityManagerFactory ;
//
//    protected IDatabaseTester tester;
//    private Properties prop;
//    protected IDataSet beforeData;
//
//    //TODO moved to properties
//    private String DBUNIT_DRIVER_CLASS = "org.h2.Driver";
//    private String DBUNIT_CONNECTION_URL = "jdbc:h2:mem:test"; // jdbc:h2:mem:test;DB_CLOSE_DELAY=-1
//    private String DBUNIT_USERNAME = "";
//    private String DBUNIT_PASSWORD = "";
//    private String MIGRATION_LOCATION = "classpath:/migration/";
//
//
//    @Before
//    public void setUp() throws ClassNotFoundException {
//        System.out.println(getClass().getResource("/META-INF/persistence.xml"));
//        System.out.println(DBUnitConfig.class.getResource("/").getPath());
//
//        //        System.out.println(Class.forName("org.hibernate.ejb.HibernatePersistence"));
//
//        tester = new JdbcDatabaseTester(DBUNIT_DRIVER_CLASS, DBUNIT_CONNECTION_URL, DBUNIT_USERNAME, DBUNIT_PASSWORD);
//
//        System.out.println(entityManagerFactory == null);
//        createDB();
//    }
//
//    @After
//    public void doDestroy() {
//        cleanDB();
//    }
//
//    private void cleanDB() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<String> tablesForDrop = new ArrayList<String>() {{
//            add("category");
//            add("user");
//            add("customer");
//            add("item");
//            add("details");
//        }};
//
//        entityManager.getTransaction().begin();
//
//        tablesForDrop.forEach(table ->
//                entityManager.createNativeQuery("DROP TABLE " + table)
//        );
//
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public DBUnitConfig(String name) {
//        super(name);
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, DBUNIT_DRIVER_CLASS);
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, DBUNIT_CONNECTION_URL);
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, DBUNIT_USERNAME);
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, DBUNIT_PASSWORD);
//    }
//
//    private void createDB() {
//        Flyway flyway = new Flyway();
//        flyway.setLocations(MIGRATION_LOCATION);
//        flyway.setDataSource(getDataSource());
//        flyway.setBaselineOnMigrate(true);
//        flyway.migrate();
//    }
//
//    @Override
//    protected DataSource getDataSource() {
//        //configuration
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(DBUNIT_DRIVER_CLASS);
//        hikariConfig.setJdbcUrl(DBUNIT_CONNECTION_URL);
//        hikariConfig.setUsername(DBUNIT_USERNAME);
//        hikariConfig.setPassword(DBUNIT_PASSWORD);
//
//        //datasource
//        return new HikariDataSource(hikariConfig);
//    }
//
//    @Override
//    protected IDataSet getDataSet() throws Exception {
//        return beforeData;
//    }
//
//    @Override
//    protected DatabaseOperation getTearDownOperation() throws Exception {
//        return DatabaseOperation.DELETE_ALL;
//    }
}
