package com.rafiki.ilernaprojectbe.controller;

import com.rafiki.ilernaprojectbe.model.Film;
import com.rafiki.ilernaprojectbe.model.FilmComment;
import com.rafiki.ilernaprojectbe.service.FilmService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 4000)
@RestController
@RequestMapping("/flashes/films")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmController {

    final FilmService filmService;

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film){
        return new ResponseEntity<>(filmService.createFilm(film), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Film>> listFilms(){
        return new ResponseEntity<>(filmService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getBook(@PathVariable Integer id){
        return new ResponseEntity<>(filmService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/filmComments")
    public ResponseEntity<List<FilmComment>> listComments(@PathVariable Integer id){
        return new ResponseEntity<>(filmService.listCommentsByFilmId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/filmComments")
    public ResponseEntity<FilmComment> createComment(@PathVariable Integer id, @RequestBody FilmComment comment) {
        return new ResponseEntity<>(filmService.createFilmComment(id, comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id){
        filmService.deleteFilmById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
