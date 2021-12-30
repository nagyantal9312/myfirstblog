package hu.nagyantal.springblog.gateway.web.rest;

import hu.nagyantal.springblog.gateway.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PublicUserController {

    private final UserService userService;

    public PublicUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/authorities")
    public Mono<List<String>> getAuthorities() {
        return userService.getAuthorities().collectList();
    }
}
