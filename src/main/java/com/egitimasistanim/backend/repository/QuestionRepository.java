package com.egitimasistanim.backend.repository;

import com.egitimasistanim.backend.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // @Query("SELECT q FROM Question q Join User u on q.user.id = u.id WHERE u.id = :userId")
    @Query("SELECT q FROM Question q")
    Page<Question> findAllByUserId(Integer userId, Pageable pageable);

}
