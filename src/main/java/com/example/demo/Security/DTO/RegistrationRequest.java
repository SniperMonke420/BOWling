package com.example.demo.Security.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @JsonProperty("isAdmin")
    private boolean isAdmin;

}
