package com.klu.eventservice.controller;

import com.klu.eventservice.entity.Event;
import com.klu.eventservice.service.EventService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> create(
            @RequestBody Event event) {

        return ResponseEntity.ok(
                eventService.create(event)
        );
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {

        return ResponseEntity.ok(
                eventService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                eventService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(
            @PathVariable Long id,
            @RequestBody Event event) {

        return ResponseEntity.ok(
                eventService.update(id, event)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        eventService.delete(id);

        return ResponseEntity.ok(
                "Event deleted successfully"
        );
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<Event> reserveSeats(
            @PathVariable Long id,
            @RequestParam int seats) {

        return ResponseEntity.ok(
                eventService.reserveSeats(id, seats)
        );
    }

    @PostMapping("/{id}/release")
    public ResponseEntity<Event> releaseSeats(
            @PathVariable Long id,
            @RequestParam int seats) {

        return ResponseEntity.ok(
                eventService.releaseSeats(id, seats)
        );
    }
}