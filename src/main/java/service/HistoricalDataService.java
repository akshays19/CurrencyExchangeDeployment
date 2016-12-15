package service;

import java.util.List;

import model.HistoricalData;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HistoricalDataDaoImpl;

/**
 * Service to invoke DAO classes
 * @author Akshay
 *
 */
@Service
public class HistoricalDataService {

	@Autowired
	private HistoricalDataDaoImpl historicalDataDao;

	public List<HistoricalData> findByUsername(String userName) {
		return historicalDataDao.getUserHistoricalData(userName);
	}
	
	public void saveHistoricalData(String userName, String baseCurrency, JSONObject exchangeRateArr, String queryTime){
		historicalDataDao.saveExchangeRate(userName, baseCurrency, exchangeRateArr, queryTime);
	}
}
