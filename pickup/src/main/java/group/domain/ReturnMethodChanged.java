package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReturnMethodChanged extends AbstractEvent {

    private Long id;
    private String returnMethod;
    private String status;

    public ReturnMethodChanged(PickupHistory aggregate){
        super(aggregate);
    }
    public ReturnMethodChanged(){
        super();
    }
    // keep

}
