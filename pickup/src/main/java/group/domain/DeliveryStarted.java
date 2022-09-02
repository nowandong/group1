package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long pickupId;

// keep

}


