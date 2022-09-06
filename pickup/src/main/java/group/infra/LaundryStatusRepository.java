package group.infra;

import group.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="laundryStatuses", path="laundryStatuses")
public interface LaundryStatusRepository extends PagingAndSortingRepository<LaundryStatus, Long> {

    List<LaundryStatus> findByPickupId(Long pickupId);


    void deleteByPickupId(Long pickupId);


    // keep

}
