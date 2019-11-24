package client.model;

public class Information {
    private String name;
    private String password;
    private String matriculationOrSiape;
    private String reply;

    public Information() {
    }

    public Information(String reply) {
        this.reply = reply;
    }

    public Information(String name, String password, String matriculationOrSiape) {
        this.name = name;
        this.password = password;
        this.matriculationOrSiape = matriculationOrSiape;
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

    public String getMatriculationOrSiape() {
        return matriculationOrSiape;
    }

    public void setMatriculationOrSiape(String matriculationOrSiape) {
        this.matriculationOrSiape = matriculationOrSiape;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Information{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", matriculationOrSiape=" + matriculationOrSiape +
                ", reply='" + reply + '\'' +
                '}';
    }
}
