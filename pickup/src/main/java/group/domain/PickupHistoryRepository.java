package group.domain;

import group.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="pickupHistories", path="pickupHistories")
public interface PickupHistoryRepository extends PagingAndSortingRepository<PickupHistory, Long>{

}
