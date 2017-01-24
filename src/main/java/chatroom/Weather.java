package chatroom;


public class Weather {
    //here gonna be stored refactored data ftom json aobut weather
    //useing api from online forcast to show actaul weather
    private String cityName;
    private String info;
    private String temp;
    private String tempMax;
    private String tempMin;
    private String pressure;
    private String humidity;
    

    public Weather(String city, String info, String temp, String ps, String hmt,String tempMax,String tempMin){
        this.cityName = city;
        this.info = info;
        this.temp = temp;
        this.pressure = ps;
        this.humidity = hmt;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    @Override
    public String toString(){
        return "Current weather in " + this.cityName +
                " " + this.info +
                " temperature: " + this.temp +
                " max: " + this.tempMax +
                " min. temperature: " + this.tempMin +
                " pressure: " + this.pressure +
                " humidity: " + this.humidity;
    }
    
    
}
