package Server;

import java.sql.SQLException;

public class Server {
    private static DBaseConnection dbc = null;
    public static void main(String[] args) {
        new ServerGUI();
        dbc = new DBaseConnection();
        new ServerConnection();
    }

    public static boolean checkConnection() {
        try {
            return dbc.getConnectionStatus();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
