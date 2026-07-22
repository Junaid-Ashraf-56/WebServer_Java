import router.Router;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

class Main{

    static BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(15);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,5,1000, TimeUnit.SECONDS,queue);


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server is running");

        Router router = new Router();

        while (!serverSocket.isClosed()){
            Socket client = serverSocket.accept();
            System.out.println("request accepted");

            ClientHandler task = new ClientHandler(client,router);

            threadPoolExecutor.submit(task);

        }
    }
}