package client.view;

import client.controller.Proxy;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        Proxy proxy = new Proxy();

        proxy.createStudent("Jamerson","123123123",418866);
    }
}
