package client.view;

import client.controller.Proxy;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Proxy proxy = new Proxy();

        System.out.println(proxy.createStudent("Jamerson", "123123123", "418866"));
    }
}
