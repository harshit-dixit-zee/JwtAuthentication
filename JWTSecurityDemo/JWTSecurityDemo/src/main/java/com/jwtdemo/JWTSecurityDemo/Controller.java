package com.jwtdemo.JWTSecurityDemo;

import com.jwtdemo.JWTSecurityDemo.models.AuthenticationResponse;
import com.jwtdemo.JWTSecurityDemo.models.AuthenticationRequest;
import com.jwtdemo.JWTSecurityDemo.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtUtils jwtUtils;

//    @Autowired
//    AuthenticationRequest authenticationRequest;
//    @Autowired
//    AuthenticationResponse authenticationResponse;

    @GetMapping("/welcome")
    public String Hello()
    {
        return "HELLO";
    }

    // But the problem is that i cannot call this endpoint/api before authenticating it first for one time
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
    {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch(BadCredentialsException e)
        {
            throw new Exception("Incorrect userName or Password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
