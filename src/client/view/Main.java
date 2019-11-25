package client.view;

import client.controller.Proxy;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Proxy proxy = new Proxy();

        System.out.println(proxy.createStudent("Jamerson", "123123123", "418866"));
        System.out.println(proxy.createStudent("Jamerson", "123123123", "418866"));
        System.out.println(proxy.createTeacher("JV", "LANSOABRABA", "23546"));
        System.out.println(proxy.createTeacher("JV", "LANSOABRABA", "23546"));
        System.out.println(proxy.readStudent("418861236"));
        System.out.println(proxy.readTeacher("23123546"));
        System.out.println(proxy.deleteStudent("1231234123", "418866"));
        System.out.println(proxy.deleteTeacher("LANSOA1BRABA","23546"));

    }
}
