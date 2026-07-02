package in.tripva.assignment.user.userController;

import in.tripva.assignment.user.userDTO.LoginRequest;
import in.tripva.assignment.user.userDTO.LoginResponse;
import in.tripva.assignment.user.userDTO.userDTO;
import in.tripva.assignment.user.userEntity.userEntity;
import in.tripva.assignment.user.userService.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class userController {

    private userService userservice;

    public userController(userService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/create")
    public userDTO registerUser(@RequestBody userEntity user){

        return userservice.createUser(user);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){

        LoginResponse msg = userservice.login(loginRequest);

        return ResponseEntity.ok(msg);

    }

}
