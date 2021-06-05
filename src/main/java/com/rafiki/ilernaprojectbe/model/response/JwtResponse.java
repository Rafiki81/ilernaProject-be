package com.rafiki.ilernaprojectbe.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
    String token;
    String type = "Bearer";
    String id;
    String username;
    String email;
}
