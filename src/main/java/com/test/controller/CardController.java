package com.test.controller;

import com.test.model.Card;
import com.test.model.Lists;
import com.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.createCard(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Integer id, @RequestBody Card card) {
        return ResponseEntity.ok(cardService.updateCard(id, card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/move/{cardId}/{listId}")
    public ResponseEntity<Void> moveCard(@PathVariable Integer cardId, @PathVariable Lists listId) {
        cardService.moveCard(cardId, listId);
        return ResponseEntity.noContent().build();
    }
}
