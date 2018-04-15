import java.net.*;
import java.io.*;
import java.sql.SQLException;

public class Server {
    public static void main(String[] ar)  throws SQLException, ClassNotFoundException  {
        DBUtil.setConnection();
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");
            Socket socket = ss.accept();
            System.out.println("accepted ok");
            InputStream sin = socket.getInputStream();
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(sin);
            String line = null;
            while(true) {
                line = in.readLine();
                System.out.println(line);
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

