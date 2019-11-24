package server.view;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    DatagramSocket serverSocket = new DatagramSocket();

    public UDPServer() throws SocketException, UnknownHostException {
    }

    public static void main(String[] args) {

    }

    public String getRequest() throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        return new String(receivePacket.getData());
    }

    public void sendResponse(String reply) {
        byte[] sendData = new byte[1024];

    }
}