package hh.sof3.moviemania.service;

import hh.sof3.moviemania.domain.AppUser;
import hh.sof3.moviemania.domain.AppUserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
