package servercode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(300);
        } catch (IOException ex) {
            System.out.println("Hatalı port numarası! ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Client bağlantısı kabul edilmiyor! ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Socket girişi sağlanamıyor! ");
        }

        try {
            out = new FileOutputStream("C:\\Users\\musta\\Desktop\\Gönderen");
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı!");
        }

        byte[] bytes = new byte[1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}


