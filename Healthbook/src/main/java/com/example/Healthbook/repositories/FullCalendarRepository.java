package com.example.Healthbook.repositories;

import com.example.Healthbook.model.FullCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FullCalendarRepository extends JpaRepository<FullCalendar,Long> {

}
