import handler.HelloHandler;
import handler.UsersHandler;
import http.request.HttpRequest;
import http.request.HttpRequestParser;
import http.response.HttpResponse;
import router.RouteMatcher;

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

            RouteMatcher route = new RouteMatcher();
            HttpResponse response = route.incomingRequest(request);
            System.out.println("HTTP/1.1 200"+response.getStatus());
            System.out.println(response.getHeaders().get("accept"));
            System.out.println(response.getBody());

        }catch (Exception e){
            System.out.println("http.request problem "+ e.getMessage());
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
