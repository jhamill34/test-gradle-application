package tech.jhamill34.app;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleServer implements Server {
    private final int port;
    private ServerSocket serverSocket;
    private final ExecutorService executorService;
    private boolean running;

    public SimpleServer(int port) {
        super();
        this.port = port;
        int cores = Runtime.getRuntime().availableProcessors();
        this.executorService = new ThreadPoolExecutor(
                cores,
                Math.max(cores, 100),
                1, TimeUnit.MINUTES,
                new SynchronousQueue<>()
        );
    }

    @Override
    public void start() throws Exception {
        this.running = true;
        this.serverSocket = new ServerSocket(this.port);

        while (running) {
            Handler handler = new Handler(this.serverSocket.accept(), "Hello!");
            this.executorService.execute(handler);
        }
    }

    @Override
    public void stop() throws Exception {
        this.running = false;
        this.executorService.shutdownNow();
        this.serverSocket.close();
    }
}


