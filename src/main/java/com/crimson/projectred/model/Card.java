package com.crimson.projectred.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@Table(name = "tbcard")
public class Card extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    @SequenceGenerator(name = "card_seq", sequenceName = "card_SEQ", allocationSize = 1)
    private Long cardId;
    @Column(nullable = false)
    private String nameOnCard;
    @Column(nullable = false)
    private Date validDate;
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private int CVC;
    @Column(nullable = false)
    private String cpf;
    @JoinColumn(name = "customerId")
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;

}
