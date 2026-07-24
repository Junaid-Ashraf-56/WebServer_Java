import middleware.MiddlewareChain;
import router.Router;
import server.ClientHandler;
import server.ServerLifecycle;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

class Main{

    static BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(15);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,5,1000, TimeUnit.SECONDS,queue);


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        Router router = new Router();
        MiddlewareChain middlewareChain = new MiddlewareChain(router);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() ->
                        ServerLifecycle.shutdown(
                                serverSocket,
                                threadPoolExecutor
                        )
                )
        );

        System.out.println("Server is running");

        try {
            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();

                ClientHandler task =
                        new ClientHandler(client, middlewareChain);

                threadPoolExecutor.submit(task);
            }
        } catch (SocketException exception) {
            if (!serverSocket.isClosed()) {
                System.out.println(
                        "Server socket problem: " + exception.getMessage()
                );
            }
        }
    }
}