package servercode;

import java.io.*;
import java.net.*;

public class Server {
 
  public final static int socket_port = 300;  // Port
  public final static String file_to_send = "C:\\Users\\musta\\Desktop\\Gönderen\\Deneme.txt";  //Dosyanın konumu
 
  public static void main (String [] args ) throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    try {
      servsock = new ServerSocket(socket_port);
      while (true) {
        System.out.println("Bekleniyor ...");
        try {
          sock = servsock.accept();
          System.out.println("Kabul edilen bağlantı:" + sock);
          
          File myFile = new File (file_to_send);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Gönderiliyor " + file_to_send + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }
}


