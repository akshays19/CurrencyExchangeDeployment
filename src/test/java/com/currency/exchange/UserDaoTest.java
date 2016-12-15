package com.currency.exchange;

import model.User;

import org.junit.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import dao.UserDao;
import dao.UserDaoImpl;

public class UserDaoTest {

	private EmbeddedDatabase db;
	UserDao userDao;

	@Before
	public void setUp() {
		// db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
		db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("db/sql/create-db.sql")
				.addScript("db/sql/insert-data.sql").build();
	}

	@Test
	public void testFindByname() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.setNamedParameterJdbcTemplate(template);

		User user = userDao.findByUserName("akshay");

		Assert.assertNotNull(user);
		Assert.assertEquals(1, user.getId().intValue());
		Assert.assertEquals("akshay", user.getName());
		Assert.assertEquals("akshay@gmail.com", user.getEmail());

	}

	@After
	public void tearDown() {
		db.shutdown();
	}
}
