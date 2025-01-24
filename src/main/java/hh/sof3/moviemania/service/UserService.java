package hh.sof3.moviemania.service;

import hh.sof3.moviemania.domain.AppUser;

public interface UserService {
    AppUser findByUsername(String username);

    AppUser saveUser(AppUser Appuser);
}
