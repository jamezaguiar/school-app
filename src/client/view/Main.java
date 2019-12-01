package client.view;

import client.controller.Proxy;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Proxy proxy = new Proxy();
        showMenu();


        System.out.println(proxy.createStudent("Jamerson", "123123123", "4188626"));
        /*System.out.println(proxy.createStudent("Jamerson", "123123123", "418866"));
        System.out.println(proxy.createTeacher("JV", "LANSOABRABA", "23546"));
        System.out.println(proxy.createTeacher("JV", "LANSOABRABA", "23546"));
        System.out.println(proxy.readStudent("418861236"));
        System.out.println(proxy.readTeacher("23123546"));
        System.out.println(proxy.deleteStudent("1231234123", "418866"));
        System.out.println(proxy.deleteTeacher("LANSOA1BRABA","23546"));

*/



    }

    public static void showMenu(){
        String leftAlignFormat = "| %-15s | %-4d |%n";
        //          System.out.format(leftAlignFormat, "some data" + i, i * i);

         System.out.format("+------------------------SCHOOL MANAGEMENT-----------------------+%n");

        System.out.format("+-----------------+------+%n");
        System.out.format("| Select the desired option    | ID   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < 5; i++) {
        }
        System.out.format("+-----------------+------+%n");
    }
}
