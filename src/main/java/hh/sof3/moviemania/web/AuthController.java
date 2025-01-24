package hh.sof3.moviemania.web;

import hh.sof3.moviemania.domain.AppUser;
import hh.sof3.moviemania.security.JwtTokenUtil;
import hh.sof3.moviemania.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
            UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser appUser) {
        try {
            // Authenticate user credentials
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));

            // Generate JWT token upon successful authentication
            String jwt = jwtTokenUtil.generateToken(authentication.getName());
            return ResponseEntity.ok(jwt);

        } catch (AuthenticationException e) {
            // Handle invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser appUser) {
        // Check if the user already exists
        if (userService.findByUsername(appUser.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken!");
        }

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPasswordHash(hashedPassword);

        // Save the new user
        userService.saveUser(appUser);
        return ResponseEntity.ok("User registered successfully!");
    }
}
