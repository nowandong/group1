package group.domain;

import group.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="laundryHistories", path="laundryHistories")
public interface LaundryHistoryRepository extends PagingAndSortingRepository<LaundryHistory, Long>{

}
