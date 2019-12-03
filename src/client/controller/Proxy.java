package client.controller;

import client.model.*;
import client.view.UDPClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

public class Proxy {
    private static int requestId = 0;
    private UDPClient udpClient = new UDPClient();

    public Proxy() throws SocketException, UnknownHostException {
    }

    public String doOperation(String objectReference, String methodId, String arguments) throws IOException {
        String data = packJSON(objectReference, methodId, arguments);
        requestId++;
        Message reply = null;
        while (reply == null) {
            udpClient.sendRequest(data);
            udpClient.getClientSocket().setSoTimeout(1000);
            try {
                reply = unpackJSON(udpClient.getResponse());
            } catch (TimeoutException e) {
                udpClient.sendRequest(data);
            }
            assert reply != null;
            if (reply.getRequestId() == requestId) {
                return reply.getArguments();
            } else {
                continue;
            }
        }
        return reply.getArguments();
    }

    public Student createStudent(String name, String password, String matriculation) throws Exception {
        Information information = new Information(name, password, matriculation);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "createStudent", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        String json = serverReply.getReply();
        try {
            return new Gson().fromJson(json, Student.class);
        } catch (Exception e) {
            throw new Exception("Erro de requisição ao banco de dados. Verifique os dados inseridos e tente novamente.");
        }
    }

    public Student readStudent(String matriculation) throws Exception {
        Information information = new Information(matriculation);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "readStudent", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        String json = serverReply.getReply();
        try {
            return new Gson().fromJson(json, Student.class);
        } catch (Exception e) {
            throw new Exception("Erro de requisição ao banco de dados. Verifique os dados inseridos e tente novamente.");
        }
    }

    public Student[] listStudents() throws IOException {
        Information information = new Information();
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "listStudents", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        String json = serverReply.getReply();
        return new Gson().fromJson(json, Student[].class);
    }


    public String deleteStudent(String password, String matriculation) throws IOException {
        Information information = new Information(password, matriculation);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "deleteStudent", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        return serverReply.getReply();
    }

    public Teacher createTeacher(String name, String password, String siape) throws Exception {
        Information information = new Information(name, password, siape);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "createTeacher", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        String json = serverReply.getReply();
        try {
            return new Gson().fromJson(json, Teacher.class);
        } catch (Exception e) {
            throw new Exception("Erro de requisição ao banco de dados. Verifique os dados inseridos e tente novamente.");
        }
    }

    public Teacher readTeacher(String siape) throws Exception {
        Information information = new Information(siape);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "readTeacher", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        String json = serverReply.getReply();
        try {
            return new Gson().fromJson(json, Teacher.class);
        } catch (Exception e) {
            throw new Exception("Erro de requisição ao banco de dados. Verifique os dados inseridos e tente novamente.");
        }
    }

    public Teacher[] listTeachers() throws IOException {
        Information information = new Information();
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "listTeachers", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        String json = serverReply.getReply();
        return new Gson().fromJson(json, Teacher[].class);
    }

    public String deleteTeacher(String password, String siape) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Information information = new Information(password, siape);
        String args = new Gson().toJson(information);
        String reply = doOperation("Servant", "deleteTeacher", args);
        Reply serverReply = new Gson().fromJson(reply, Reply.class);
        return serverReply.getReply();
    }

    private String packJSON(String objectReference, String methodId, String arguments) {
        return new Gson().toJson(new Message(0, requestId, objectReference, methodId, arguments));
    }

    private Message unpackJSON(String message) {
        return new Gson().fromJson(message, (Type) Message.class);
    }


}
