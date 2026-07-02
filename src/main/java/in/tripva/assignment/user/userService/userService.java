package in.tripva.assignment.user.userService;

import in.tripva.assignment.user.userDTO.LoginRequest;
import in.tripva.assignment.user.userDTO.LoginResponse;
import in.tripva.assignment.user.userDTO.userDTO;
import in.tripva.assignment.user.userEntity.userEntity;
import in.tripva.assignment.user.userRepo.userRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data

public class userService implements UserDetailsService {

    private userRepo userrepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    public userService(userRepo userrepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userrepo = userrepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public userDTO createUser(userEntity user) {

        String email = user.getEmail();
        if (userrepo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity createdUser = userrepo.save(user);
        createdUser.setPassword(passwordEncoder.encode(createdUser.getPassword()));

        return modelMapper.map(createdUser, userDTO.class);
    }

    public LoginResponse login(LoginRequest loginRequest) {

        userEntity getUser = userrepo.findByEmail(loginRequest.getEmail());
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
