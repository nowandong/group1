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
import java.util.Optional;


@Service
@Transactional
public class PolicyHandler{
    @Autowired LaundryHistoryRepository laundryHistoryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PaymentApproved'")
    public void wheneverPaymentApproved_ReceiveLaundry(@Payload PaymentApproved paymentApproved){

        PaymentApproved event = paymentApproved;
        System.out.println("\n\n##### listener ReceiveLaundry : " + paymentApproved + "\n\n");

        // Sample Logic //

        LaundryHistory.receiveLaundry(event);
    }
    

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ReturnMethodChanged'")
    public void wheneverReturnMethodChanged_ChangeReturnMethod(@Payload ReturnMethodChanged returnMethodChanged){

        ReturnMethodChanged event = returnMethodChanged;
        System.out.println("\n\n##### listener ChangeReturnMethod : " + returnMethodChanged + "\n\n");


        

        // Sample Logic //
        LaundryHistory.changeReturnMethod(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PaymentCanceled'")
    public void wheneverPaymentCanceled_CancelLaundry(@Payload PaymentCanceled paymentCanceled){

        PaymentCanceled event = paymentCanceled;
        System.out.println("\n\n##### listener CancelLaundry : " + paymentCanceled + "\n\n");


        

        // Sample Logic //
        LaundryHistory.cancelLaundry(event);
        

        

    }

    // keep

}


