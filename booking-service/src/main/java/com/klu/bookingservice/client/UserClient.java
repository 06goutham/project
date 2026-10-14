package com.klu.bookingservice.client;

import com.klu.bookingservice.dto.UserResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    UserResponse getUser(@PathVariable("id") Long id);
}