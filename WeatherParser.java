package org.example;

import org.json.JSONObject;
import org.json.JSONArray;

public class WeatherParser {

    public static String parseWeatherJson(String json, String userName) {
        JSONObject obj = new JSONObject(json);

        String city = obj.getString("name");
        JSONObject main = obj.getJSONObject("main");
        JSONArray weatherArray = obj.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        JSONObject wind = obj.getJSONObject("wind");

        double temp = main.getDouble("temp");
        double feelsLike = main.getDouble("feels_like");
        int humidity = main.getInt("humidity");
        String description = weather.getString("description");
        double windSpeed = wind.getDouble("speed");

        return String.format(
                "Hi %s,\n\nHere is your weather report for %s:\n" +
                        "Weather: %s\n" +
                        "Temperature: %.1f°C\n" +
                        "Feels Like: %.1f°C\n" +
                        "Humidity: %d%%\n" +
                        "Wind Speed: %.1f m/s\n\nHave a great day.",
                userName, city, description, temp, feelsLike, humidity, windSpeed
        );
    }
}