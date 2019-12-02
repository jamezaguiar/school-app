package client.view;

import client.controller.Proxy;
import client.model.Student;
import client.model.Teacher;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    static Proxy proxy;

    static {
        try {
            proxy = new Proxy();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Main() throws SocketException, UnknownHostException {
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        showMenu();

    }

    public static void showMenu() throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {

        Scanner in = new Scanner(System.in);
        int opc = 0;
        while (true) {
            renderTable();
            System.out.println("Digite sua opção: ");
            opc = Integer.parseInt(in.nextLine());

            switch (opc) {
                case 1:
                    System.out.println("Digite a matrícula do estudante: ");
                    showStudent(in.nextLine());
                    in.nextLine();
                    break;
                case 2:
                    listStudents();
                    in.nextLine();
                    break;
                case 3:
                    createStudent();
                    in.nextLine();
                    break;
                case 4:
                    deleteStudent();
                    in.nextLine();
                    break;
                case 5:
                    System.out.println("Digite o SIAPE do professor: ");
                    showTeacher(in.nextLine());
                    in.nextLine();
                    break;
                case 6:
                    listTeachers();
                    in.nextLine();
                    break;
                case 7:
                    createTeacher();
                    in.nextLine();
                    break;
                case 8:
                    deleteTeacher();
                    in.nextLine();
                    break;
                case 9:
                    return;
                default:


            }

        }
    }

    public static void renderTable() {
        AsciiTable tb = new AsciiTable();
        AsciiTable hey = new AsciiTable();
        ST st = new ST("Sistema Escolar - School App");
        tb.addRule();
        tb.addRow(st);
        tb.addRule();
        tb.setTextAlignment(TextAlignment.CENTER);
        tb.addRule();
        hey.addRow("Exibir aluno pela matrícula", "1");
        hey.addRule();
        hey.addRow("Listar alunos", "2");
        hey.addRule();
        hey.addRow("Cadastrar aluno", "3");
        hey.addRule();
        hey.addRow("Deletar aluno", "4");
        hey.addRule();
        hey.addRow("Listar professor pelo SIAPE", "5");
        hey.addRule();
        hey.addRow("Listar professores", "6");
        hey.addRule();
        hey.addRow("Cadastrar professor", "7");
        hey.addRule();
        hey.addRow("Deletar professor", "8");
        hey.addRule();
        hey.addRow("Sair", "9");
        hey.addRule();
        hey.setTextAlignment(TextAlignment.CENTER);
        System.out.println(tb.render());
        System.out.println(hey.render());
    }

    public static void showStudent(String matriculation) throws IllegalAccessException, IOException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        AsciiTable tb = new AsciiTable();
        AsciiTable hey = new AsciiTable();
        ST st = new ST("Dados do Aluno Específico");
        Student student = proxy.readStudent(matriculation);

        tb.addRule();
        tb.addRow(st);
        tb.addRule();
        tb.addRule();
        hey.addRow("Nome", student.getName());
        hey.addRule();
        hey.addRow("Matrícula", student.getMatriculation());
        hey.addRule();

        System.out.println(tb.render());
        System.out.println(hey.render());
    }

    public static void listStudents() throws IOException {
        AsciiTable hey = new AsciiTable();
        Student[] students = proxy.listStudents();
        for (Student student : students) {
            hey.addRule();
            hey.addRow("Nome", student.getName());
            hey.addRule();
            hey.addRow("Matrícula", student.getMatriculation());
            hey.addRule();
        }

        System.out.println(hey.render());
    }

    public static void createStudent() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o nome do estudante: ");
        String name = in.nextLine();
        System.out.println("Digite a senha do estudante: ");
        String password = in.nextLine();
        System.out.println("Digite a matrícula do estudante: ");
        String matriculation = in.nextLine();
        Student student = proxy.createStudent(name, password, matriculation);
        AsciiTable tb = new AsciiTable();
        AsciiTable hey = new AsciiTable();
        ST st = new ST("Dados do estudante criado");

        tb.addRule();
        tb.addRow(st);
        tb.addRule();
        tb.addRule();
        hey.addRow("Nome", student.getName());
        hey.addRule();
        hey.addRow("Matrícula", student.getMatriculation());
        hey.addRule();

        System.out.println(tb.render());
        System.out.println(hey.render());
    }

    public static void deleteStudent() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite a matrícula do estudante: ");
        String matriculation = in.nextLine();
        System.out.println("Digite a senha do estudante: ");
        String password = in.nextLine();


        proxy.deleteStudent(password, matriculation);
        AsciiTable tb = new AsciiTable();
        ST st = new ST("Estudante deletado");

        tb.addRule();
        tb.addRow(st);
        tb.addRule();

        System.out.println(tb.render());
    }

    public static void showTeacher(String siape) throws IllegalAccessException, IOException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        AsciiTable tb = new AsciiTable();
        AsciiTable hey = new AsciiTable();
        ST st = new ST("Dados do professor");
        Teacher teacher = proxy.readTeacher(siape);

        tb.addRule();
        tb.addRow(st);
        tb.addRule();
        tb.addRule();
        hey.addRow("Nome", teacher.getName());
        hey.addRule();
        hey.addRow("SIAPE", teacher.getSiape());
        hey.addRule();

        System.out.println(tb.render());
        System.out.println(hey.render());
    }

    public static void listTeachers() throws IOException {
        AsciiTable hey = new AsciiTable();
        Teacher[] teachers = proxy.listTeachers();
        for (Teacher teacher : teachers) {
            hey.addRule();
            hey.addRow("Nome", teacher.getName());
            hey.addRule();
            hey.addRow("SIAPE", teacher.getSiape());
            hey.addRule();
        }

        System.out.println(hey.render());
    }

    public static void createTeacher() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o nome do professor: ");
        String name = in.nextLine();
        System.out.println("Digite a senha do professor: ");
        String password = in.nextLine();
        System.out.println("Digite o SIAPE do professor: ");
        String siape = in.nextLine();
        Teacher teacher = proxy.createTeacher(name, password, siape);
        AsciiTable tb = new AsciiTable();
        AsciiTable hey = new AsciiTable();
        ST st = new ST("Dados do professor criado");

        tb.addRule();
        tb.addRow(st);
        tb.addRule();
        tb.addRule();
        hey.addRow("Nome", teacher.getName());
        hey.addRule();
        hey.addRow("SIAPE", teacher.getSiape());
        hey.addRule();

        System.out.println(tb.render());
        System.out.println(hey.render());
    }

    public static void deleteTeacher() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o SIAPE do professor: ");
        String siape = in.nextLine();
        System.out.println("Digite a senha do professor: ");
        String password = in.nextLine();

        String response = proxy.deleteTeacher(password, siape);
        AsciiTable tb = new AsciiTable();
        ST st = new ST("Professor deletado");

        tb.addRule();
        tb.addRow(st);
        tb.addRule();

        System.out.println(tb.render());
    }

}
