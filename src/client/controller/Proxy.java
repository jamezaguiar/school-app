package client.controller;

import client.model.Information;
import client.model.Message;
import client.view.UDPClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Proxy {
    private int requestId = 0;
    private UDPClient udpClient = new UDPClient();

    public Proxy() throws SocketException, UnknownHostException {
    }

    public String doOperation(String objectReference, String methodId, String arguments) throws IOException {
        String data = packJSON(objectReference, methodId, arguments);
        udpClient.sendRequest(data);
        Message reply = unpackJSON(udpClient.getResponse());
        return reply.getArguments();
    }

    public String createStudent(String name, String password, String matriculation) throws IOException {
        Information information = new Information(name, password, matriculation);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "createStudent", args);
        information = new Gson().fromJson(reply, Information.class);
        return information.getReply();
    }

    public String createTeacher(String name, String password, String siape) throws IOException {
        Information information = new Information(name, password, siape);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "createTeacher", args);
        information = new Gson().fromJson(reply, Information.class);
        return information.getReply();
    }

    private String packJSON(String objectReference, String methodId, String arguments) {
        return new Gson().toJson(new Message(0, 0, objectReference, methodId, arguments));
    }

    private Message unpackJSON(String message) {
        return new Gson().fromJson(message, (Type) Message.class);
    }


}
