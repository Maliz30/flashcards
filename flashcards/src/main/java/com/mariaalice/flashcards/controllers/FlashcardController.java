package com.mariaalice.flashcards.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mariaalice.flashcards.entities.Flashcard;
import com.mariaalice.flashcards.repositories.FlashcardRepository;

@RestController
@RequestMapping(path = "/api/")
public class FlashcardController {
    private final FlashcardRepository flashcardRepository;


    public FlashcardController(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    @GetMapping(path = "/flashcards")
    public ResponseEntity<List<Flashcard>> getFlashcards(){
        List<Flashcard> flashcards = flashcardRepository.findAll();

        return ResponseEntity.ok(flashcards);
    }

    @GetMapping(path = "/flashcard/{id}")
    public ResponseEntity<Flashcard> getFlashcard(@PathVariable Long id){
        Optional<Flashcard> existingFlashcard = flashcardRepository.findById(id);

        if(existingFlashcard.isPresent()){
            return ResponseEntity.ok(existingFlashcard.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/flashcard")
    public ResponseEntity<Flashcard> postFlashcard(@RequestBody Flashcard flashcard){
        Flashcard response = flashcardRepository.save(flashcard);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path = "/flashcard/{id}")
    public ResponseEntity<Flashcard> putFlashcard(@PathVariable Long id, @RequestBody Flashcard newFlashcard){
        Optional<Flashcard> existingFlashcard = flashcardRepository.findById(id);

        if(existingFlashcard.isPresent()){
            Flashcard oldFlashcard = existingFlashcard.get();

            oldFlashcard.setAnswer(newFlashcard.getAnswer());
            oldFlashcard.setCategory(newFlashcard.getCategory());
            oldFlashcard.setConfidenceLevel(newFlashcard.getConfidenceLevel());
            oldFlashcard.setQuestion(newFlashcard.getQuestion());
            
            Flashcard updatedFlashcard = flashcardRepository.save(oldFlashcard);
            return ResponseEntity.ok(updatedFlashcard);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/flashcard/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long id){
        boolean flashcardExists = flashcardRepository.existsById(id);

        if(flashcardExists){
            flashcardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }

    }
    
}
