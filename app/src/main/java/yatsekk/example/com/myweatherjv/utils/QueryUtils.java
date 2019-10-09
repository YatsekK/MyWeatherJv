package yatsekk.example.com.myweatherjv.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import yatsekk.example.com.myweatherjv.Weather;

public class QueryUtils {

    private QueryUtils() {
    }

    public static Weather fetchWeatherData(String stringUrl) {
        URL url = makeUrl(stringUrl);
        String jsonString = null;
        try {
            jsonString = makeHTTPRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractFeatureFromJson(jsonString);
    }

    private static URL makeUrl(String stringUrl) {
        URL url = null;
        if (stringUrl != null) {
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    private static Weather extractFeatureFromJson(String weatherJSON) {
        Weather weather = null;
        try {
            JSONObject jsonObject = new JSONObject(weatherJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            String currentWeather = jsonArray.getJSONObject(0).getString("description");
            String currentCity = jsonObject.getString("name");
            double currentTempDouble = jsonObject.getJSONObject("main").getDouble("temp");
            int currentTemp = (int) Math.round(currentTempDouble);
            String comfortTemp = jsonObject.getJSONObject("main").getString("temp_min");
            String humidity = jsonObject.getJSONObject("main").getString("humidity");
            String pressure = jsonObject.getJSONObject("main").getString("pressure");
            String wind = jsonObject.getJSONObject("wind").getString("speed");
            weather = new Weather(currentCity,
                    currentTemp,
                    currentWeather,
                    comfortTemp,
                    humidity,
                    pressure,
                    wind);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }

}
