package sdf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler {

    private Socket sock;

    public ClientHandler(Socket sock) {
        this.sock = sock;
    }

    public void run() throws IOException {

        while (true) {
            boolean stop = false;
            System.out.println("Got a new connection");

            // Output and input stream
            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            while (!stop) {

                String oper = dis.readUTF();

                switch (oper) {
                    case Constants.EXIT:
                        stop = true;
                        break;

                    case Constants.PLUS:
                        float opnd0 = dis.readFloat();
                        float opnd1 = dis.readFloat();
                        float result = opnd0 + opnd1;
                        dos.writeFloat(result);
                        dos.flush();
                        break;

                    default:
                }

            }

            System.out.println("Terminating client connection");
            try {
                sock.close();
            } catch (IOException ex) { }
        }
    }

}
