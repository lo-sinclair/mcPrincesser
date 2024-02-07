package xyz.losi.mcprincesser.utils;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import xyz.losi.mcprincesser.rest.ManageController;

import java.io.*;

public class FileUtils {

    public static boolean saveResource(@NotNull String resourcePath, boolean replace) throws FileNotFoundException {

        InputStream in = FileUtils.class.getResourceAsStream(resourcePath);

        if(in == null) {
            throw new FileNotFoundException("resource not found");
        }

        String filePath = resourcePath.substring(0, 1).equals("/") ? resourcePath.substring(1) : resourcePath;
        String[] pathArray = filePath.split("/");
        for(int i=0; i<pathArray.length-1; i++) {
            File dir = new File(pathArray[i]);
        }
        File outFile = new File(filePath);

        if(!replace && outFile.exists()) {
            return false;
        }

        for(int i = 0; i < pathArray.length-1; i++) {
            File dir = new File(pathArray[i]);
            if(!dir.exists()) {
                dir.mkdir();
            }
        }

        try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {
            byte[] buffer = new byte[1024*4];
            int len;
            while((len = in.read(buffer)) >= 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

}
