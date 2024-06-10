package com.pm.parking_management.parking.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class UserRequest {

    private long idRole;
    private String name;
    private String password;
    private String email;
    private LocalDateTime createdDtm;

}
