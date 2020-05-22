package com.example.otDemoApplication.controller;

import com.example.otDemoApplication.client.ForecastClient;
import com.example.otDemoApplication.model.WeatherDetailDto;
import com.example.otDemoApplication.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherForecastController {

    @Autowired
    ForecastClient client;

    ForecastService forecastService;

    public WeatherForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping(value = "/dates")
    public List<String> getDates() {
        return forecastService.getDates();
    }

    @GetMapping(value = "/forecastInfo")
    public WeatherDetailDto getWeatherDetail(String date, String time) {
        return forecastService.getWeatherDetail(date, time);
    }
}
