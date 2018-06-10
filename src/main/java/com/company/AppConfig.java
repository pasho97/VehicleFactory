package com.company;

import com.company.components.VinGenerator;
import com.company.components.engine.EngineFactoryConfig;
import com.company.components.model.ModelConfig;
import com.company.components.transmission.TransmissionConfig;
import com.company.storages.SqlServerVehiclePersistentStorage;
import com.company.storages.VehiclePersistentStorage;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Configuration class for Spring Annotation application context
 * <p>
 * Transaction management is defined here
 * <p>
 * This class reads the information needed to connect to the database from databaseInfo.properties file
 * Here are imported and united all the other Configuration classes
 */
@Configuration
@ComponentScan("com.company.*")
@EnableTransactionManagement
@Import({EngineFactoryConfig.class, ModelConfig.class, TransmissionConfig.class})
@PropertySource("databaseInfo.properties")
public class AppConfig {


    @Autowired
    private Environment env;

    @Bean
    public VinGenerator vinGenerator() {
        return new VinGenerator("0123456789ABCDEFGHJKLMNPRSTUVWXYZ", "BG", 0);
    }

    @Bean
    public JdbcTemplate template() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public int linesCount() {
        return 2;
    }

    @Bean("streams")
    public InputStream[] inputStreams() throws FileNotFoundException {
        return new InputStream[]{new FileInputStream("asd"), new FileInputStream("abv")};
    }

    @Bean
    public PlatformTransactionManager txManager() {

        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SQLServerDataSource sqlServerDataSource = new SQLServerDataSource();
        sqlServerDataSource.setUser(env.getProperty("mssql.username"));
        sqlServerDataSource.setPassword(env.getProperty("mssql.password"));
        sqlServerDataSource.setServerName(env.getProperty("mssql.server"));
        sqlServerDataSource.setPortNumber(Integer.parseInt(env.getProperty("mssql.port")));

        return sqlServerDataSource;
    }

    @Bean
    public VehiclePersistentStorage storage() {
        return new SqlServerVehiclePersistentStorage(template());
    }

}
