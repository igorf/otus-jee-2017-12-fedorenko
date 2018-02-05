package ru.otus.hw5.server.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String login;
    private String password;
}
