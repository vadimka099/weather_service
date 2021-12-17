package com.task.weather.repository;

import com.task.weather.model.weather_history;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface weatherRepo extends CrudRepository<weather_history,Long> {

    @Query("select p from weather_history p where p.weather_date=?1")
    Optional<weather_history> findByDate(LocalDate date);

}
