package com.test.service;

import com.test.model.Card;
import com.test.model.Lists;
import com.test.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Integer id, Card card) {
        card.setId(id);
        return cardRepository.save(card);
    }

    public void deleteCard(Integer id) {
        cardRepository.deleteById(id);
    }

    public void moveCard(Integer cardId, Lists listId) {
        Card card = cardRepository.findById(cardId).orElseThrow();
        card.setList(listId);
        cardRepository.save(card);
    }
}
