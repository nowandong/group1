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
        System.out.println("\n\n##### listener SendNotification : " + PickupId + "\n\n");
        //System.out.println(PickupId+" 세탁물 픽업을 시작합니다."); 
        System.out.println("=========================깨톡===================================="); 
        System.out.println("ID =>["+PickupId + "] 세탁물 픽업을 시작합니다. 부재중이시면 연락 주세요."); 
        System.out.println("================================================================="); 
        System.out.println("\n\n##### listener SendNotification : " + PickupId + "\n\n");
        // Sample Logic //

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='DeliveryRequestCanceled'")
    public void wheneverDeliveryRequestCanceled_SendNotification(@Payload DeliveryRequestCanceled deliveryRequestCanceled){

        DeliveryRequestCanceled event = deliveryRequestCanceled;
        Long PickupId = deliveryRequestCanceled.getPickupId();
        String returnMethod = deliveryRequestCanceled.getReturnMethod();
        System.out.println("\n\n##### listener SendNotification : " + PickupId + "\n\n");
       

        System.out.println("=========================깨톡===================================="); 
        System.out.println("ID =>["+PickupId + "] 세탁물 수거를 ["+ returnMethod +"] 방법 으로  취소합니다"); 
        System.out.println("================================================================="); 
    
        System.out.println("\n\n##### listener SendNotification : " + PickupId + "\n\n");
        // Sample Logic //

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PickupRequested'")
    public void wheneverPickupRequested_SendNotification(@Payload PickupRequested pickupRequested){

        PickupRequested event = pickupRequested;
        Long PickupId = pickupRequested.getId();
        String returnMethod = pickupRequested.getReturnMethod();
        System.out.println("\n\n##### listener SendNotification : " + PickupId + "\n\n");
     

        System.out.println("=========================깨톡===================================="); 
        System.out.println("ID =>["+PickupId + "]  세탁물 픽업 ["+returnMethod +"]로 요청합니다."); 
        System.out.println("================================================================="); 
    
        System.out.println("\n\n##### listener SendNotification : " + PickupId + "\n\n");

        // Sample Logic //

        

    }

    // keep

}


