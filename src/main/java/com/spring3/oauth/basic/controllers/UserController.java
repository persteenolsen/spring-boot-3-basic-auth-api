package com.spring3.oauth.basic.controllers;

import com.spring3.oauth.basic.dtos.*;

import com.spring3.oauth.basic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private  AuthenticationManager authenticationManager;
    
   
   // Public Access - Take a look in SecurityConfig.java
   @GetMapping("/hello")
   public String hello() {
   try {
       return "01-09-2025 - Hello from a public endpoint...";
   } catch (Exception e){
       throw new RuntimeException(e);
   }
  }
    
    // Note: Need to have public access in security config and have a route <> login
    // Public Access
     @PostMapping("/auth")
    public BasicResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        
       // Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("user", "persteen1967"));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
       
        // Note: Successfull Login when user = user 
        // Not successfull Login when User <> user
        String clientUserName = authRequestDTO.getUsername();
        String authUserName = authentication.getName();
        boolean auth = false;

        // true if the User type a username = the username in the db
        if( clientUserName.equals(authUserName))
            auth = true;

        // true if the User type a username = the username in the db and the User is Authenticated
        if(auth && authentication.isAuthenticated()){
           
           return BasicResponseDTO.builder()
                  
                   //.userName( "clientUserName: " + clientUserName + " AuthUserName: " + authUserName )
                   // Sending username to client for a welcome greeting when logged in
                   .userName( clientUserName )
                   .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
            
        }
    }
    
    // Public Access
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/save")
    public ResponseEntity saveUser(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.saveUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
       
    // It is protected by Auth - Take a look in SecurityConfig.java
    @GetMapping("/ping")
    public String ping() {
    try {
        return "Welcome to a protected ping endpoint";
    } catch (Exception e){
        throw new RuntimeException(e);
    }
   }
    
    // It is protected by Auth and by Role
    // Note: If the User have the role USER instead of ADMIN there will be a 403 forbidden
    @SuppressWarnings("rawtypes")
    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        try {
            List<UserResponse> userResponses = userService.getAllUser();
            return ResponseEntity.ok(userResponses);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    // It is protected by Auth
    @PostMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile() {
        try {
        UserResponse userResponse = userService.getUser();
        return ResponseEntity.ok().body(userResponse);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    

}
