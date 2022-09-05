package group.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import group.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import group.domain.*;


@Service
@Transactional
public class PolicyHandler{
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='DeliveryStarted'")
    public void wheneverDeliveryStarted_SendNotification(@Payload DeliveryStarted deliveryStarted){
       
        Long PickupId = deliveryStarted.getPickupId();
        DeliveryStarted event = deliveryStarted;
        System.out.println("\n\n##### listener SendNotification : " + deliveryStarted + "\n\n");
        System.out.println(PickupId+" 세탁물 픽업을 시작합니다."); 

        // Sample Logic //

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='DeliveryRequestCanceled'")
    public void wheneverDeliveryRequestCanceled_SendNotification(@Payload DeliveryRequestCanceled deliveryRequestCanceled){

        DeliveryRequestCanceled event = deliveryRequestCanceled;
        System.out.println("\n\n##### listener SendNotification : " + deliveryRequestCanceled + "\n\n");
        Long PickupId = deliveryRequestCanceled.getPickupId();

        System.out.println(PickupId+" 세탁물 픽업 취소합니다."); 


        // Sample Logic //

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PickupRequested'")
    public void wheneverPickupRequested_SendNotification(@Payload PickupRequested pickupRequested){

        PickupRequested event = pickupRequested;
        System.out.println("\n\n##### listener SendNotification : " + pickupRequested + "\n\n");

        Long PickupId = pickupRequested.getId();
        String status = pickupRequested.getStatus();

        System.out.println(PickupId+" 세탁물 픽업 "+status + " 로 요청합니다."); 


        // Sample Logic //

        

    }

    // keep

}


