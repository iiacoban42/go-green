package server.security;

import database.manager.UserManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("trying to authenticate");

        database.entity.User user = UserManager.getUser(username);
        if (user == null) {
            System.out.println("username not found");
            throw new UsernameNotFoundException(username);
        }

        // Not using roles at the moment, so use empty list.
        // We also need to decode the password here once we are actually hashing it.
        return new User(user.getUsername(), user.getHashPassword(), Collections.emptyList());
    }
}
