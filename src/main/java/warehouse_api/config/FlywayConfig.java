package warehouse_api.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@Startup
@Singleton
public class FlywayConfig {
    private String MIGRATION_LOCATION;
    private String DRIVER;
    private String JDBC_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;

    private Flyway flyway;

    public FlywayConfig() {
        initContextVariables();
        doMigrate();
    }

    private void initContextVariables() {
        try {
            InitialContext ctx = new InitialContext();

            MIGRATION_LOCATION = (String) ctx.lookup("migration_location");
            DRIVER = (String) ctx.lookup("db_driver");
            JDBC_URL = (String) ctx.lookup("connection");
            DB_USERNAME = (String) ctx.lookup("db_user");
            DB_PASSWORD = (String) ctx.lookup("db_password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doMigrate() {
        flyway = new Flyway();
        flyway.setLocations(MIGRATION_LOCATION);
        flyway.setDataSource(getDataSource());
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
    }

    private DataSource getDataSource() {
        //configuration
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER);
        hikariConfig.setJdbcUrl(JDBC_URL);
        hikariConfig.setUsername(DB_USERNAME);
        hikariConfig.setPassword(DB_PASSWORD);

        //datasource
        return new HikariDataSource(hikariConfig);
    }
}
