package com.egitimasistanim.backend.service;

import com.egitimasistanim.backend.entity.Note;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface NoteService {
    Page<Note> findAllByUserId(Integer userId, int pageNo);

    Note save(Note note);
    Note update(Note note);
    void delete(Integer id);

    Optional<Note> findById(Integer id);
}
