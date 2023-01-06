package sdf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {

    public static void main(String[] args) throws IOException {

        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        System.out.printf("Starting server on port %d\n", port);
        ServerSocket server = new ServerSocket(port);

        while (true) {
            Socket sock = server.accept();
            System.out.println("New client connection");

            ClientHandler handler = new ClientHandler(sock);
            try {
                // Call this in a try/catch block so 
                // any client exception will not cause the
                // server to stop
                handler.run();
            } catch (IOException ex) { }
        }

    }

}