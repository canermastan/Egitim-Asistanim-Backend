package com.egitimasistanim.backend.service;

import com.egitimasistanim.backend.entity.Note;
import com.egitimasistanim.backend.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Page<Note> findAllByUserId(Integer userId, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 4, Sort.by("id"));
        return noteRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note update(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void delete(Integer id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Optional<Note> findById(Integer id) {
        return noteRepository.findById(id);
    }
}
