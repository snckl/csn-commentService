package com.csn.commentservice.repository;

import com.csn.commentservice.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<List<Comment>> findAllByPostId(Long id);

    @Transactional
    void deleteAllByPostId(Long id);
}
