package lv.accenture.bootcamp.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class SunAPIService {

	@Value("${API.request}")
	private String requestUrl;

	public void getSunrise(String requestedDate) {

		try {
			URL url = new URL(requestUrl + "&date" + requestedDate);
			// lag et nytt URL objekt
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setConnectTimeout(3000);
			urlConnection.connect();

			InputStream inputStream = urlConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			// 29.10.2020 Bulding a String Builder

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;

			while ((line = bufferedReader.readLine()) != null) {

				stringBuilder.append(line + "\n");

			}

			String jsonResponse = stringBuilder.toString();
			bufferedReader.close();
			// buffered reader is supposed to close all the previous readers

			Gson gson = new Gson();
			SunApiResponse sunApiResponse = gson.fromJson(jsonResponse, SunApiResponse.class);

			String sunrise = sunApiResponse.getResults().getSunrise();

			System.out.println(sunrise);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa");
			// HH - hours, if time was 05:16:46, hh if time 05:16:46

			Date sunriseDate = simpleDateFormat.parse(sunrise);
			sunriseDate = new Date(sunriseDate.getTime() + (2 * 60 * 60 * 1000));
			
			System.out.println(sunriseDate);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			throw new RuntimeException(e);
		}
	}
}
