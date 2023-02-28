package nttdata.bootcamp.quarkus.buyservice.entity;

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
@MongoEntity(collection="buyservice")
public class BuyServiceEntity extends ReactivePanacheMongoEntity {

    private ObjectId id;
    private String descripcion;
    private String dniComprador;
    private double priceBootcoin;
    private double amount;
    private String dniTransRecibi;
}
