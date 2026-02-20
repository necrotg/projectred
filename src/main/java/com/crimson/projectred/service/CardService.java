package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.Card;
import com.crimson.projectred.repositoty.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private CardRepository cardRepository;

    public Card findByCardId(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(()->new BusinessException(ExceptionMessage.CARD_NOT_FOUND));
    }
}
