package com.kanban.kanban.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;




    public String getFullName(String firstname, String lastname)
{
    return firstname+" "+ lastname;
}
}
