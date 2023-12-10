package com.example.eComputer.controller;

import com.example.eComputer.auth.JwtUtil;
import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.domain.request.LoginRequest;
import com.example.eComputer.domain.request.SignupRequest;
import com.example.eComputer.domain.response.ErrorResponse;
import com.example.eComputer.domain.response.LoginResponse;
import com.example.eComputer.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody SignupRequest signupRequest) {
        UserEntity newUser = new UserEntity();
        newUser.setName(signupRequest.getName());
        newUser.setEmail(signupRequest.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());

        newUser.setPassword(hashedPassword);
        boolean isUserCreated = userService.save(newUser);
        if (isUserCreated) {
            return ResponseEntity.ok("User registered successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user.");
        }
    }

    @GetMapping
    public List<UserEntity> getAllStudents() {
        return userService.getAllUsers();
    }
    @PostMapping("addAdmin/{id}")
    public ResponseEntity addAdmin(@PathVariable Long id){
        try{
            UserEntity user = userService.getUserById(id);
            userService.addAdmin(user);
            return ResponseEntity.ok("Admin role added");
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            UserEntity user = new UserEntity();
            user.setEmail(email);
            String token = jwtUtil.createToken(user);
            LoginResponse loginRes = new LoginResponse(email,token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
