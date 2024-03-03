package xyz.losi.mcprincesser.manager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import xyz.losi.mcprincesser.config.McServerYmlProperties;
import xyz.losi.mcprincesser.model.dto.GameServer;
import xyz.losi.mcprincesser.utils.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GameServerManager {
    @Autowired
    private McServerYmlProperties mcServerProperties;
    private Map<String, GameServer> gameServerMap = new HashMap<>();
    @Nullable
    private GameServer activeServer;
    private boolean lock;

    private static int counter = 1;


    private final String FUNCTIONS_SH = "bash/functions.sh";
    private final String START_SH = "bash/start.sh";

    public GameServerManager(McServerYmlProperties mcServerProperties) {
        System.out.println("CONSTR");
        this.gameServerMap = mcServerProperties.getServers().stream().collect(Collectors.toMap(k -> k.getName(), v -> v));
        System.out.println(gameServerMap);
        updateActiveServer();

    }

    public Map<String, GameServer> findAllServers(){
        return mcServerProperties.getServers().stream().collect(Collectors.toMap(k -> k.getName(), v -> v));
    }


    public void updateServers(boolean lock){
        setLock(this.lock);
        if (!this.lock) {
            updateActiveServer();
        }
    }
    public void updateServers(){
        updateServers(false);
    }


    public GameServer findServerByDir(String dirPath){
        for (GameServer server : gameServerMap.values()) {
            if (server.getDir().equals(dirPath)) {
                return server;
            }
        }
        return null;
    }


    public void updateActiveServer() {
        System.out.println(Color.GREEN + "UP_ACTIVE" + counter +Color.RESET); counter++;
        List<String> getdir = execFunction("getdir");
        System.out.println("GETDIR");
        System.out.println(getdir);
        if (!getdir.isEmpty()) {
            String dirPath = getdir.get(0);
            GameServer newActiveServer = findServerByDir(dirPath);
            System.out.println("NEW_AC");
            System.out.println(newActiveServer);
            System.out.println("ACT");
            System.out.println(getActiveServer());
            System.out.println("MMMMMM");
            System.out.println(getGameServerMap());
            if (newActiveServer != null) {
                System.out.println(newActiveServer);
                if(activeServer != null)activeServer.setActive(false);
                newActiveServer.setActive(true);
                System.out.println("ACT");
                System.out.println(getActiveServer());
                System.out.println("NEW_AC_BEFORE_SET_ACTIVE");
                setActiveServer(newActiveServer);
                System.out.println("===============");
                System.out.println("ACT");
                System.out.println(getActiveServer());
                System.out.println("MMMMMM");
                System.out.println(getGameServerMap());
                return;
            }
        }
        setActiveServer(null);
    }


    public void startServer(GameServer server) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(START_SH, server.getDir());
        Process process = processBuilder.start();
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = output.readLine()) != null) {
           log.info(line);
        }
        process.destroy();
    }


    public List<String> execFunction(String fooName)  {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{FUNCTIONS_SH, fooName});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        List<String> output = new ArrayList<>();
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            output.add(line);
        }
        System.out.println(output);
        return output;
    }

    public List<GameServer> getGameServerList(){
        return gameServerMap.values().stream().collect(Collectors.toList());
    }

    public Map<String, GameServer> getGameServerMap() {
        return gameServerMap;
    }

    public GameServer getActiveServer() {
        return activeServer;
    }

    private void setActiveServer(GameServer activeServer) {
        this.activeServer = activeServer;
    }

    public GameServer getGameServerByName(String name) {
        return gameServerMap.get(name);
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
