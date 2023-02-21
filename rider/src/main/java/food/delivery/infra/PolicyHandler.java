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
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Accepted'"
    )
    public void wheneverAccepted_LoadOrderInfo(@Payload Accepted accepted) {
        Accepted event = accepted;
        System.out.println(
            "\n\n##### listener LoadOrderInfo : " + accepted + "\n\n"
        );

        // Sample Logic //
        Delivery.loadOrderInfo(event);
    }

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
        Delivery.loadOrderInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AddressChanged'"
    )
    public void wheneverAddressChanged_LoadOrderInfo(
        @Payload AddressChanged addressChanged
    ) {
        AddressChanged event = addressChanged;
        System.out.println(
            "\n\n##### listener LoadOrderInfo : " + addressChanged + "\n\n"
        );

        // Sample Logic //
        Delivery.loadOrderInfo(event);
    }
}
