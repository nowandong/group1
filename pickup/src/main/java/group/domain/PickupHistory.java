package group.domain;

import group.domain.PickupRequested;
import group.PickupApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="PickupHistory_table")
@Data

public class PickupHistory  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        group.external.PaymentHistory paymentHistory = new group.external.PaymentHistory();
        // mappings goes here
        PickupApplication.applicationContext.getBean(group.external.PaymentHistoryService.class)
            .pay(paymentHistory);


        PickupRequested pickupRequested = new PickupRequested(this);
        pickupRequested.publishAfterCommit();

    }

    public static PickupHistoryRepository repository(){
        PickupHistoryRepository pickupHistoryRepository = PickupApplication.applicationContext.getBean(PickupHistoryRepository.class);
        return pickupHistoryRepository;
    }



    public void cancel(){
        PickupCanceled pickupCanceled = new PickupCanceled(this);
        pickupCanceled.publishAfterCommit();

    }
    public void changeReturnMethod(ChangeReturnMethodCommand changeReturnMethodCommand){
        ReturnMethodChanged returnMethodChanged = new ReturnMethodChanged(this);
        returnMethodChanged.publishAfterCommit();

    }

    public static void updateStatus(DeliveryStarted deliveryStarted){

        /** Example 1:  new item 
        PickupHistory pickupHistory = new PickupHistory();
        repository().save(pickupHistory);

        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryStarted.get???()).ifPresent(pickupHistory->{
            
            pickupHistory // do something
            repository().save(pickupHistory);


         });
        */

        
    }


}
