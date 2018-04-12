import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] ar)    {
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");
            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            String line = null;
            while(true) {
                line = in.readUTF();
                if(line.equals("all")){
                    out.writeUTF("id \t title \t year \t duration\n");
                    for (int i =0;i<DBUtil.getData().length; i++){
                        out.writeUTF(DBUtil.getData()[i].get(0)+" ");//id
                        out.writeUTF(DBUtil.getData()[i].get(1)+" ");//title
                        out.writeUTF(DBUtil.getData()[i].get(2)+" ");//year
                        out.writeUTF((String)DBUtil.getData()[i].get(4));//duration
                    }
                }
                else if (line.matches("\\\\d+")){
                    out.writeUTF("id \t title \t year \t duration\n");
                    for (int i =0;i<DBUtil.getData().length; i++){
                        out.writeUTF(DBUtil.getData(Integer.parseInt(line))[i].get(0)+" ");//id
                        out.writeUTF(DBUtil.getData(Integer.parseInt(line))[i].get(1)+" ");//title
                        out.writeUTF(DBUtil.getData(Integer.parseInt(line))[i].get(2)+" ");//year
                        out.writeUTF((String)DBUtil.getData(Integer.parseInt(line))[i].get(4));//duration
                    }
                }
                else{
                    out.writeUTF("You sent "+line);
                }
            }
        } catch(Exception x) { x.printStackTrace(); }
    }

}

