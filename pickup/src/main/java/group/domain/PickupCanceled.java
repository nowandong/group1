package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PickupCanceled extends AbstractEvent {

    private Long id;
    private String returnMethod;
    private String status;

    public PickupCanceled(PickupHistory aggregate){
        super(aggregate);
    }
    public PickupCanceled(){
        super();
    }
    // keep

}
