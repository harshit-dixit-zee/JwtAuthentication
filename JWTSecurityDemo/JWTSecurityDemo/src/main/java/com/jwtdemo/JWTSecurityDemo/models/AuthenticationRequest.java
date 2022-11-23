package com.jwtdemo.JWTSecurityDemo.models;


// Step 1. To take the user id and password from the user and return jwt for that
// So, we have to make a class for taking an object as AuthenticationRequest
//and one more class as Authenticationresponse for returning the jwts.

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
