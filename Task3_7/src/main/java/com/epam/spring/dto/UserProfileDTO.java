package com.epam.spring.dto;

import com.epam.spring.domain.UserProfileEntity;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {

    private Long id;
    private String firstName;
    private String lastName;

    public static UserProfileDTO of(UserProfileEntity entity) {
        if (entity == null) {
            return new UserProfileDTO(-1L, "", "");
        }

        return builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
