package group.domain;

import group.domain.DeliveryRequestCanceled;
import group.domain.DeliveryStarted;
import group.LaundrycompanyApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="LaundryHistory_table")
@Data

public class LaundryHistory  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long pickupId;

    @PostPersist
    public void onPostPersist(){


        DeliveryRequestCanceled deliveryRequestCanceled = new DeliveryRequestCanceled(this);
        deliveryRequestCanceled.publishAfterCommit();



        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();

    }

    public static LaundryHistoryRepository repository(){
        LaundryHistoryRepository laundryHistoryRepository = LaundrycompanyApplication.applicationContext.getBean(LaundryHistoryRepository.class);
        return laundryHistoryRepository;
    }




    public static void receiveLaundry(PaymentApproved paymentApproved){

        /** Example 1:  new item 
        LaundryHistory laundryHistory = new LaundryHistory();
        repository().save(laundryHistory);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(laundryHistory->{
            
            laundryHistory // do something
            repository().save(laundryHistory);


         });
        */

        
    }
    public static void changeReturnMethod(ReturnMethodChanged returnMethodChanged){

        /** Example 1:  new item 
        LaundryHistory laundryHistory = new LaundryHistory();
        repository().save(laundryHistory);

        */

        /** Example 2:  finding and process
        
        repository().findById(returnMethodChanged.get???()).ifPresent(laundryHistory->{
            
            laundryHistory // do something
            repository().save(laundryHistory);


         });
        */

        
    }
    public static void cancelLaundry(PaymentCanceled paymentCanceled){

        /** Example 1:  new item 
        LaundryHistory laundryHistory = new LaundryHistory();
        repository().save(laundryHistory);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(laundryHistory->{
            
            laundryHistory // do something
            repository().save(laundryHistory);


         });
        */

        
    }


}
