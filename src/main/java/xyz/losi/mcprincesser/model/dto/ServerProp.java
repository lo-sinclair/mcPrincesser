package xyz.losi.mcprincesser.model.dto;

public class ServerProp {
    private String name;

    private String dir;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", dir='" + dir + '\'' +
                '}';
    }
}
