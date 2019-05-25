package com.oskarro.justcode.repositories;

import com.oskarro.justcode.domains.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByArticleId(Long articleId, Pageable pageable);

    Optional<Comment> findByIdAndArticleId(Long id, Long articleId);
}
