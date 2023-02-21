package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPicked extends AbstractEvent {

    private Long id;

    public OrderPicked(Delivery aggregate) {
        super(aggregate);
    }

    public OrderPicked() {
        super();
    }
}
