package com.example.demo.Security.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDataResponse {
    private Long id;

    private String firstName;
    private String lastName;
}
