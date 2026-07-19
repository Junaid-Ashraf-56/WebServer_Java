import java.io.*;
import java.net.Socket;
import java.util.Objects;


public class ClientHandler implements Runnable{

    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public void clientHandler() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out =  new PrintWriter(client.getOutputStream(),true);

        String message;

        while ((message = in.readLine()) != null) {
            System.out.println();

            if (Objects.equals(message, "/q")) {
                System.out.print("connection close");
                break;
            }

            System.out.print(message);
            out.println("request accepted");
            System.out.println();
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
