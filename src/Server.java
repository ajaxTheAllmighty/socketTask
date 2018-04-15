import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] ar)    {
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");
            Socket socket = ss.accept();
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String line = null;
            while(true) {
                line = in.readUTF();
                if(line.equals("all")){ // SELECT *
                    out.writeObject(DBUtil.getData());
                }
                else if (line.matches("\\\\d+")){ // SELECT exact count
                    out.writeObject(DBUtil.getData(Integer.parseInt(line)));
                }
                else{   //return same text
                    out.writeUTF("You sent "+line);
                }
            }
        } catch(Exception x) { x.printStackTrace(); }
    }

}

