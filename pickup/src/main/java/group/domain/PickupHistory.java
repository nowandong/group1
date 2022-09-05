package group.domain;

import group.PickupApplication;
import group.config.kafka.KafkaProcessor;

import javax.persistence.*;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import lombok.Data;

@Entity
@Table(name="PickupHistory_table")
@Data
@EnableBinding(value= {KafkaProcessor.class})//, MessageBinder.class})
public class PickupHistory  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
        
    private Long id;
    private String returnMethod;
    private String status;

    private static KafkaProcessor kafkaProcessor;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        group.external.PaymentHistory paymentHistory = new group.external.PaymentHistory();

        // 결제 command 쪽에 ID 추가.
        paymentHistory.setPickupId(
            getId()
        );

        // mappings goes here. req/res 구현부.
        PickupApplication.applicationContext.getBean(group.external.PaymentHistoryService.class)
            .pay(paymentHistory);


        PickupRequested pickupRequested = new PickupRequested(this);

        // request 시 등록.
        pickupRequested.publishAfterCommit();
        // kafka message 발행. (pub/sub 패턴, pub)
        
        kafkaProcessor.outboundTopic().send(MessageBuilder.withPayload(pickupRequested).build());
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
        // kafka subscribe?
        //kafkaProcessor.inboundTopic().subscribe(handler);
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
