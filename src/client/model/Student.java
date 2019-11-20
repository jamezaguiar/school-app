package client.model;

public class Student {
    private String name;
    private String password;
    private long matriculation;

    public Student(String name, String password, long matriculation) {
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

    public long getMatriculation() {
        return matriculation;
    }

    public void setMatriculation(long matriculation) {
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
