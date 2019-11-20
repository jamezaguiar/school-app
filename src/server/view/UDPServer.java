package server.view;

import com.google.gson.Gson;
import server.controller.Dispatcher;
import server.model.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    Dispatcher dispatcher = new Dispatcher();

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {

        try {
            Message requisicao = desempacotaRequisicao(getRequest());
            System.out.println(requisicao);
            String resultado = dispatcher.invoke(requisicao);
            sendResponse(empacotaResposta(resultado, requisicao.getRequestId()));

        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }

    public String getRequest() {
        String data = null;
        try {
            data = in.readUTF();
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
        return data;
    }


    public void sendResponse(String Response) {
        try {
            out.writeUTF(Response);
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("close failed");
            }
        }
    }



    public Message desempacotaRequisicao(String data) {
        Gson objetogson = new Gson();
        Message mensagemRequisicao = objetogson.fromJson(data, Message.class);
        return mensagemRequisicao;
    }

    public String empacotaResposta(String resultado, int requestId) {
        Gson objetogson = new Gson();
        Message mensagemResposta = new Message(1, requestId, null, null, resultado);
        String Json = objetogson.toJson(mensagemResposta);

        return Json;
    }
}

public class UDPServer {
    //public static void main(String[] args) {
        /*
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),
                        request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }

         */
/*
        DatagramSocket aSocket = null;
        Dispatcher dispatcher = new Dispatcher();

        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];

            while (true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                String requestString = request.getData().toString();
                String response = dispatcher.invoke(unpackRequest(requestString));

                byte[] pack = packReply(response);

                DatagramPacket reply = new DatagramPacket(pack, pack.length, request.getAddress(), request.getPort());
                aSocket.send(reply);

            }
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    public static Message unpackRequest(String data) throws UnsupportedEncodingException {
        Message messageRequest = new Gson().fromJson(data, Message.class);
        return messageRequest;
    }

    public static byte[] packReply(String reply){
        Message message = new Message(1,0,null,null,reply);
        return new Gson().toJson(message).getBytes();
    }

 */
    public static void main(String[] args) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}