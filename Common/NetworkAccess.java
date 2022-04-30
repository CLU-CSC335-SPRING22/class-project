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
     * @param ip: server.vandurasystems.xyz
     * @param port: 8000
     */
    public NetworkAccess (String ip, int port) throws IOException {
        try {
            // -- construct the peer to peer socket
            //    check if the server is available and connects if it is,
            //    if not throw an exception
            socket = new Socket(ip, port);

            // -- wrap the socket in stream I/O objects
            //    these are for passing String types over the network
            //    there are other stream types (Object stream) that can be used
            datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataout = new DataOutputStream(socket.getOutputStream());

        }
        catch (UnknownHostException e) {

            System.out.println("Host " + ip + " at port " + port + " is unavailable.");
            System.exit(1);

        }
//        catch (IOException e) {
//
//            System.out.println("Unable to create I/O streams.");
//            System.exit(1);
//        }

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
            // -- construct the peer to peer socket
            //    check if the server is available and connects if it is,
            //    if not throw an exception
            this.socket = socket;

            // -- wrap the socket in stream I/O objects
            //    these are for passing String types over the network
            //    there are other stream types (Object stream) that can be used
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

        // -- the protocol is this:
        //    client sends a \n terminated String to the server
        //    server receives String, processes it, sends \n terminate String to client
        //    this is called a "hand-shake" system
        try {
            // -- the server only receives String objects that are
            //    terminated with a newline \n"
            // -- send the String making sure to flush the buffer
            dataout.writeBytes(_msg + "\n");
            dataout.flush();

            if (acknowledge) {
                // -- receive the response from the server
                //    The do/while makes this a blocking read. Normally BufferedReader.readLine() is non-blocking.
                //    That is, if there is no String to read, it will read "". Doing it this way does not allow
                //    that to occur. We must get a response from the server. Time out could be implemented with
                //    a counter.
                rtnmsg = "";
                do {
                    // -- this is a non-blocking read
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
