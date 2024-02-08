package xyz.losi.mcprincesser.model.dto;

public class GameServer {
    private String name;

    private String title;

    private String dir;

    public String getName() {
        return name;
    }

    public GameServer() {

    }

    public GameServer(String name, String title, String dir) {
        this.name = name;
        this.title = title;
        this.dir = dir;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "ServerProp{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", dir='" + dir + '\'' +
                '}';
    }
}
