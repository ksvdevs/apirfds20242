package codksv.apirfds20242.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoClient {

    private String idClient;
    private String firstName;
    private String surName;
    private String dni;
    private boolean gender;
    private Integer phone;
    private String address;
    private Date birthDate;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    
}
