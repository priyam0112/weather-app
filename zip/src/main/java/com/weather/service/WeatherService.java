
package com.weather.service;

import com.weather.model.DailyWeatherSummary;
import com.weather.model.WeatherData;
import com.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    private static final String API_KEY = "ADD_KEY_HERE"; // replace with your API key
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid=" + API_KEY;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private AlertService alertService; // Alert service for threshold checking

    private Map<String, DailyWeatherSummary> dailySummaries = new HashMap<>();

    @Scheduled(fixedRate = 300000) // every 5 minutes
    public void fetchWeatherData() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
        RestTemplate restTemplate = new RestTemplate();

        for (String city : cities) {
            try {
                WeatherData weatherData = restTemplate.getForObject(URL, WeatherData.class, city);
                processWeatherData(weatherData, city);
                alertService.checkTemperatureAlert(weatherData); // Check if alert should be triggered
            } catch (Exception e) {
                System.out.println("Error fetching weather data for city: " + city + " - " + e.getMessage());
            }
        }
    }

    private void processWeatherData(WeatherData weatherData, String city) {
        LocalDate date = LocalDate.now();
        double temperatureCelsius = weatherData.getMain().getTemp() - 273.15;

        // Retrieve or initialize summary for the city and date
        DailyWeatherSummary summary = dailySummaries.computeIfAbsent(
            city + "-" + date,
            k -> new DailyWeatherSummary(city, date)
        );

        summary.setAverageTemperature((summary.getAverageTemperature() + temperatureCelsius) / 2);
        summary.setMaximumTemperature(Math.max(summary.getMaximumTemperature(), temperatureCelsius));
        summary.setMinimumTemperature(summary.getMinimumTemperature() == 0.0 ? temperatureCelsius :
                Math.min(summary.getMinimumTemperature(), temperatureCelsius));
        summary.setDominantCondition(weatherData.getWeather().get(0).getMain());

        weatherRepository.save(summary);
    }
}

// package com.weather.service;

// import com.weather.model.DailyWeatherSummary;
// import com.weather.model.WeatherData;
// import com.weather.repository.WeatherRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import java.time.LocalDate;
// import java.util.HashMap;
// import java.util.Map;

// @Service
// public class WeatherService {
//     private static final String API_KEY = ""; // replace with your API key
//     private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid=" + API_KEY;

//     @Autowired
//     private WeatherRepository weatherRepository;

//     // Store daily summaries by date and city
//     private Map<LocalDate, Map<String, DailyWeatherSummary>> dailySummaries = new HashMap<>();

//     @Scheduled(fixedRate = 300000) // every 5 minutes
//     public void fetchWeatherData() {
//         String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
//         RestTemplate restTemplate = new RestTemplate();

//         for (String city : cities) {
//             try {
//                 WeatherData weatherData = restTemplate.getForObject(URL, WeatherData.class, city);
//                 if (weatherData != null) {
//                     processWeatherData(weatherData, city);
//                 }
//             } catch (Exception e) {
//                 System.err.println("Error fetching weather data for city: " + city + " - " + e.getMessage());
//             }
//         }
//     }

//     private void processWeatherData(WeatherData weatherData, String city) {
//         LocalDate date = LocalDate.now();
//         double temperatureCelsius = weatherData.getMain().getTemp() - 273.15;

//         // Get or create the map for today's date
//         Map<String, DailyWeatherSummary> summariesForDate = dailySummaries.computeIfAbsent(date, d -> new HashMap<>());

//         // Get or create the weather summary for the city
//         DailyWeatherSummary summary = summariesForDate.computeIfAbsent(city, c -> new DailyWeatherSummary());

//         // Update summary with weather data
//         summary.setDate(date);
//         summary.setCity(city);
//         summary.setAverageTemperature((summary.getAverageTemperature() + temperatureCelsius) / 2);
//         summary.setMaximumTemperature(Math.max(summary.getMaximumTemperature(), temperatureCelsius));
//         summary.setMinimumTemperature(Math.min(summary.getMinimumTemperature(), temperatureCelsius));
//         summary.setDominantCondition(weatherData.getWeather().get(0).getMain());

//         // Log before saving
//         System.out.println("Saving summary for " + city + " on " + date + ": " + summary);

//         // Save the updated summary in MongoDB
//         weatherRepository.save(summary);
//     }
// }
