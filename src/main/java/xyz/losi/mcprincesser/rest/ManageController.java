package xyz.losi.mcprincesser.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.losi.mcprincesser.manager.GameServerManager;
import xyz.losi.mcprincesser.model.dto.GameServer;

import java.io.IOException;

@RestController
@RequestMapping("/api")
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ManageController {

    @Autowired
    private GameServerManager gameServerManager;

    @GetMapping("status")
    public GameServer serverStatus() throws IOException {
        return new GameServer("aa", "bb", "cc");
        //return gameServerManager.getActiveServer();
    }

}
