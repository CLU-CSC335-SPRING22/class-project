package Server;

        import java.net.*;
        import java.io.*;

public class GreetClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 45555);

        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        pr.println("Is it working?");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("Server: " + str);


    }
}
