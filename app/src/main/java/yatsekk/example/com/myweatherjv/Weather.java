package yatsekk.example.com.myweatherjv;

public class Weather {

    private String timeHour;
    private String cityName;
    private int currentTemp;
    private String currentWeather;
    private String comfortTemp;
    private String humidity;
    private String pressure;
    private String wind;

    public Weather(String timeHour, int currentTemp, String currentWeather, String wind) {
        this.timeHour = timeHour;
        this.currentTemp = currentTemp;
        this.currentWeather = currentWeather;
        this.wind = wind;
    }

    public Weather(String cityName, int currentTemp, String currentWeather, String comfortTemp, String humidity, String pressure, String wind) {
        this.cityName = cityName;
        this.currentTemp = currentTemp;
        this.currentWeather = currentWeather;
        this.comfortTemp = comfortTemp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
    }

    public String getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(String timeHour) {
        this.timeHour = timeHour;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public String getComfortTemp() {
        return comfortTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWind() {
        return wind;
    }
}
