package com.egitimasistanim.backend.repository;

import com.egitimasistanim.backend.entity.Note;
import com.egitimasistanim.backend.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    // @Query("SELECT n FROM Note n Join User u on n.user.id = u.id WHERE u.id = :userId")
    @Query("SELECT n FROM Note n")
    Page<Note> findAllByUserId(Integer userId, Pageable pageable);


}
