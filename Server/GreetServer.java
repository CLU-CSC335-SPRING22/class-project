package Server;

        import java.net.*;
        import java.io.*;

public class GreetServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(45555);
        Socket s = ss.accept();

        System.out.println("client connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("Client: " + str);

        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        pr.println("yes");

    }
}

