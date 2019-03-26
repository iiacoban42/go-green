package server.security;

import database.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("trying to authenticate");

        database.entity.User user = UserManager.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // Not using roles at the moment, so use empty list.
        // We also need to decode the password here once we are actually hashing it.
        return new User(user.getUsername(), user.getHashPassword(), Collections.emptyList());
    }
}
