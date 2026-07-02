package in.tripva.assignment.user.userService;

import in.tripva.assignment.user.userDTO.LoginRequest;
import in.tripva.assignment.user.userDTO.LoginResponse;
import in.tripva.assignment.user.userEntity.userEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Data
public class JwtService  {

    @Value("${spring.security.jwt.secret-key}")
    private String secretkey;

    @Value("${spring.security.expiration-time}")
    private long expirationTime;

    private SecretKey getkey(){
        return Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email){
       return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(getkey())
                .compact();
    }

    public String extractUsername(String token){
        return Jwts
                .parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Boolean isTokenValid(String token,String email){
        return extractUsername(token).equals(email);
    }

}
