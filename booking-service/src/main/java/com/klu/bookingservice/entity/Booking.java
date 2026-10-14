package com.klu.bookingservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Integer seatsBooked;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    public Booking() {
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public Integer getSeatsBooked() {
        return seatsBooked;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public void setBookingStatus(
            BookingStatus bookingStatus) {

        this.bookingStatus = bookingStatus;
    }
}