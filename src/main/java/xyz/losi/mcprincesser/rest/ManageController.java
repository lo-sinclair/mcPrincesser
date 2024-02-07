package xyz.losi.mcprincesser.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.losi.mcprincesser.config.McServerYmlProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
public class ManageController {

    @Autowired
    private McServerYmlProperties mcServerProperties;

    @GetMapping("status")
    public String serverStatus() throws IOException {



        String[] args = {"getdir1"};
        String shManage = "bash/manage.sh";

        Process process = Runtime.getRuntime().exec(new String[]{shManage, "getdir"});
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = "";
        System.out.println("SSSSSSSSS");

        System.out.println(mcServerProperties.getServers());

        String line;
        while ((line = reader.readLine()) != null ) {
            output += line + "\n";
        }

        if(output.isEmpty()) System.out.println("EEEEEEE");

        //return mcServerProperties.getServers().toString();
        //return "aaa";
        return output;
    }

}
