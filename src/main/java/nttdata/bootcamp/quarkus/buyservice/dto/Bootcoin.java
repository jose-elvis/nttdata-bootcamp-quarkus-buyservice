package nttdata.bootcamp.quarkus.buyservice.dto;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bootcoin{

    private ObjectId id;
    private String dniClient;
    private String nroPhone;
    private String email;
    private double amount;
}
