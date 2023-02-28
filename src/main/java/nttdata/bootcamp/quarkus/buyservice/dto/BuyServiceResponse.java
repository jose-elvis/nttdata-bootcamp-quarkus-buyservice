package nttdata.bootcamp.quarkus.buyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.buyservice.entity.BuyServiceEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyServiceResponse extends ResponseBase{
    List<BuyServiceEntity>buyService;
}
