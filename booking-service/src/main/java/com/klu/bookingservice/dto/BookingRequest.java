package com.klu.bookingservice.dto;

public class BookingRequest {

    private Long eventId;
    private Integer seatsBooked;

    public BookingRequest() {
    }

    public Long getEventId() {
        return eventId;
    }

    public Integer getSeatsBooked() {
        return seatsBooked;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
}