package in.tripva.assignment.user.userDTO;

import in.tripva.assignment.user.userEntity.Role;
import lombok.Data;

@Data
public class userDTO {

    private Long userId;
    private String name;
    private String email;
    private Role role;

}
