
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 6666;
        String address = "127.0.0.1";
        try {
            Socket socket = new Socket(address, serverPort);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream stringIn = new DataInputStream(socket.getInputStream());
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            ArrayList results = new ArrayList();
            while (true) {
                line = keyboard.readLine();
                System.out.println("Sending this line to the server...");
                out.writeUTF(line);
                out.flush();
                Object object = in.readObject();
                results = (ArrayList) object;
                System.out.println("ID\t title\t year\t genre\t duration");
                for (int i =0; i < results.size();i++){
                    System.out.println(results.get(0));//id
                    System.out.println(results.get(1));//title
                    System.out.println(results.get(2));//year
                    System.out.println(results.get(3));//duration
                }
                System.out.println(stringIn.readUTF());
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}