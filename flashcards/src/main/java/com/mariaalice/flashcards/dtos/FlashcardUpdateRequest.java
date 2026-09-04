package com.mariaalice.flashcards.dtos;

import com.mariaalice.flashcards.enums.ConfidenceLevel;

public record FlashcardUpdateRequest(String question, String answer, String category, ConfidenceLevel confidenceLevel) {
}
