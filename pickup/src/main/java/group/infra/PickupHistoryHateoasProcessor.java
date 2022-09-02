package group.infra;
import group.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class PickupHistoryHateoasProcessor implements RepresentationModelProcessor<EntityModel<PickupHistory>>  {

    @Override
    public EntityModel<PickupHistory> process(EntityModel<PickupHistory> model) {
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/cancel").withRel("cancel"));
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/changereturnmethod").withRel("changereturnmethod"));

        
        return model;
    }
    
}
