package food.delivery.domain;

import food.delivery.HungerApplication;
import food.delivery.domain.AddressChanged;
import food.delivery.domain.OrderCancelled;
import food.delivery.domain.OrderCreated;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foodId;

    private String address;

    private String option;

    private Long customerId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        OrderCreated orderCreated = new OrderCreated(this);
        orderCreated.publishAfterCommit();

        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();

        AddressChanged addressChanged = new AddressChanged(this);
        addressChanged.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = HungerApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public static void updateState(CookStarted cookStarted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(cookStarted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }

    public static void updateState(CookFinished cookFinished) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(cookFinished.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
}
