package com.task.weather.controller;


import com.task.weather.model.weather_history;
import com.task.weather.service.weatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class weatherController {

@Autowired
private weatherService weatherService;

@GetMapping("/weather")
public String getWeather(Model model){
    weather_history weather=new weather_history();
    weather=weatherService.getTodayWeather();


    model.addAttribute("weather",weather);

    return "home";




}



}
