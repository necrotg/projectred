package com.crimson.projectred.control;

import com.crimson.projectred.constant.ResponseMessage;
import com.crimson.projectred.model.Card;
import com.crimson.projectred.model.StandardResponse;
import com.crimson.projectred.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/{customerId}/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<StandardResponse> addCard(@RequestBody Card card, @PathVariable Long customerId){
        cardService.addCard(card,customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new StandardResponse(HttpStatus.OK.value(), ResponseMessage.CARD_ADDED));
    }
    @GetMapping
    public ResponseEntity<List<Card>> getCustomerCards(@PathVariable Long customerId){
        List<Card> cards = cardService.getCustomerCards(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(cards);
    }
    @DeleteMapping("/{cardId}")
    public ResponseEntity<StandardResponse> deleteCard(@PathVariable Long cardId, @PathVariable Long customerId){
        cardService.removeCard(cardId,customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new StandardResponse(HttpStatus.OK.value(), ResponseMessage.CARD_DELETED));
    }

}
