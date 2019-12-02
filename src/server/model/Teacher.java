package server.model;

public class Teacher {
    private String name;
    private String password;
    private String siape;

    public Teacher(String name, String password, String siape) {
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

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + "\"" +
                ",\n \"password\":\"" + password + "\"" +
                ", \n\"siape\":\"" + siape + "\"\n}";
    }
}
