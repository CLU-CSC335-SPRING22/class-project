package Common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkAccess {

    /**
     * socket variable for peer to peer communication
     * this is a peer-to-peer connection, either TCP/IP or UDP
     */
    private Socket socket;

    /**
     * stream variables for peer to peer communication
     * to be opened on top of the socket
     */
    private BufferedReader datain;
    private DataOutputStream dataout;

    /**
     * Constructor performs connection construction for the client
     * this will create a socket based on the IP address and the port number
     *

     * @param port: 8000
     */
    public NetworkAccess (String ip, int port){
        try {
            socket = new Socket(ip, port);

            datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataout = new DataOutputStream(socket.getOutputStream());

        }
        catch (UnknownHostException e) {

            System.out.println("Host " + ip + " at port " + port + " is unavailable.");
            System.exit(1);

        }
        catch (IOException e) {

            System.out.println("Unable to create I/O streams.");
            System.exit(1);
        }

    }

    /**
     * Constructor performs connection construction for the server
     * the server will provide the socket as received from the ServerSocket.listen()
     *
     * @param socket: socket provided by the server ServerSocket
     */
    public NetworkAccess (Socket socket)
    {
        try {

            this.socket = socket;

            datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataout = new DataOutputStream(socket.getOutputStream());

        }
        catch (IOException e) {

            System.out.println("Unable to create I/O streams.");
            System.exit(1);
        }
    }

    /**
     * reads a string from the input data stream
     *
     * @return string from the stream
     * @throws IOException
     */
    public String readString () throws IOException
    {
        try {
            return datain.readLine();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * send a String to the server and return the received hand-shake String
     *
     * @param _msg: the string to be sent (\n will be added)
     * @param acknowledge: whether or not to expect an acknowledgment string
     *        client will set this to true except for disconnect
     *        server will set it to false
     * @return
     */
    public String sendString (String _msg, boolean acknowledge)
    {
        String rtnmsg = "";

        try {

            dataout.writeBytes(_msg + "\n");
            dataout.flush();

            if (acknowledge) {
                rtnmsg = "";
                do {
                    rtnmsg = datain.readLine();

                } while (rtnmsg.equals(""));
            }
        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);

        }

        return rtnmsg;

    }

    /**
     * close the connection (socket)
     */
    public void close ()
    {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("close: invalid socket");
        }
    }

}
