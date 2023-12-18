package com.example.Healthbook.service;


import com.example.Healthbook.exception.BadRequestException;
import com.example.Healthbook.model.FullCalendar;
import com.example.Healthbook.repositories.FullCalendarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FullCalendarService {
    private final FullCalendarRepository fullCalendarRepository;

    @Autowired
    public FullCalendarService(FullCalendarRepository fullCalendarRepository) {
        this.fullCalendarRepository = fullCalendarRepository;
    }

    public Optional<FullCalendar> findDateByIdOrThrowBadRequestException(Long id){

        return Optional.ofNullable(fullCalendarRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Date not found")));
    }
    public List<FullCalendar> findAllEvents(){
        return fullCalendarRepository.findAll();
    }


    @Transactional
    public FullCalendar createNewEvent(FullCalendar fullCalendar) {
        return fullCalendarRepository.save(fullCalendar);
    }

    public void deleteEventById(Long id){
        fullCalendarRepository.deleteById(id);
    }

    public FullCalendar updateEvent(Long id, FullCalendar fullCalendar ){
        findDateByIdOrThrowBadRequestException(id);
        fullCalendar.setId(id);
        return fullCalendarRepository.save(fullCalendar);
    }

}
