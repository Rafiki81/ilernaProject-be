package com.rafiki.ilernaprojectbe.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="comments_flashes")
public class FilmComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String title;
    String comment;
    @CreatedDate
    Date date;
    Integer note;
    @CreatedBy
    String userName;
}
