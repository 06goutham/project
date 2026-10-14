package com.klu.bookingservice.client;

import com.klu.bookingservice.dto.EventResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "EVENT-SERVICE")
public interface EventClient {

    @GetMapping("/api/events/{id}")
    EventResponse getEvent(
            @PathVariable("id") Long id
    );

    @PostMapping("/api/events/{id}/reserve")
    EventResponse reserveSeats(
            @PathVariable("id") Long id,
            @RequestParam("seats") int seats
    );

    @PostMapping("/api/events/{id}/release")
    EventResponse releaseSeats(
            @PathVariable("id") Long id,
            @RequestParam("seats") int seats
    );
}