package com.rafiki.ilernaprojectbe.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="users_flashes")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String username;
    String email;
    String password;
}
