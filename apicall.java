package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class apicall {
    public static String getWeather(String city) throws Exception{
        String apiId="d56381be15cf199638cfad98b4250b45";
//        String city=city;
        String url=String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",city,apiId);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest postReq=HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(postReq, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println("Raw Response: " + responseBody);

        // Parse JSON response
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

        // Extract weather info
        String cityName = jsonObject.get("name").getAsString();
        JsonObject main = jsonObject.getAsJsonObject("main");
        double temp = main.get("temp").getAsDouble();
        double feelsLike = main.get("feels_like").getAsDouble();
        int humidity = main.get("humidity").getAsInt();

        JsonObject weatherObj = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
        String weatherMain = weatherObj.get("main").getAsString();
        String description = weatherObj.get("description").getAsString();

        System.out.println("\nWeather Data:");
        System.out.println("City: " + cityName);
        System.out.println("Temperature: " + temp + "°C");
        System.out.println("Feels Like: " + feelsLike + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Weather: " + weatherMain + " (" + description + ")");
        return "Hello";

    }
}
