package github.pasiahopelto.scorelib;

import javax.inject.Singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import github.pasiahopelto.scorelib.writer.DbMaker;

@Configuration
public class DbConfig {

	@Singleton
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new DbMaker().createDatabase();
	}
}
