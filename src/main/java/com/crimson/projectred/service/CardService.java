package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.exception.cust.NotFoundException;
import com.crimson.projectred.model.Card;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private static final Logger log = LoggerFactory.getLogger(CardService.class);
    private final CardRepository cardRepository;
    private final CustomerService customerService;

    @Transactional
    public void addCard(Card card,Long customerId){
         Customer customer = customerService.getCustomerById(customerId);
         customer.getCards().add(card);
         card.setCustomer(customer);
         cardRepository.save(card);
         log.info("CardService::addCard: Card Added Successfully");
    }

    public Card findByCardId(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(()->new NotFoundException(ExceptionMessage.CARD_NOT_FOUND));
    }
    public void removeCard(Long cardId,Long customerId){
        Card card = cardRepository.findById(cardId).orElseThrow(()->new NotFoundException(ExceptionMessage.CARD_NOT_FOUND));
        if(customerId.equals(card.getCustomer().getCustomerId())){
            cardRepository.deleteById(cardId);
        }else{
            throw new BusinessException("Customer and card do not match");
        }

    }

    public List<Card> getCustomerCards(Long customerId) {
        return cardRepository.findCardByCustomer_CustomerId(customerId).orElseThrow(()-> new NotFoundException((ExceptionMessage.CARD_NOT_FOUND)));
    }
}
