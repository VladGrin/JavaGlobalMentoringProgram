package com.epam.spring.dto.form.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpFormData {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
