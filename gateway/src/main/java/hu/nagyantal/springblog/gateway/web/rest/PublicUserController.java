package hu.nagyantal.springblog.gateway.web.rest;

import hu.nagyantal.springblog.gateway.service.UserService;
import hu.nagyantal.springblog.gateway.service.dto.UserDTO;
import hu.nagyantal.springblog.gateway.util.PaginationUtil;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PublicUserController {

    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
            Arrays.asList("id", "login", "firstName", "lastName", "email", "activated", "langKey")
    );

    private final UserService userService;

    public PublicUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public Mono<ResponseEntity<Flux<UserDTO>>> getAllPublicUsers(ServerHttpRequest request, Pageable pageable) {

        if (!onlyContainsAllowedProperties(pageable)) {
            return Mono.just(ResponseEntity.badRequest().build());
        }

        return userService
                .countManagedUsers()
                .map(total -> new PageImpl<>(new ArrayList<>(), pageable, total))
                .map(page -> PaginationUtil.generatePaginationHttpHeaders(UriComponentsBuilder.fromHttpRequest(request), page))
                .map(headers -> ResponseEntity.ok().headers(headers).body(userService.getAllPublicUsers(pageable)));

    }



    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }



    @GetMapping("/authorities")
    public Mono<List<String>> getAuthorities() {
        return userService.getAuthorities().collectList();
    }
}
