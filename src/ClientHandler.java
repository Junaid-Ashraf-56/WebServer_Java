import request.HttpRequest;
import request.HttpRequestParser;

import java.io.*;
import java.net.Socket;


public class ClientHandler implements Runnable{

    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public void clientHandler() throws IOException{
        try {
            HttpRequestParser parser = new HttpRequestParser();
            HttpRequest request = parser.parseData(
                    client.getInputStream()
            );
            System.out.println("method " + request.getMethod());
            System.out.println("path "+ request.getPath());
            System.out.println("http version "+ request.getHttpVersion());
        }catch (Exception e){
            System.out.println("request problem "+ e.getMessage());
        }

    }

    @Override
    public void run() {
        try {
            clientHandler();
        } catch (IOException e) {
            System.out.println("Client connection problem: " + e.getMessage());
        } finally {
            try {
                client.close();
                System.out.println("Connection closed");
            } catch (IOException e) {
                System.out.println("Close problem happened");
            }
        }
    }
}
