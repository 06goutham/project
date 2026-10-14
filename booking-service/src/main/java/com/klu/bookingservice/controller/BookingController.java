package com.klu.bookingservice.controller;

import com.klu.bookingservice.dto.BookingRequest;
import com.klu.bookingservice.entity.Booking;
import com.klu.bookingservice.service.BookingService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(
            BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody BookingRequest request,
            Authentication authentication) {

        Long userId = getUserId(authentication);

        return ResponseEntity.ok(
                bookingService.createBooking(
                        userId,
                        request
                )
        );
    }

    @GetMapping("/my")
    public ResponseEntity<List<Booking>> myBookings(
            Authentication authentication) {

        Long userId = getUserId(authentication);

        return ResponseEntity.ok(
                bookingService.getMyBookings(userId)
        );
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancel(
            @PathVariable Long id,
            Authentication authentication) {

        Long userId = getUserId(authentication);

        return ResponseEntity.ok(
                bookingService.cancelBooking(
                        userId,
                        id
                )
        );
    }

    private Long getUserId(
            Authentication authentication) {

        return Long.valueOf(
                authentication.getName()
        );
    }
}