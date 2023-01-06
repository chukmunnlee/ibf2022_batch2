package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        System.out.println("Starting server on port 3000");
        // Create a server socket and listen to a specific port
        ServerSocket server = new ServerSocket(3000);

        while (true) {
            // Wait for a connection
            System.out.println("Waiting incoming connection");
            Socket conn = server.accept();

            System.out.println("Got a connection");

            // Do something
            // Get the input stream, read the data from the client
            InputStream is = conn.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            String input = ois.readUTF();
            int count = ois.readInt();
            System.out.printf(">>> from client: %s\n", input);

            input = input.toUpperCase();
            System.out.printf(">>> from client: %s\n", input);

            OutputStream os = conn.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (int i = 0; i < count; i++)
                oos.writeUTF("%d: %s".formatted(i, input));
            oos.flush();

            // Close the connection, all streams will be closed
            conn.close();
        }
    }

}