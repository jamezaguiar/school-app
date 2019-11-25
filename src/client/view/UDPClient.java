package client.view;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    private DatagramSocket clientSocket = new DatagramSocket();
    private InetAddress IPAddress = InetAddress.getByName("localhost");
    private int port = 3333;


    public UDPClient() throws SocketException, UnknownHostException {
    }

    public void sendRequest(String message) throws IOException {
        byte[] sendData;
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);
    }

    public String getResponse() throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        return new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
    }
}

