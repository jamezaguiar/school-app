package server.view;

import com.google.gson.Gson;
import server.controller.Dispatcher;
import server.model.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

public class UDPServer extends Thread {
    private static HashMap<String, String> responses = new HashMap<String, String>();
    private DatagramPacket receivePacket;
    private DatagramPacket sendPacket;
    private DatagramSocket serverSocket;
    private Dispatcher dispatcher = new Dispatcher();


    public UDPServer(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.start();
    }

    public static void main(String[] args) throws IOException {


        try {
            DatagramSocket serverSocket = new DatagramSocket(3333);
            UDPServer c = new UDPServer(serverSocket);

        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }


    }

    @Override
    public void run() {
        try {
            while (true) {
                Message request = unpackRequest(getRequest());
                String key = receivePacket.getAddress() + "" + receivePacket.getPort() + "" + request.getRequestId();
                if (responses.containsKey(key)) {
                    sendResponse(responses.get(key));
                } else {
                    System.out.println("Request from " + receivePacket.getAddress() + ": " + request);
                    String result = dispatcher.invoke(request);
                    String response = packResponse(result, request.getRequestId());
                    responses.clear();
                    responses.put(key, response);
                    sendResponse(response);
                }
            }
        } catch (NoSuchMethodException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getRequest() throws IOException {
        byte[] receiveData = new byte[1024];
        this.receivePacket = new DatagramPacket(receiveData, receiveData.length);
        this.serverSocket.receive(receivePacket);
        return new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
    }

    public void sendResponse(String reply) throws IOException {
        byte[] sendData;
        sendData = reply.getBytes();
        this.sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
        this.serverSocket.send(sendPacket);
    }

    public Message unpackRequest(String data) {
        return new Gson().fromJson(data, Message.class);
    }

    public String packResponse(String result, int requestId) {
        Message replyMessage = new Message(1, requestId, null, null, result);
        return new Gson().toJson(replyMessage);
    }
}