package xyz.losi.mcprincesser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.losi.mcprincesser.utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {

        if(FileUtils.saveResource("/bash/functions.sh", true))
            Runtime.getRuntime().exec("chmod +x bash/functions.sh");
        if(FileUtils.saveResource("/bash/start.sh", true))
            Runtime.getRuntime().exec("chmod +x bash/start.sh");

        if(FileUtils.saveResource("/conf/mcserver.yml", false));

        SpringApplication.run(Application.class, args);
    }
}
