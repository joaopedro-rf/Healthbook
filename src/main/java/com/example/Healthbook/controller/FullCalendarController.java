package com.example.Healthbook.controller;

import com.example.Healthbook.model.FullCalendar;
import com.example.Healthbook.service.FullCalendarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(origins = "https://healthclinic.vercel.app")
public class FullCalendarController {
    @Autowired
    private FullCalendarService fullCalendarService;
    @GetMapping("/{id}")
    public ResponseEntity<Optional<FullCalendar>> getDateById(@PathVariable Long id){
        return ResponseEntity.ok(fullCalendarService.findDateByIdOrThrowBadRequestException(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<FullCalendar>> getAllEvents(){
        return new ResponseEntity<>(fullCalendarService.findAllEvents(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<FullCalendar> saveEvent(@Valid @RequestBody FullCalendar fullCalendar){
        FullCalendar newCalendar = fullCalendarService.createNewEvent(fullCalendar);
        return new ResponseEntity<>(newCalendar, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        fullCalendarService.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FullCalendar> updateEvent(@PathVariable Long id,
                                                    @Valid @RequestBody FullCalendar fullCalendar)
    {
        return new ResponseEntity<>(fullCalendarService.updateEvent(id,fullCalendar), HttpStatus.OK);
    }


}
