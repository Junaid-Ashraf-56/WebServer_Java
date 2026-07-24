package main.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerLifecycle {

    public static void shutdown(
            ServerSocket serverSocket,
            ThreadPoolExecutor executor
    ) {
        System.out.println("Server shutdown started");

        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException exception) {
            System.out.println(
                    "Could not close main.java.server socket: "
                            + exception.getMessage()
            );
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException exception) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Server stopped");
    }
}