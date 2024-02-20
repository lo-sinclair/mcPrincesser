package xyz.losi.mcprincesser.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.losi.mcprincesser.config.McServerYmlProperties;
import xyz.losi.mcprincesser.model.dto.GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameServerManager {
    @Autowired
    private McServerYmlProperties mcServerProperties;

    private final String SH_FUNCTIONS = "bash/manage.sh";

    public List<GameServer> findAllServers(){
        return mcServerProperties.getServers();
    }

    public GameServer findServerByDir(String dirPath){
        List<GameServer> servers = mcServerProperties.getServers();
        System.out.println(dirPath);
        for (GameServer server : servers) {
            if (server.getDir().equals(dirPath)) {
                return server;
            }
        }
        return null;
    }

    public GameServer getActiveServer() {
        List<String> getdir = execFunction("getdir");
        if (!getdir.isEmpty()) {
            String dirPath = getdir.get(0);
            return findServerByDir(dirPath);
        }
        return null;
    }


    public List<String> execFunction(String fooName)  {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{SH_FUNCTIONS, fooName});
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

}
