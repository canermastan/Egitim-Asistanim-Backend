package com.egitimasistanim.backend.api;

import com.egitimasistanim.backend.entity.Question;
import com.egitimasistanim.backend.entity.dtos.QuestionDto;
import com.egitimasistanim.backend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @GetMapping("/all/{userId}/{pageNo}")
    public ResponseEntity<Page<QuestionDto>> getAll(@PathVariable Integer userId, @PathVariable int pageNo) {
        Page<Question> questions = questionService.findAllByUserId(userId, pageNo);
        int totalElements = (int) questions.getTotalElements();
        return ResponseEntity.ok().body(
                new PageImpl<QuestionDto>(questions.getContent()
                        .stream()
                        .map(question -> modelMapper.map(question, QuestionDto.class))
                        .collect(Collectors.toList()), questions.getPageable(), totalElements)
        );
    }

    @PostMapping("/save")
    public ResponseEntity<QuestionDto> save(@RequestBody QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        QuestionDto returnQuestionDto = modelMapper.map(questionService.save(question), QuestionDto.class);
        return ResponseEntity.ok().body(returnQuestionDto);
    }

    @PostMapping("/update")
    public ResponseEntity<QuestionDto> update(@RequestBody QuestionDto questionDto) {
        Question question = questionService.findById(questionDto.getId()).orElseThrow(() -> new RuntimeException("Question not found"));
        modelMapper.map(questionDto, question);
        QuestionDto returnQuestionDto = modelMapper.map(questionService.update(question), QuestionDto.class);
        return ResponseEntity.ok().body(returnQuestionDto);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        questionService.delete(id);
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }
}
