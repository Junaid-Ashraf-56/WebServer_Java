import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Connection {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.print("Server is running");

        Socket client = serverSocket.accept();
        System.out.println();
        System.out.print("request accepted");

        while (serverSocket.isBound()){

            while (client.isConnected()){
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out =  new PrintWriter(client.getOutputStream(),true);

                String message =  in.readLine();
                System.out.println();
                if (Objects.equals(message, "/q")){
                    client.close();
                    break;

                }
                System.out.print(message);

                out.println("request accepted");
                System.out.println();
            }
            if (serverSocket.isClosed()){
                System.out.println("server Closed");
            }
            client = serverSocket.accept();
            System.out.println();
            System.out.print("request accepted");
        }
    }
}
