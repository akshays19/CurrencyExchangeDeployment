package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Handles user persistence 
 * @author Akshay
 *
 */
@Component
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/**
	 * Finds user from database by userName
	 */
	@Override
	public User findByUserName(String name) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);

		String sql = "SELECT * FROM users WHERE name=:name";

		User result = null;

		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params,
					new UserMapper());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.debug("DataAccessException during findByName: " + e.getMessage());
		}

		return result;

	}

	@Override
	public List<User> findAll() {

		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM users";

		List<User> result = namedParameterJdbcTemplate.query(sql, params,
				new UserMapper());

		return result;

	}

	/**
	 * Insert new user record into databases
	 * @param user
	 * @return
	 */
	public long registerUser(User user) {
		String SQL = "INSERT INTO USERS (name, email, password, dob, street, zip, city, country) VALUES (:name, :email, :password, :dob, :street, :zip, :city, :country)";
		long id=-1;
		Map namedParameters = new HashMap();
		try {
			namedParameters.put("name", user.getName());
			namedParameters.put("email", user.getEmail());
			namedParameters.put("password", user.getPassword());
			namedParameters.put("dob", user.getDob());
			namedParameters.put("zip", user.getZipCode());
			namedParameters.put("street", user.getStreet());
			namedParameters.put("city", user.getCity());
			namedParameters.put("country", user.getCountry());
			id = namedParameterJdbcTemplate.update(SQL, namedParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception during user create: " + e.getMessage());
		}
		return id;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setDob(rs.getString("dob"));
			user.setStreet(rs.getString("street"));
			user.setZipCode(rs.getString("zip"));
			user.setCity(rs.getString("city"));
			user.setCountry(rs.getString("country"));
			return user;
		}
	}

}
