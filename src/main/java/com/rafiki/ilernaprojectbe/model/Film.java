package com.rafiki.ilernaprojectbe.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="films_flashes")
public class Film {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String title;
    String director;
    String sinopsis;
    @Enumerated(EnumType.STRING)
    Theme theme;
    @CreatedDate
    Date date;
    @OneToMany(cascade = CascadeType.ALL)
    List<FilmComment> comments;
    Double note;
    @CreatedBy
    String username;
}
