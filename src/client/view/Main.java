package client.view;

import client.controller.Proxy;
import client.model.Student;
import com.google.gson.Gson;
import de.vandermeer.asciitable.*;
import de.vandermeer.skb.interfaces.render.HasText;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        showMenu();


        //System.out.println(proxy.createStudent("Jamerson", "123123123", "4188626"));
        /*System.out.println(proxy.createStudent("Jamerson", "123123123", "418866"));
        System.out.println(proxy.createTeacher("JV", "LANSOABRABA", "23546"));
        System.out.println(proxy.readTeacher("23123546"));
        System.out.println(proxy.deleteStudent("1231234123", "418866"));
        System.out.println(proxy.deleteTeacher("LANSOA1BRABA","23546"));

*/



    }

    public static void showMenu() throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        showStudent("418082");
    }

    public static void showStudent(String matriculation) throws IllegalAccessException, IOException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        AsciiTable tb = new AsciiTable();
        AsciiTable hey = new AsciiTable();
        ST st = new ST("Dados do Aluno Específico");
        Proxy proxy = new Proxy();
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
}
