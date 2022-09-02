package group.infra;
import group.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class LaundryHistoryHateoasProcessor implements RepresentationModelProcessor<EntityModel<LaundryHistory>>  {

    @Override
    public EntityModel<LaundryHistory> process(EntityModel<LaundryHistory> model) {

        
        return model;
    }
    
}
