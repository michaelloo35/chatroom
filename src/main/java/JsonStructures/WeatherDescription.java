package JsonStructures;

import com.google.gson.annotations.SerializedName;

public class WeatherDescription {
	 @SerializedName("description")
	    private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
