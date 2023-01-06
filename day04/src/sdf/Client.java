package sdf;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        // Connect to the server listening on port 3000
        // 127.0.0.1 or localhost means my local computer
        Socket conn = new Socket("127.0.0.1", 3000);

        System.out.println("Connected to server on localhost:3000");

        Console cons = System.console();
        String line = cons.readLine("What would you like to uppercase today? ");

        // Do something
        // Get the output stream to write the the server
        OutputStream os = conn.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);

        int count = 4;
        oos.writeInt(count);
        oos.writeUTF(line);
        oos.flush();

        // Get the input from the server
        InputStream is = conn.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        for (int i = 0; i < count; i++) {
            String result = ois.readUTF();
            System.out.printf(">> result: %s\n", result);
        }

        // Close the connection
        conn.close();

    }
    
}
