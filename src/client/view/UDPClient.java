package client.view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class UDPClient {
    /*
    public static void main(String[] args) {

        Proxy proxy = new Proxy();
        Information information;
        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(new Discipline("Banco de dados", "qxd01", null, null));
        Student student = new Student("Joao", 323323432, disciplines);
        String arguments = proxy.packJSON(new Information("joao", 13212, disciplines).toString());
        Message message = new Message(0, 1, "Servant", "createStudent", arguments);
        String packed_message = proxy.packJSON(message.toString());


        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(packed_message.getBytes(), packed_message.length(), aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));
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
    DatagramSocket aSocket;
    int serverPort = 6789;
    DatagramPacket request = null;

    public void sendRequest(String message) throws UnknownHostException {
        InetAddress aHost = InetAddress.getByName("localhost");
        request = new DatagramPacket(message.getBytes(), message.length(), aHost, serverPort);
        try {
            aSocket = new DatagramSocket();
            aSocket.setSoTimeout(2000);
            aSocket.send(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        try {
            aSocket.receive(reply);
            aSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply.getData().toString();
    }

     */
    DataInputStream in;
    DataOutputStream out;
    int serverPort = 7896;
    String address = "127.0.0.1";
    static Socket s = null;

    public void Connection() {
        try {
            s = new Socket(address, serverPort);
            this.in = new DataInputStream(s.getInputStream());
            this.out = new DataOutputStream(s.getOutputStream());

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    public void sendRequest(String request) {
        try {
            Connection();
            out.writeUTF(request);
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    public String getResponse() {
        String response = null;
        try {
            response = in.readUTF();
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
        return response;
    }

    public void close() {

        if (s != null) {
            try {
                s.close();
                System.out.println("Socket fechado!");
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}