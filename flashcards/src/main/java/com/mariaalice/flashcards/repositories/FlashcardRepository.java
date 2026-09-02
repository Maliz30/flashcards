package com.mariaalice.flashcards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mariaalice.flashcards.entities.Flashcard;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

}
