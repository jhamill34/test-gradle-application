package tech.jhamill34.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private final String customGreeting;

    public Handler(Socket socket, String customGreeting) {
        this.socket = socket;
        this.customGreeting = customGreeting;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(getGreeting());

            String inputLine;
            while((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("bye");
                    break;
                }
                out.println(inputLine);
            }
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("INTERNAL ERROR");
        }
    }

    private String getGreeting() {
       return this.customGreeting;
    }
}
