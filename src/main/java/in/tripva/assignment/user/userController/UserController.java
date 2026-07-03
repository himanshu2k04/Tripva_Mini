package in.tripva.assignment.user.userController;

import in.tripva.assignment.user.userDTO.LoginRequest;
import in.tripva.assignment.user.userDTO.LoginResponse;
import in.tripva.assignment.user.userDTO.UserDTO;
import in.tripva.assignment.user.userEntity.UserEntity;
import in.tripva.assignment.user.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/create")
    public UserDTO registerUser(@RequestBody UserEntity user){

        return userservice.createUser(user);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){

        LoginResponse msg = userservice.login(loginRequest);

        return ResponseEntity.ok(msg);

    }

}
