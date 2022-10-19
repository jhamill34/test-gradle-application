package tech.jhamill34.app;

public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println(args[0]);

        int[] values = new int[10];
        values[0] = 100;
        System.out.println(values[0]);

        Server s = new SimpleServer(4000);
        s.start();
    }
}
