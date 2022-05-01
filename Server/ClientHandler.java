package Server;

import java.io.IOException;
import java.net.Socket;

import Common.NetworkAccess;


public class ClientHandler extends Thread {

    /**
     * provides a peer-to-peer connection to the client
     */
    private NetworkAccess networkaccess;

    /**
     * controls the run thread
     */
    private boolean go;

    /**
     * the name of this client
     */
    private String name;

    /**
     * the unique id of this client
     */
    private int id;

    /**
     * a reference to the server that "has" this ClientHandler
     */
    private ServerConnection server;

    /**
     * Constructor saves the ID, socket, and reference to the server
     * then construct the NetworkAccess object
     *
     * @param id: the unique ID for this ClientHandler
     * @param socket: the peer-to-peer socket for connection to the client
     * @param server: reference to the server that "has" this ClientHandler
     */
    public ClientHandler (int id, Socket socket, ServerConnection server)
    {
        this.server = server;
        this.id = id;
        this.name = Integer.toString(id);
        go = true;

        networkaccess = new NetworkAccess(socket);
    }


    public String toString ()
    {
        return name;
    }

    /**
     * getter function for the private name field
     *
     * @return name
     */
    public String getname ()
    {
        return name;
    }

    public void Stop()
    {
        go = false;
    }

    public int getID()
    {
        return id;
    }

    public ServerConnection getServer()
    {
        return server;
    }

    // -- similar to a main() function in that it is the entry point of
    //    the thread
    public void run () {

        // -- server thread runs until the client terminates the connection
        while (go) {
            try {
                // -- always receives a String object with a newline (\n)
                //    on the end due to how BufferedReader readLine() works.
                //    The client adds it to the user's string but the BufferedReader
                //    readLine() call strips it off
                String cmd = networkaccess.readString();

                // -- if it is not the termination message, send it back adding the
                //    required (by readLine) "\n"

                // -- if the disconnect string is received then
                //    close the socket, remove this thread object from the
                //    server's active client thread list, and terminate the thread
                //    this is the server side "command processor"
                //    you will need to define a communication protocol (language) to be used
                //    between the client and the server
                //    e.g. client sends "LOGIN;<username>;<password>\n"
                //         server parses it to "LOGIN", "<username>", "<password>" and performs login function
                //         server responds with "SUCCESS\n"
                //    this is where all the server side Use Cases will be handled

                CommandProtocol.processCommand(cmd, networkaccess, this);
            }
            catch (IOException e) {

                e.printStackTrace();
                go = false;

            }

        }
    }
}
