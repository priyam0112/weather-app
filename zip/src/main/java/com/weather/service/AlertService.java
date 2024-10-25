package com.weather.service;

import com.weather.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Value("${weather.alert.temperature.threshold}")
    private double temperatureThreshold;

    @Value("${weather.alert.consecutive.updates}")
    private int consecutiveUpdatesThreshold;

    private int consecutiveBreachCount = 0;

    public void checkTemperatureAlert(WeatherData weatherData) {
        double temperatureCelsius = weatherData.getMain().getTemp() - 273.15;

        if (temperatureCelsius > temperatureThreshold) {
            consecutiveBreachCount++;
            if (consecutiveBreachCount >= consecutiveUpdatesThreshold) {
                triggerAlert(temperatureCelsius, weatherData);
                consecutiveBreachCount = 0; // Reset after alerting
            }
        } else {
            consecutiveBreachCount = 0; // Reset if below threshold
        }
    }

    private void triggerAlert(double temperature, WeatherData weatherData) {
        System.out.println("ALERT: Temperature has exceeded the threshold! Current temperature: " + temperature + "°C");

        // Future option: Send an email alert
        // emailService.sendAlert(temperature, weatherData);
    }
}
