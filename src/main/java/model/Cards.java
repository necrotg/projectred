package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Cards {
    @Id
    private Long id;
    private String nameOnCard;
    private Date validDate;
    private String cardNumber;
    private int CVC;
    private String cpf;

}
