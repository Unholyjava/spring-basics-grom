package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan("entity")//Пакет в котором искать @Entity
public class HibernateConfiguration {
	
    private static final String PROP_DATABASE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String PROP_DATABASE_PASSWORD = "shmopka1488";
    private static final String PROP_DATABASE_URL = "jdbc:oracle:thin:@gromcodegregorydb-lessons.cgiruusabwsj.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String PROP_DATABASE_USERNAME = "main";
    	
	@Resource
    Environment env;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));

        return dataSource;
    }
}
