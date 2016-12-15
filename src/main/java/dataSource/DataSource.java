package dataSource;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DataSource {

	// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	EmbeddedDatabase db = builder
		.setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
		.addScript("db/sql/create-db.sql")
		.addScript("db/sql/insert-data.sql")
		.build();
	//return db;
}
