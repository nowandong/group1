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
    @Autowired PaymentHistoryRepository paymentHistoryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PickupCanceled'")
    public void wheneverPickupCanceled_CancelPayment(@Payload PickupCanceled pickupCanceled){

        PickupCanceled event = pickupCanceled;
        System.out.println("\n\n##### listener CancelPayment : " + pickupCanceled + "\n\n");


        

        // Sample Logic //
        PaymentHistory.cancelPayment(event);
        

        

    }

    // keep

}


