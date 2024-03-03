package xyz.losi.mcprincesser.model.dto;

public class GameServer {
    private String name;

    private String title;

    private String dir;

    private boolean active;



    public GameServer() {

    }

    public GameServer(String name, String title, String dir) {
        this.name = name;
        this.title = title;
        this.dir = dir;
        this.active = false;
    }

    public GameServer(String name, String title, String dir, boolean active) {
        this.name = name;
        this.title = title;
        this.dir = dir;
        this.active = active;
    }

    public String getName() {
        return name;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", dir='" + dir + '\'' +
                ", active=" + active +
                '}';
    }
}
