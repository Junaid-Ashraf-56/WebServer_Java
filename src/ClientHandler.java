import http.request.*;
import http.response.HttpResponse;
import http.response.ResponseWriter;
import router.Router;

import java.io.*;
import java.net.Socket;


public class ClientHandler implements Runnable{

    private final Socket client;
    private final Router router;

    public ClientHandler(Socket client, Router router) {
        this.client = client;
        this.router = router;
    }

    public void clientHandler() throws IOException{
        try {
            HttpRequestParser parser = new HttpRequestParser();
            HttpRequest request = parser.parseData(
                    client.getInputStream()
            );


            HttpResponse response = router.incomingRequest(request);

            ResponseWriter.write(
                    response,
                    client.getOutputStream()
            );
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
