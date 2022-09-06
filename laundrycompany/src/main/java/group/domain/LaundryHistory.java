package group.domain;

import group.domain.DeliveryRequestCanceled;
import group.domain.DeliveryStarted;
import group.LaundrycompanyApplication;
import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import java.util.List;
import lombok.Data;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name="LaundryHistory_table")
@Data

public class LaundryHistory  {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    private Long id;
    private Long pickupId;
    private String status;
    private String returnMethod;

    @PostPersist
    public void onPostPersist(){

        //Delivery Cancel
        DeliveryRequestCanceled deliveryRequestCanceled = new DeliveryRequestCanceled(this);
        //deliveryRequestCanceled.setPickupId(pickupId);
        //BeanUtils.copyProperties(this, deliveryRequestCanceled);
        //deliveryRequestCanceled.publish();
        //deliveryRequestCanceled.publishAfterCommit();


        //Delevery Started
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        //deliveryStarted.setPickupId(pickupId);
        //BeanUtils.copyProperties(this, deliveryStarted);
        //deliveryStarted.publish();
        //deliveryStarted.publishAfterCommit();

    }

    public static LaundryHistoryRepository repository(){
        LaundryHistoryRepository laundryHistoryRepository = LaundrycompanyApplication.applicationContext.getBean(LaundryHistoryRepository.class);
        return laundryHistoryRepository;
    }




    public static void receiveLaundry(PaymentApproved paymentApproved){

        /** Example 1:  new item
        LaundryHistory laundryHistory = new LaundryHistory();
        laundryHistory.setPickupId(paymentApproved.getPickupId());
        repository().save(laundryHistory);
        */

        //laundryHistory에 등록
        LaundryHistory laundryHistory = new LaundryHistory();
        laundryHistory.setId(paymentApproved.getId());
        laundryHistory.setPickupId(paymentApproved.getPickupId());
        repository().save(laundryHistory);

        //DeliveryStarted 이벤트에 Publish
        DeliveryStarted deliveryStarted = new DeliveryStarted(laundryHistory);
        deliveryStarted.publishAfterCommit();


        /** Example 2:  finding and process
        repository().findByPickupId(paymentApproved.getPickupId()).ifPresent(laundryHistory->{
           
            laundryHistory.setPickupId(paymentApproved.getPickupId());
            repository().save(laundryHistory);


         });
         */

        /*
        repository().findByPickupId(paymentApproved.getPickupId()).ifPresent(laundryHistory->{
            DeliveryStarted deliveryStarted = new DeliveryStarted(laundryHistory);
            //laundryHistory.setStatus("Delivery Started");
            
            deliveryStarted.publishAfterCommit();
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


        repository().findByPickupId(returnMethodChanged.getId()).ifPresent(laundryHistory->{
            laundryHistory.setReturnMethod(returnMethodChanged.getReturnMethod());
            repository().save(laundryHistory);

            DeliveryRequestCanceled deliveryRequestCanceled = new DeliveryRequestCanceled();
            deliveryRequestCanceled.setId(laundryHistory.getId());
            deliveryRequestCanceled.setPickupId(laundryHistory.getPickupId());
            deliveryRequestCanceled.setReturnMethod(laundryHistory.getReturnMethod());
            deliveryRequestCanceled.publishAfterCommit();
        });
    
    }



    public static void cancelLaundry(PaymentCanceled paymentCanceled){

        /** Example 1:  new item 
        LaundryHistory laundryHistory = new LaundryHistory();
        repository().save(laundryHistory);

        */

        /** Example 2:  finding and process
        
        repository().findByPickupId(paymentCanceled.getPickupId()).ifPresent(laundryHistory->{
            
            laundryHistory.setPickupId() // do something
            repository().delete(laundryHistory);

         });
         */

        repository().findByPickupId(paymentCanceled.getId()).ifPresent(laundryHistory->{
            // kafka publish.
            //DeliveryRequestCanceled deliveryRequestCanceled = new DeliveryRequestCanceled(laundryHistory);
            //deliveryRequestCanceled.publishAfterCommit();

            // delivery 데이터 삭제.
            repository().delete(laundryHistory);
        });

    }


}
