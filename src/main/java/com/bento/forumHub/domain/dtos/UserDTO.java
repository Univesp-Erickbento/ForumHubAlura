package com.bento.forumHub.domain.dtos;

public record UserDTO(
        Long id,
        String username,
        String password,
        String role
) {}

