package com.algaworks.osworks.domain.repository;

import com.algaworks.osworks.domain.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
