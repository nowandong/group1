package group.domain;

import group.domain.PaymentCanceled;
import group.domain.PaymentApproved;
import group.PayApplication;
import javax.persistence.*;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="PaymentHistory_table")
@Data

public class PaymentHistory  {    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long pickupId;

    @PostPersist
    public void onPostPersist(){
        PaymentApproved paymentApproved = new PaymentApproved(this);
        paymentApproved.publishAfterCommit();

    }

    public static PaymentHistoryRepository repository(){
        PaymentHistoryRepository paymentHistoryRepository = PayApplication.applicationContext.getBean(PaymentHistoryRepository.class);
        return paymentHistoryRepository;
    }

   public static void cancelPayment(PickupCanceled pickupCanceled){

        repository().findByPickupId(pickupCanceled.getId()).ifPresent(paymentHistory->{
            // kafka publish.
            PaymentCanceled paymentCanceled = new PaymentCanceled(paymentHistory);
            paymentCanceled.publishAfterCommit();

            // 주문 데이터 삭제.
            repository().delete(paymentHistory);            
        });
    }


}
