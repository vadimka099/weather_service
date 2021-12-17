package com.task.weather;


import com.task.weather.model.weather_history;
import com.task.weather.service.weatherService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@SpringBootTest


public class WeatherTests {


    @Autowired
    weatherService weatherService;




    @Test
    public void testWeather(){

        weather_history wth=weatherService.getTodayWeather();
        List<weather_history> list= (List<weather_history>) weatherService.findAll();
        LocalDate ld=LocalDate.now(ZoneId.of("Europe/Moscow"));


        Assertions.assertThat(wth.getWeather_date()).isEqualTo(ld.toString());
        Assertions.assertThat(list.size()).isEqualTo(1);

    }





}
