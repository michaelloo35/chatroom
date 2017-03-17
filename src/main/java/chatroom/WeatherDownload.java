package chatroom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import JsonStructures.*;

public class WeatherDownload {
	// i'm using openweather api city == krakow default
	private JsonElement data;
	private Weather weather;
	
	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	private final String apiKey = "83d28b738b2ea179816e95b1d46572af";

	 public WeatherDownload() {
		String weatherURL = urlLink("cracow");
		HttpURLConnection request = openHttpURLConnection(weatherURL);

		JsonParser jsonParser = new JsonParser();
		JsonElement root = null;
		try {
			root = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
		} catch (IOException e) {
			System.err.println("Gson or json data error");
		}
		this.data = root;
		this.weather = getWeatherDescription();

	}

	private Weather getWeatherDescription() {
		Gson dataRead = new Gson();
		JsonElement weather = data.getAsJsonObject().get("weather").getAsJsonArray().get(0);
		JsonElement temperature = data.getAsJsonObject().get("main");

		WeatherDescription weatherDescription = dataRead.fromJson(weather, WeatherDescription.class);
		WeatherTemp weatherTemp = dataRead.fromJson(temperature, WeatherTemp.class);

		return new Weather("cracow", weatherDescription.getDescription(), weatherTemp.getTemp(),
				weatherTemp.getPressure(), weatherTemp.getHumidity(), weatherTemp.getTempMax(),
				weatherTemp.getTempMin());
	}

	private String urlLink(String cityName) {
		String result = "http://api.openweathermap.org/data/2.5/weather?q=";
		result += cityName;
		result += "&appid=" + this.apiKey + "&units=metric";
		return result;
	}

	private HttpURLConnection openHttpURLConnection(String str) {
		try {
			URL test = new URL(str);
			HttpURLConnection request = (HttpURLConnection) test.openConnection();
			request.connect();
			return request;
		} catch (IOException e) {
			System.out.println("JSON error");
		}
		return null;
	}
}