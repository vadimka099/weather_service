package com.task.weather.service;


import com.task.weather.model.weather_history;
import com.task.weather.repository.weatherRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class weatherService {

    @Autowired
    private weatherRepo weatherRep;

    public Iterable<weather_history> findAll(){
        return weatherRep.findAll();
    }

    public weather_history getTodayWeather() {
        weather_history weather=new weather_history();
        Optional<weather_history> op=weatherRep.findByDate(LocalDate.now(ZoneId.of("Europe/Moscow")));

        if (op.isPresent()) {
            weather = op.get();



        }
        else{

            try{
                Document doc = Jsoup.connect("https://yandex.ru/pogoda/moscow?lat=55.753215&lon=37.622504")
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements listNews = doc.getElementsByClass("temp fact__temp fact__temp_size_s");
                String result=listNews.text().substring(19);


                weather.setWeather_value(result);
                weather.setWeather_date(LocalDate.now(ZoneId.of("Europe/Moscow")));
                weatherRep.save(weather);


            }
            catch (IOException e) {
            }

    }
        return weather;

    }

}
