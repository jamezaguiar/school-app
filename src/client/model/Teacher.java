package client.model;

import java.util.ArrayList;

public class Teacher {
    private String name;
    private String password;
    private long siape;

    public Teacher(String name, String password, long siape) {
        this.name = name;
        this.password = password;
        this.siape = siape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSiape() {
        return siape;
    }

    public void setSiape(long siape) {
        this.siape = siape;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", siape=" + siape +
                '}';
    }
}
