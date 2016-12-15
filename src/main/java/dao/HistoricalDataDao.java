package dao;

import java.util.List;

import model.HistoricalData;

public interface HistoricalDataDao {

	
	public List<HistoricalData> getUserHistoricalData(String userName);
}
