package com.hyunjuuun.memorization.domain.glossary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GlossaryRepository extends JpaRepository<Glossary, Long> {

    @Query("select g from Glossary g order by g.id desc")
    List<Glossary> findAllDesc();

}
