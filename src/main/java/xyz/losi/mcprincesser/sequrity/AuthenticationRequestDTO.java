package xyz.losi.mcprincesser.sequrity;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String name;
    private String password;
}
