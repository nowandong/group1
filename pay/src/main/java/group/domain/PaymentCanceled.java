package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PaymentCanceled extends AbstractEvent {

    private Long id;
    private Long pickupId;

    public PaymentCanceled(PaymentHistory aggregate){
        super(aggregate);
    }
    public PaymentCanceled(){
        super();
    }
    // keep

}
