package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long pickupId;

    public DeliveryStarted(LaundryHistory aggregate){
        super(aggregate);
    }
    public DeliveryStarted(){
        super();
    }
    // keep

}
