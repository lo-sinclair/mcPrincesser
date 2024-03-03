package xyz.losi.mcprincesser.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.losi.mcprincesser.event.EventHandler;
import xyz.losi.mcprincesser.manager.GameServerManager;
import xyz.losi.mcprincesser.model.dto.GameServer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
//@PreAuthorize("hasAnyAuthority('SERVER')")
public class ManageController {

    @Autowired
    private GameServerManager gameServerManager;

    public volatile List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    private final EventHandler eventHandler;


    @GetMapping("status")
    public GameServer serverStatus() throws IOException {
        return new GameServer("aa", "bb", "cc");
        //return gameServerManager.getActiveServer();
    }


    @GetMapping("register-client")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public SseEmitter sseEmitter() {
        return eventHandler.registerClient();
    }


    @GetMapping("event/update")
    public void updateEvent() {
        System.out.println("UUUUPPP");
        eventHandler.updateServers();
    }

    @GetMapping("start")
    public void start(@RequestParam String serverName) throws IOException {
        GameServer gameServer = gameServerManager.getGameServerByName(serverName);
        gameServerManager.startServer(gameServer);
    }

/*
    @GetMapping(path = "events/{text}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  void sendEventToSubscribers(@PathVariable String text) {
        emitters.forEach(e -> {
            try {
                var event = SseEmitter.event()
                        .data(text, MediaType.APPLICATION_JSON)
                        .id(String.valueOf(UUID.randomUUID()))
                        .name("TEST-EVENT");
                e.send(event);
            } catch (IOException ex) {
                emitters.remove(e);
                throw  new RuntimeException(ex);
            }
        });
    }
*/

}
