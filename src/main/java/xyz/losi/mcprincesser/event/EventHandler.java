package xyz.losi.mcprincesser.event;

import ch.qos.logback.classic.net.SimpleSSLSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.losi.mcprincesser.manager.GameServerManager;
import xyz.losi.mcprincesser.model.dto.GameServer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@Component
@Slf4j
public class EventHandler {
    @Autowired
    GameServerManager gameServerManager;

    private static final AtomicInteger ID_COUNTER = new AtomicInteger();
    public static final long DEFAULT_TIMEOUT = Long.MAX_VALUE;
    private final Set<SseEmitter> registeredEmitters = new HashSet<>();

    public SseEmitter registerClient() {
        var emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitter.onCompletion(() -> registeredEmitters.remove(emitter));
        emitter.onError((err) -> removeAndLogError(emitter));
        emitter.onTimeout(() -> removeAndLogError(emitter));
        registeredEmitters.add(emitter);
        sendData(emitter);
        log.info("New client registered {}", emitter.toString());
        return emitter;
    }

    public void startServer() {
        Set<SseEmitter> emitters = Set.copyOf(registeredEmitters);
        for (SseEmitter emitter: emitters) {
            sendData(emitter);
        }
    }

    private void removeAndLogError(SseEmitter emitter) {
        log.info("Error during communication. Unregister client {}", emitter.toString());
        registeredEmitters.remove(emitter);
    }


    private void sendData(SseEmitter sseEmitter) {

        try {
            log.info("Send status to client");
            int id = ID_COUNTER.incrementAndGet();

            SseEmitter.SseEventBuilder eventBuilder = event().name("status")
                    .id(String.valueOf(id))
                    .data(new StatusRequestDto(gameServerManager.findAllServers(), gameServerManager.getActiveServer()), MediaType.APPLICATION_JSON);
            sseEmitter.send(eventBuilder);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }


}
