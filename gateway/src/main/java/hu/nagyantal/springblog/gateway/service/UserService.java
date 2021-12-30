package hu.nagyantal.springblog.gateway.service;

import hu.nagyantal.springblog.gateway.domain.Authority;
import hu.nagyantal.springblog.gateway.repository.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
public class UserService {

    private final AuthorityRepository authorityRepository;

    public UserService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }


    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    @Transactional(readOnly = true)
    public Flux<String> getAuthorities() {
        return authorityRepository.findAll().map(Authority::getName);
    }
}


