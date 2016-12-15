package model;

/**
 * Model to store historical data
 * 
 * @author Akshay
 * 
 */
public class HistoricalData {

	private int id;
	private String userName;
	private String baseCurrency;
	private String exchangeRates;
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getExchangeRates() {
		return exchangeRates;
	}

	public void setExchangeRates(String exchangeRates) {
		this.exchangeRates = exchangeRates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
