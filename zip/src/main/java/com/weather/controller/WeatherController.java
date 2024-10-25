package com.weather.controller;

import com.weather.model.DailyWeatherSummary;
import com.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("/weather-summary")
    public String getWeatherSummary(Model model) {
        LocalDate today = LocalDate.now();
        List<DailyWeatherSummary> summaries = weatherRepository.findByDate(today);

        model.addAttribute("summaries", summaries);

        return "weather-summary"; // This is the name of the Thymeleaf template
    }
}
