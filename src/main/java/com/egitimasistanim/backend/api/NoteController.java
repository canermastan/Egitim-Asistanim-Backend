package com.egitimasistanim.backend.api;

import com.egitimasistanim.backend.entity.Note;
import com.egitimasistanim.backend.entity.Question;
import com.egitimasistanim.backend.entity.dtos.NoteDto;
import com.egitimasistanim.backend.entity.dtos.QuestionDto;
import com.egitimasistanim.backend.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    private final ModelMapper modelMapper;

    @GetMapping("/all/{userId}/{pageNo}")
    public ResponseEntity<Page<NoteDto>> getAll(@PathVariable Integer userId, @PathVariable int pageNo) {
        Page<Note> notes = noteService.findAllByUserId(userId, pageNo);
        int totalElements = (int) notes.getTotalElements();
        return ResponseEntity.ok().body(
                new PageImpl<NoteDto>(notes.getContent()
                        .stream()
                        .map(note -> modelMapper.map(note, NoteDto.class))
                        .collect(Collectors.toList()), notes.getPageable(), totalElements)
        );
    }

    @PostMapping("/save")
    public ResponseEntity<NoteDto> save(@RequestBody NoteDto noteDto) {
        Note note = modelMapper.map(noteDto, Note.class);
        NoteDto returnNoteDto = modelMapper.map(noteService.save(note), NoteDto.class);
        return ResponseEntity.ok().body(returnNoteDto);
    }

    @PostMapping("/update")
    public ResponseEntity<NoteDto> update(@RequestBody NoteDto noteDto) {
        Note note = noteService.findById(noteDto.getId()).orElseThrow(() -> new RuntimeException("Note not found"));
        modelMapper.map(noteDto, note);
        NoteDto returnNoteDto = modelMapper.map(noteService.update(note), NoteDto.class);
        return ResponseEntity.ok().body(returnNoteDto);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Note> delete(@PathVariable Integer id) {
        noteService.delete(id);
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }
}
