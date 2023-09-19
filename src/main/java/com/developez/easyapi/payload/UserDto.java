package com.developez.easyapi.payload;

import lombok.Data;

@Data
public class UserDto {
    Long id;
    String name;
    String lastname;
    String email;
    String fiscalcode;
    String province;
    Long phone;
    Integer age;
}