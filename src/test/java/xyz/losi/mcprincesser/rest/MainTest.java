package xyz.losi.mcprincesser.rest;

import java.io.*;
import java.net.URISyntaxException;

class MainTest {

    public void saveResource() throws FileNotFoundException, URISyntaxException {
        String resourcePath = "bash/manage.sh";
        String[] split = resourcePath.split("/");
        for(String st : split) {
            System.out.println(st);
        }
        File dir = new File("a");
        dir.mkdir();
        File dir2 = new File("a/b");
        dir2.mkdir();
        /*File outDir = new File("bash");

        File outFile = new File(outDir, "manage.sh");

        if(outFile.exists()) {
            return;
        }
        if(!outDir.exists()) {
            outDir.mkdir();
        }

        InputStream resource = getClass().getResourceAsStream("/bash/manage.sh");

        System.out.println(resource);


        if(resource == null)
            throw new FileNotFoundException("resource not found");

        try(InputStream in = resource;
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {

            byte[] buffer = new byte[1024 * 4];
            int length;
            while ((length = in.read(buffer)) >= 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


    }


    /*public void saveResource(@NotNull String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }

        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = getResource(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + file);
        }

        File outFile = new File(dataFolder, resourcePath);
        int lastIndex = resourcePath.lastIndexOf('/');
        File outDir = new File(dataFolder, resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } else {
                logger.log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
        }
    }*/


   /* public InputStream getResource(@NotNull String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        try {
            URL url = getClassLoader().getResource(filename);

            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }*/

}