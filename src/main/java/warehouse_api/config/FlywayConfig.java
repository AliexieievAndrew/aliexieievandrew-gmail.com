package warehouse_api.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Startup
@Singleton
public class FlywayConfig {
    private String MIGRATION_LOCATION = "classpath:/migration/";
    private String DRIVER = "org.postgresql.Driver";
    private String JDBC_URL = "jdbc:postgresql://localhost:5432/warehouse";
    private String DB_USERNAME = "postgres";
    private String DB_PASSWORD = "24893232";

    private Flyway flyway;

    @PostConstruct
    private void init() {
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
