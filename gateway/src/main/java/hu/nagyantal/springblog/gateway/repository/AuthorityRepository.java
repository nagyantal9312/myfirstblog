package hu.nagyantal.springblog.gateway.repository;

import hu.nagyantal.springblog.gateway.domain.Authority;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AuthorityRepository extends R2dbcRepository<Authority, String> {
}
