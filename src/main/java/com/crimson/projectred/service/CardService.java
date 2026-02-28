package com.crimson.projectred.service;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.model.Card;
import com.crimson.projectred.model.Customer;
import com.crimson.projectred.repositoty.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CustomerService customerService;

    @Transactional
    public void addCard(Card card,Long customerId){
         Customer customer = customerService.getCustomerById(customerId);
         customer.getCards().add(card);
         card.setCustomer(customer);
    }

    public Card findByCardId(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(()->new BusinessException(ExceptionMessage.CARD_NOT_FOUND));
    }
    public void removeCard(Long cardId,Long customerId){
        Card card = cardRepository.findById(cardId).orElseThrow(()->new BusinessException(ExceptionMessage.CARD_NOT_FOUND));
        if(customerId.equals(card.getCustomer().getCustomerId())){
            cardRepository.deleteById(cardId);
        }else{
            throw new BusinessException("Customer and card do not match");
        }

    }
}
