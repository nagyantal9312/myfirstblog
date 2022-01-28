package hu.nagyantal.springblog.gateway.service.dto;

import hu.nagyantal.springblog.gateway.domain.User;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

}
