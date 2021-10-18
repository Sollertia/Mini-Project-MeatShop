package com.hanghae.miniprojectmeatshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String password;
}
