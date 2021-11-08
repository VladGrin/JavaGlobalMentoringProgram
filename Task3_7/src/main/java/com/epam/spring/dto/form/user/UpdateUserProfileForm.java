package com.epam.spring.dto.form.user;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserProfileForm {

    private String firstName;

    private String lastName;

    private String occupationName;

    private String companyName;

    private String summary;
}
