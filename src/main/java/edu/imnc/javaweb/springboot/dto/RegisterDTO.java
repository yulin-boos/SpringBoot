package edu.imnc.javaweb.springboot.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String repassword;
}
