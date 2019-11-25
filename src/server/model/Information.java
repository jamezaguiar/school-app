package server.model;

public class Information {
    private String name;
    private String password;
    private String matriculationOrSiape;

    public Information() {
    }

    public Information(String matriculationOrSiape) {
        this.matriculationOrSiape = matriculationOrSiape;
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



    @Override
    public String toString() {
        return "Information{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", matriculationOrSiape=" + matriculationOrSiape +
                '}';
    }
}
