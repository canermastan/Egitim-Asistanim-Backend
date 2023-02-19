package com.egitimasistanim.backend.service;

import com.egitimasistanim.backend.entity.Question;
import com.egitimasistanim.backend.repository.QuestionRepository;
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
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> findAllByUserId(Integer userId, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 4, Sort.by("id"));
        return questionRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question update(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Optional<Question> findById(Integer id) {
        return questionRepository.findById(id);
    }
}
