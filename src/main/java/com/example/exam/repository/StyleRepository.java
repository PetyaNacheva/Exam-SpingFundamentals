package com.example.exam.repository;

import com.example.exam.models.entity.Style;
import com.example.exam.models.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    Optional<Style> findByName(StyleEnum name);
}
