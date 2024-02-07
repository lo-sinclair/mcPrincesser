package xyz.losi.mcprincesser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.losi.mcprincesser.utils.FileUtils;

@Component
public class Init implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        System.out.println("\u001B[32mmcPrincesser init!\033[39m");



    }
}
