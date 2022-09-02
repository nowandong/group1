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

        DeliveryStarted event = deliveryStarted;
        System.out.println("\n\n##### listener SendNotification : " + deliveryStarted + "\n\n");


        

        // Sample Logic //

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='DeliveryRequestCanceled'")
    public void wheneverDeliveryRequestCanceled_SendNotification(@Payload DeliveryRequestCanceled deliveryRequestCanceled){

        DeliveryRequestCanceled event = deliveryRequestCanceled;
        System.out.println("\n\n##### listener SendNotification : " + deliveryRequestCanceled + "\n\n");


        

        // Sample Logic //

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PickupRequested'")
    public void wheneverPickupRequested_SendNotification(@Payload PickupRequested pickupRequested){

        PickupRequested event = pickupRequested;
        System.out.println("\n\n##### listener SendNotification : " + pickupRequested + "\n\n");


        

        // Sample Logic //

        

    }

    // keep

}


