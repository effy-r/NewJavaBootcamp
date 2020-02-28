package lv.accenture.bootcamp.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SunAPIService {

	@Value("${API.request}")
	private String requestUrl;

	public void getSunrise() {

		try {
			URL url = new URL(requestUrl);
			// lag et nytt URL objekt
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setConnectTimeout(3000);
			urlConnection.connect();
			
			InputStream inputStream = urlConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			throw new RuntimeException(e);
		}
	}
}
