package github.pasiahopelto.scorelib.writer;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DbMaker {

	private static final String CREATE_SQL = "create.sql";

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate createDatabase() {
		DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:test", "sa", "");
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
		return new JdbcTemplate(dataSource);
	}

	private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource(CREATE_SQL));
        return databasePopulator;
    }
}
