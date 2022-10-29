package com.rafiki.ilernaprojectbe.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="films")
public class Film {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String title;
    String director;
    String synopsis;
    Integer runningTime;
    @Enumerated(EnumType.STRING)
    Genre genre;
    @CreatedDate
    Date date;
    @OneToMany(cascade = CascadeType.ALL)
    List<FilmComment> comments;
    Double note;
    @CreatedBy
    String username;
}
