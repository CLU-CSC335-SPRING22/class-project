
package Client;

import Common.NetworkAccess;

import java.io.IOException;

public class ClientConnection {
private NetworkAccess networkaccess;

public NetworkAccess getNetworkAccess()
        {
        return networkaccess;
        }

/**
 * Creates a peer-to-peer connection to the server
 *
 * @param ip: the IP address of the server
 * @param port: the port on which the server is listening
 */
public ClientConnection (String ip, int port) throws IOException {
        networkaccess = new NetworkAccess(ip, port);
        }


/**
 * Disconnects the client from the server
 */
public void disconnect ()
        {
        String text = "disconnect";
        networkaccess.sendString(text,  false);
        networkaccess.close();
        }
        }