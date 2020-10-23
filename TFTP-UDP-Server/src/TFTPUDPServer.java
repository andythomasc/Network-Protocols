

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class TFTPUDPServer {

    int defaultPort = 1025;
    DatagramSocket socket;

    public static void main(String[] args) throws IOException {
        //System.out.println("Time Server Started");
        TFTPUDPServer server = new TFTPUDPServer();
    }

    /**
     * Setup the socket up with default port. This is where all requests will
     * come to. When it receives a packet it will create a new thread to deal
     * with the transaction which will take place.
     *
     * @throws SocketException
     * @throws IOException
     */
    public TFTPUDPServer() throws SocketException, IOException {
        this.socket = new DatagramSocket(defaultPort);
        while (true) {
            byte[] inBuf = new byte[516];
            DatagramPacket packet = new DatagramPacket(inBuf, 516);
            //System.out.println("Server waiting for packet");
            socket.receive(packet);
            new Thread(new TFTPUDPServerThread(packet)).start();
            //System.out.println("New Thread Server Started");
        }
    }
}