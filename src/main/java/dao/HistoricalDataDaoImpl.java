package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.HistoricalData;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Persistence class to handle historical data
 * 
 * @author Akshay
 * 
 */
@Component
public class HistoricalDataDaoImpl implements HistoricalDataDao {

	private static final Logger logger = LoggerFactory
			.getLogger(HistoricalDataDaoImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/**
	 * Fetch historical data by userName
	 * 
	 * @param userName
	 */
	@Override
	public List<HistoricalData> getUserHistoricalData(String userName) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", userName);

		String sql = "SELECT * FROM (SELECT * FROM historical_data WHERE userName=:name ORDER BY id DESC LIMIT 10"
				+ ") sub ORDER BY id DESC";

		List<HistoricalData> result = null;

		try {
			result = namedParameterJdbcTemplate.query(sql, params,
					new HistoricalDataMapper());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.debug("DataAccessException: "+e.getMessage());
		}
		return result;

	}

	/**
	 * Persist exchange rate into historical data
	 * 
	 * @param userName
	 * @param baseCurrency
	 * @param exchangeRateArr
	 */
	public void saveExchangeRate(String userName, String baseCurrency,
			JSONObject exchangeRateArr, String dateTime) {

		String SQL_INSERT = "INSERT INTO HISTORICAL_DATA (userName, baseCurrency, exchangeRate, queryDate) VALUES (:userName, :baseCurrency, :exchangeRate, :queryDate)";
		Map namedParameters = new HashMap();
		try {
			namedParameters.put("userName", userName);
			namedParameters.put("baseCurrency", baseCurrency);
			namedParameters.put("exchangeRate", exchangeRateArr.toString());
			namedParameters.put("queryDate", dateTime);
			long id = namedParameterJdbcTemplate
					.update(SQL_INSERT, namedParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("Exception during insert historical data: "+e.getMessage());
		}

	}

	private static final class HistoricalDataMapper implements
			RowMapper<HistoricalData> {

		public HistoricalData mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			HistoricalData historicalData = new HistoricalData();
			historicalData.setId(rs.getInt("id"));
			historicalData.setUserName(rs.getString("userName"));
			historicalData.setBaseCurrency(rs.getString("baseCurrency"));
			historicalData.setExchangeRates(rs.getString("exchangeRate"));
			historicalData.setDate(rs.getString("queryDate"));
			return historicalData;
		}
	}
}
