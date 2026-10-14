package com.klu.bookingservice.service;

import com.klu.bookingservice.client.EventClient;
import com.klu.bookingservice.client.UserClient;
import com.klu.bookingservice.dto.BookingRequest;
import com.klu.bookingservice.entity.Booking;
import com.klu.bookingservice.entity.BookingStatus;
import com.klu.bookingservice.repository.BookingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final EventClient eventClient;

    public BookingService(
            BookingRepository bookingRepository,
            UserClient userClient,
            EventClient eventClient) {

        this.bookingRepository = bookingRepository;
        this.userClient = userClient;
        this.eventClient = eventClient;
    }

    public Booking createBooking(
            Long userId,
            BookingRequest request) {

        if (request.getEventId() == null ||
                request.getSeatsBooked() == null ||
                request.getSeatsBooked() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid booking details"
            );
        }

        // Check user
        userClient.getUser(userId);

        // Check event
        eventClient.getEvent(request.getEventId());

        // Reserve seats
        eventClient.reserveSeats(
                request.getEventId(),
                request.getSeatsBooked()
        );

        Booking booking = new Booking();

        booking.setUserId(userId);
        booking.setEventId(request.getEventId());
        booking.setSeatsBooked(request.getSeatsBooked());
        booking.setBookingStatus(
                BookingStatus.CONFIRMED
        );

        return bookingRepository.save(booking);
    }

    public List<Booking> getMyBookings(Long userId) {

        return bookingRepository.findByUserId(userId);
    }

    public Booking cancelBooking(
            Long userId,
            Long bookingId) {

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Booking not found"
                    )
                );

        if (!booking.getUserId().equals(userId)) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot cancel this booking"
            );
        }

        if (booking.getBookingStatus()
                == BookingStatus.CANCELLED) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Booking already cancelled"
            );
        }

        eventClient.releaseSeats(
                booking.getEventId(),
                booking.getSeatsBooked()
        );

        booking.setBookingStatus(
                BookingStatus.CANCELLED
        );

        return bookingRepository.save(booking);
    }
}