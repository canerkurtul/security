package com.caner.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired JwtService jwtService;

    @Autowired(required = false) private AuthenticationManager authenticationManager;
    @Autowired private MyUserDetailsService userDetailsService;

    @GetMapping(path = "/authenticate" )
    public ResponseEntity<?> authenticate() throws Exception {
        try {
            if (authenticationManager != null)
                authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(userDetailsService.username, userDetailsService.password));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDetailsService.username);

        final String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

}
