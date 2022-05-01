package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerConnection implements Runnable {
    /**
     * the port number used for client communication
     */
    private static final int PORT = 8000;

    /**
     * used for shutting down the server thread
     */
    private boolean running = true;

    /**
     * unique ID for each client connection
     */
    private int nextId = 0;

    /**
     * the socket that waits for client connections
     */
    private ServerSocket serversocket;

    /**
     * list of active client threads by ID number
     * Vector is a "thread safe" ArrayList
     */
    private Vector<ClientHandler> clientconnections;

    public int getconnections ()
    {
        return clientconnections.size();
    }

    /**
     * constructor creates the list of clients and
     * starts the server listening on the port
     */
    public ServerConnection ()
    {
        clientconnections = new Vector<ClientHandler>();
    }

    /**
     * listen for incoming client connections
     * analogous to a telephone operator
     * listens for the phone to ring
     * routes the incoming call to the requested extension
     * goes back to listening for the phone to ring
     *
     * This is in its own thread because the .accept() method is blocking
     */
    public void run () {

        try {
            System.out.println("Server is running...");

            serversocket = new ServerSocket(PORT);

            while (running) {
                Socket socket = serversocket.accept();
                peerconnection(socket);
            }
        }
        catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }
    }


    /**
     * creates a direct (peer-to-peer) connection between the client and the server
     * via a thread
     *
     * @param socket: socket from the ServerSocket.listen() call
     */
    public void peerconnection (Socket socket)
    {
        ClientHandler connection = new ClientHandler(nextId, socket, this);

        clientconnections.add(connection);

        connection.start();

        System.out.println("SERVER: connection received for id " + nextId + "\n");
        ++nextId;
    }


    /**
     * called by a ServerThread when a client is terminated to remove
     * the connection from the list
     *
     * @param id: the unique ID assigned by the server
     */
    public void removeID(int id)
    {
        for (int i = 0; i < clientconnections.size(); ++i) {
            ClientHandler cc = clientconnections.get(i);
            long x = cc.getID();
            if (x == id) {
                clientconnections.remove(i);

                System.out.println("SERVER: connection closed for client id " + id + "\n");
                break;
            }
        }
    }

}
