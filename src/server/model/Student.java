package server.model;

public class Student {
    private String name;
    private String password;
    private String matriculation;

    public Student(String name, String password, String matriculation) {
        this.name = name;
        this.password = password;
        this.matriculation = matriculation;
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

    public String getMatriculation() {
        return matriculation;
    }

    public void setMatriculation(String matriculation) {
        this.matriculation = matriculation;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", matriculation=" + matriculation +
                '}';
    }
}
