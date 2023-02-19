package com.egitimasistanim.backend.service;

import com.egitimasistanim.backend.entity.Question;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface QuestionService {
    Page<Question> findAllByUserId(Integer userId, int pageNo);

    Question save(Question question);
    Question update(Question question);
    void delete(Integer id);

    Optional<Question> findById(Integer id);

}
