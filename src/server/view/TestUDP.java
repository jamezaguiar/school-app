package server.view;

import com.google.gson.Gson;
import server.controller.Dispatcher;
import server.model.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TestUDP {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;
        Dispatcher dispatcher = new Dispatcher();

        try{
            serverSocket = new DatagramSocket(3333);
            byte[] dataIn = new byte[1024];
            byte[] dataOut = new byte[1024];

            while (true){
                DatagramPacket request = new DatagramPacket(dataIn, dataIn.length);
                serverSocket.receive(request);
                System.out.println(new String(request.getData()));

                String result = dispatcher.invoke(desempacotaRequisicao(new String(request.getData(), request.getOffset(), request.getLength())));
                dataOut = empacotaResposta(result, 0).getBytes();

                DatagramPacket response = new DatagramPacket(dataOut, dataOut.length, request.getAddress(), request.getPort());
                serverSocket.send(response);


            }
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    public static Message desempacotaRequisicao(String data) {
        Gson objetogson = new Gson();
        return objetogson.fromJson(data, Message.class);
    }

    public static String empacotaResposta(String resultado, int requestId) {
        Gson objetogson = new Gson();
        Message mensagemResposta = new Message(1, requestId, null, null, resultado);

        return objetogson.toJson(mensagemResposta);
    }
}
