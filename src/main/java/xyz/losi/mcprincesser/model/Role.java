package xyz.losi.mcprincesser.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, SERVER;

    @Override
    public String getAuthority() {
        return name();
    }
}
