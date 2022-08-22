package com.boyo.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
