package xyz.losi.mcprincesser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.losi.mcprincesser.domain.Role;
import xyz.losi.mcprincesser.domain.User;
import xyz.losi.mcprincesser.repository.UserRepo;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Not found: " + username);
        }
    }

    public boolean addUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null ) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if( !StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
              "Hello, %s \n" +
              "Welcome to MCPrincesser! Please, visit next link: http://localhost:8080/activate/%s",
              user.getUsername(),
              user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if(user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }
}
