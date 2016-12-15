package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dao.HistoricalDataDaoImpl;

/**
 * Service Class to get currency exchange rates
 * 
 * @author Akshay
 * 
 */
@Service
public class CurrencyExchangeService {

	private static final Logger logger = LoggerFactory
			.getLogger(CurrencyExchangeService.class);

	private static final String END_POINT = "http://www.apilayer.net/api/live";
	private static final String API_KEY = "631633d8f45c1125ac8af55ed5512179";

	/**
	 * Invoking currency layer API to get exchange rates
	 * 
	 * @param supportedCurrenciesArr
	 * @return
	 */
	public JSONObject getExchangeRateFromAPI(String[] supportedCurrenciesArr) {

		String exchangeRateArr[] = new String[supportedCurrenciesArr.length];
		StringBuffer sb = new StringBuffer();

		// Construct the service URL
		sb.append(END_POINT + "?access_key=" + API_KEY + "&currencies=");
		int i = 0;
		for (i = 0; i < supportedCurrenciesArr.length - 1; i++) {
			sb.append(supportedCurrenciesArr[i] + ",");
		}
		sb.append(supportedCurrenciesArr[i]);
		sb.append("&format=1");
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(sb.toString());
			URLConnection yc = url.openConnection();
			// Reading the API response
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			StringBuffer response = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();

			// Parse the response to get quotes into the JSON
			JSONObject parse = new JSONObject(response.toString());

			json = (JSONObject) parse.get("quotes");

		} catch (Exception ex) {
			logger.debug("Exception during API invoking: " + ex.getMessage());
		}
		return json;
	}
}
