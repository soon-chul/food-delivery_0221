package food.delivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import food.delivery.config.kafka.KafkaProcessor;
import food.delivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    CookingRepository cookingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCreated'"
    )
    public void wheneverOrderCreated_LoadOrderInfo(
        @Payload OrderCreated orderCreated
    ) {
        OrderCreated event = orderCreated;
        System.out.println(
            "\n\n##### listener LoadOrderInfo : " + orderCreated + "\n\n"
        );

        // Sample Logic //
        Cooking.loadOrderInfo(event);
    }
}
