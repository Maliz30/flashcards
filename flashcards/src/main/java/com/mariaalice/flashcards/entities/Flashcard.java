package com.mariaalice.flashcards.entities;

import com.mariaalice.flashcards.enums.ConfidenceLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "flashcards")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    private String answer;
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "confidence_level", length = 10, nullable = false)
    private ConfidenceLevel confidenceLevel;

    public Flashcard() {
    }

    public Flashcard(String question, String answer, String category, ConfidenceLevel confidenceLevel) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.confidenceLevel = confidenceLevel;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ConfidenceLevel getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(ConfidenceLevel confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }
    
    @Override
    public boolean equals(Object other){
        Flashcard otherFlashcard;

        if(other instanceof Flashcard){
            otherFlashcard = (Flashcard) other;

            if(otherFlashcard.getId() != null && this.id != null){
                return this.id.equals(otherFlashcard.getId());
            }

            return this == other;
        } else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return 1;
    }
}
