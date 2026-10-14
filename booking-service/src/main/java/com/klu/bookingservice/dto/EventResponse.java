package com.klu.bookingservice.dto;

import java.time.LocalDateTime;

public class EventResponse {

    private Long eventId;
    private String eventName;
    private String location;
    private LocalDateTime eventDate;
    private Integer totalSeats;

    public EventResponse() {
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }
}