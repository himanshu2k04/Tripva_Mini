package in.tripva.assignment.user.userService;

import in.tripva.assignment.user.userDTO.LoginRequest;
import in.tripva.assignment.user.userDTO.LoginResponse;
import in.tripva.assignment.user.userDTO.UserDTO;
import in.tripva.assignment.user.userEntity.UserEntity;
import in.tripva.assignment.user.userRepo.UserRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data

public class UserService implements UserDetailsService {

    private UserRepo userrepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    public UserService(UserRepo userrepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userrepo = userrepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserDTO createUser(UserEntity user) {

        String email = user.getEmail();
        if (userrepo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity createdUser = userrepo.save(user);
        createdUser.setPassword(passwordEncoder.encode(createdUser.getPassword()));

        return modelMapper.map(createdUser, UserDTO.class);
    }

    public LoginResponse login(LoginRequest loginRequest) {

        UserEntity getUser = userrepo.findByEmail(loginRequest.getEmail());
        if(getUser==null){
            throw new RuntimeException("user not found");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),getUser.getPassword()))
        {
            throw new RuntimeException("invalid password");
        }

        String token = jwtService.generateToken(loginRequest.getEmail());

        return new LoginResponse(token);





    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userrepo.findByEmail(username);
    }
}
