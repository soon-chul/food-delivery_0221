package food.delivery.domain;

import food.delivery.StoreOwnerApplication;
import food.delivery.domain.Rejected;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cooking_table")
@Data
public class Cooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        Rejected rejected = new Rejected(this);
        rejected.publishAfterCommit();
    }

    public static CookingRepository repository() {
        CookingRepository cookingRepository = StoreOwnerApplication.applicationContext.getBean(
            CookingRepository.class
        );
        return cookingRepository;
    }

    public void acceptOrReject(AcceptOrRejectCommand acceptOrRejectCommand) {
        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();
    }

    public void start(StartCommand startCommand) {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    public void finish(FinishCommand finishCommand) {
        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    public static void loadOrderInfo(OrderCreated orderCreated) {
        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCreated.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

    }
}
