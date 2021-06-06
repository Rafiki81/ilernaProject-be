package com.rafiki.ilernaprojectbe.service;

import com.rafiki.ilernaprojectbe.model.Film;
import com.rafiki.ilernaprojectbe.model.FilmComment;
import com.rafiki.ilernaprojectbe.repository.FilmRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmService {

    final FilmRepository filmRepository;

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film findById(Integer id) {
        return filmRepository.findById(id).isPresent() ? filmRepository.findById(id).get() : null ;
    }

    public List<Film> list() {
        return filmRepository.findAll();
    }

    public void deleteFilmById(Integer filmId) {
        filmRepository.deleteById(filmId);
    }

    public List<FilmComment> listCommentsByFilmId(Integer id) {
        return filmRepository.findById(id).isPresent() ? filmRepository.findById(id).get().getComments() : null;
    }

    public FilmComment createFilmComment(Integer filmId, FilmComment comment) {
        Film film = filmRepository.findById(filmId).isPresent() ? filmRepository.findById(filmId).get() : null;
        if (film != null) {
            film.getComments().add(comment);
            filmRepository.save(calculateNote(film));
        }
        return comment;
    }

    private Film calculateNote(Film film) {
        film.setNote(film.getComments().stream()
                .map(FilmComment::getNote)
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0));
        return film;
    }


}
