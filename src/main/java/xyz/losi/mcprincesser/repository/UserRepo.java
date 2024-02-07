package xyz.losi.mcprincesser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.losi.mcprincesser.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
