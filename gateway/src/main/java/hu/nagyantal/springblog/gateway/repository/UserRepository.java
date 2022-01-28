package hu.nagyantal.springblog.gateway.repository;

import hu.nagyantal.springblog.gateway.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends R2dbcRepository<User, Long> {


    Flux<User> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);


}
