package com.salt.www.dto;

import java.util.UUID;

public record JokeDTO(String text, String responseAt, UUID id) {}
