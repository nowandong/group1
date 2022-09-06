package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PickupRequested extends AbstractEvent {

    private Long id;
    private String returnMethod;
    private String status;

    public PickupRequested(PickupHistory aggregate){
        super(aggregate);
    }

    public PickupRequested(){
        super();
    }
    // keep

}
