package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryRequestCanceled extends AbstractEvent {

    private Long id;
    private Long pickupId;
    private String returnMethod;

    public DeliveryRequestCanceled(LaundryHistory aggregate){
        super(aggregate);
    }
    public DeliveryRequestCanceled(){
        super();
    }
    // keep

}
