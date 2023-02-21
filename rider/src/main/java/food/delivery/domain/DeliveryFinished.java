package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryFinished extends AbstractEvent {

    private Long id;

    public DeliveryFinished(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryFinished() {
        super();
    }
}
