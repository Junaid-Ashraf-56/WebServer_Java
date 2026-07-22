import http.request.*;
import http.response.HttpResponse;
import router.Router;

import java.io.*;
import java.net.Socket;
import java.util.Objects;


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

            Router router = new Router();
            HttpResponse response = router.incomingRequest(request);
            String number = response.getStatus().toString();
            if (Objects.equals(number, "OK")){
                number = "200";
            }else {
                number = "404";
            }
            System.out.println("HTTP/1.1 "+ number+" " +response.getStatus());
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
