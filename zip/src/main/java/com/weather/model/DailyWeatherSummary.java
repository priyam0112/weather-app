package com.weather.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dailyWeatherSummaries")
public class DailyWeatherSummary {

    @Id
    private String id;
    private LocalDate date;
    private String city;
    private double averageTemperature;
    private double maximumTemperature;
    private double minimumTemperature;
    private String dominantCondition;
    private int entryCount; // Keeps track of the number of entries

    // Getters and Setters

    public DailyWeatherSummary(String city, LocalDate date) {
        this.city = city;
        this.date = date;
        this.averageTemperature = 0.0;
        this.maximumTemperature = Double.MIN_VALUE;
        this.minimumTemperature = Double.MAX_VALUE;
        this.dominantCondition = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(double maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public String getDominantCondition() {
        return dominantCondition;
    }

    public void setDominantCondition(String dominantCondition) {
        this.dominantCondition = dominantCondition;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }
}
