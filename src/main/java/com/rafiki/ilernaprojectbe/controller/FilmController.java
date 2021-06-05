package com.rafiki.ilernaprojectbe.controller;

import com.rafiki.ilernaprojectbe.model.Film;
import com.rafiki.ilernaprojectbe.model.FilmComment;
import com.rafiki.ilernaprojectbe.service.FilmService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 4000)
@RestController
@RequestMapping("/book-lovers/books")
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

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<FilmComment>> listComments(@PathVariable Integer id){
        return new ResponseEntity<>(filmService.listCommentsByBookId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/filmComments")
    public ResponseEntity<FilmComment> createComment(@PathVariable Integer id, @RequestBody FilmComment comment) {
        return new ResponseEntity<>(filmService.createFilmComment(id, comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id){
        filmService.deleteFilmById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}