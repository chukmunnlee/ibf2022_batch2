package sdf;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CalculatorClient {

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Usage: server port");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        boolean stop = false;

        Console cons = System.console();

        System.out.printf("Connecting to server %s on port %d\n", host, port);
        Socket sock = new Socket(host, port);

        // Output and input stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        while (!stop) {
            String input = cons.readLine("> ");
            String[] terms = input.trim().split(" ");

            switch (terms.length) {
                case 1: // exit
                    dos.writeUTF(Constants.EXIT);
                    dos.flush();
                    stop = true;
                    break;

                case 3: // opnd0 oper opnd1
                    dos.writeUTF(terms[1].trim());
                    dos.writeFloat(Float.parseFloat(terms[0]));
                    dos.writeFloat(Float.parseFloat(terms[2]));
                    dos.flush();

                    float result = dis.readFloat();
                    System.out.printf("Result: %f\n", result);
                    break;

                default: // ignore everything else
            }
        }

        System.out.println("Closing connection");
        try { 
            sock.close();
        } catch (IOException ex) { }

    }
    
}
