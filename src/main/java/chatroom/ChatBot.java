package chatroom;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChatBot {

	public String Response(String msg) {
		String data = msg.trim();
		switch (data) {
		case "/help":
			return "Available commends: /time - displays current time /date - displays current date /weather - displays current weather in cracow";
		case "/time":
			return getTime();
		case "/date":
			return getData();
		case "greeting":
			return "Welcome to bot channel available commands are /time /date /weather and /help to get commands details";
		case "/weather":
			return weatherInCracow();

		default:
			return "Write /help to get all available options";
		}
	}

	private String weatherInCracow() {
		WeatherDownload weather = new WeatherDownload();
		return weather.getWeather().toString();
	}

	private String getTime() {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		return "Current time: " + timeStamp;
	}

	private String getData() {
		String dataStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(Calendar.getInstance().getTime());
		return "Today is " + dayOfWeek + " " + dataStamp;
	}
}
