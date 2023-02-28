package nttdata.bootcamp.quarkus.buyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private ObjectId id;
    private String description;
    private String documentNumber;
    private String cellphoneNumber;
    private String password;
    private double amount;
    private boolean associatedDebitCard;
    private LocalDate registrationDate;
    private String debitCardNumber;
}
