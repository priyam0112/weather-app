package com.weather.repository;

import com.weather.model.DailyWeatherSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends MongoRepository<DailyWeatherSummary, String> {
    List<DailyWeatherSummary> findByDate(LocalDate date);
}
