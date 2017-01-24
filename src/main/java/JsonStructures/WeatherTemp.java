package JsonStructures;

import com.google.gson.annotations.SerializedName;

public class WeatherTemp {
	  	@SerializedName("temp")
	    private String temp;

	    @SerializedName("pressure")
	    private String pressure;

	    @SerializedName("humidity")
	    private String humidity;

	    @SerializedName("temp_min")
	    private String tempMin;

	    @SerializedName("temp_max")
	    private String tempMax;

		public String getTemp() {
			return temp;
		}

		public void setTemp(String temp) {
			this.temp = temp;
		}

		public String getPressure() {
			return pressure;
		}

		public void setPressure(String pressure) {
			this.pressure = pressure;
		}

		public String getHumidity() {
			return humidity;
		}

		public void setHumidity(String humidity) {
			this.humidity = humidity;
		}

		public String getTempMin() {
			return tempMin;
		}

		public void setTempMin(String tempMin) {
			this.tempMin = tempMin;
		}

		public String getTempMax() {
			return tempMax;
		}

		public void setTempMax(String tempMax) {
			this.tempMax = tempMax;
		}
}
