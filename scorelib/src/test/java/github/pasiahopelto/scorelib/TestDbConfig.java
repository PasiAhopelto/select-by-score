package github.pasiahopelto.scorelib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class TestDbConfig {

	private JdbcTemplate jdbcTemplate;

	@Before
	public void before() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
		jdbcTemplate = context.getBean(JdbcTemplate.class);
		context.close();
	}

	@Test
	public void createdJdbcTemplateBean() {
		assertNotNull(jdbcTemplate);
		assertNotNull(jdbcTemplate.queryForObject("select count(*) from party", Integer.class));
	}

}
