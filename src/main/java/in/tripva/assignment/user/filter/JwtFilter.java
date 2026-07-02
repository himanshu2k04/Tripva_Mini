package in.tripva.assignment.user.filter;

import in.tripva.assignment.user.userService.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Data
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if(header==null||!header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = header.substring(7);

        System.out.println("Header : " + header);
        System.out.println("Token  : " + token);

       try{
           String email = jwtService.extractUsername(token);

           if(email != null && SecurityContextHolder.getContext().getAuthentication()==null) {
               UserDetails user = userDetailsService.loadUserByUsername(email);


               if (jwtService.isTokenValid(token, user.getUsername())) {

                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationFilter
                           = new UsernamePasswordAuthenticationToken(user,
                           null,
                           user.getAuthorities());

                   usernamePasswordAuthenticationFilter
                           .setDetails(
                                   new WebAuthenticationDetailsSource()
                                           .buildDetails(request));

                   SecurityContextHolder
                           .getContext()
                           .setAuthentication(usernamePasswordAuthenticationFilter);

               }
           }

       } catch (ExpiredJwtException e) {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.getWriter().write("JWT Token Expired");
           return;
       }
        filterChain.doFilter(request,response);

    }
}
