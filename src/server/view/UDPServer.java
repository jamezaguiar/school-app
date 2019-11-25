package server.view;

import com.google.gson.Gson;
import server.controller.Dispatcher;
import server.model.Message;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

public class UDPServer extends Thread {
    private DatagramPacket receivePacket;
    private DatagramPacket sendPacket;
    private DatagramSocket serverSocket;
    private Dispatcher dispatcher = new Dispatcher();


    public UDPServer(DatagramSocket serverSocket) throws SocketException {
        this.serverSocket = serverSocket;
        this.start();
    }

    @Override
    public void run() {

        try {
            Message requisicao = desempacotaRequisicao(getRequest());
            System.out.println(requisicao);
            String resultado = dispatcher.invoke(requisicao);
            sendResponse(empacotaResposta(resultado, requisicao.getRequestId()));

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
        byte[] sendData = new byte[1024];
        sendData = reply.getBytes();
        this.sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort() );
        this.serverSocket.send(sendPacket);
    }

    public Message desempacotaRequisicao(String data) {
        Gson objetogson = new Gson();
        return objetogson.fromJson(data, Message.class);
    }

    public String empacotaResposta(String resultado, int requestId) {
        Gson objetogson = new Gson();
        Message mensagemResposta = new Message(1, requestId, null, null, resultado);

        return objetogson.toJson(mensagemResposta);
    }

    public static void main(String[] args) {
        try {
                DatagramSocket serverSocket = new DatagramSocket(3333);
                UDPServer c = new UDPServer(serverSocket);

        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}