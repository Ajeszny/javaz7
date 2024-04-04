import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static boolean quit = false;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        while(!quit){
            try {
                String q = scn.nextLine();
                if (Objects.equals(q.toLowerCase(), "quit")) {
                   quit = true;
                   break;
                }
                Socket conn;
                try {
                    conn = new Socket("127.0.0.1", 1337);
                } catch (UnknownHostException e) {
                    System.out.println("Server not working you dumbass");
                    return;
                }
                PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                out.println(q);
                long a = System.currentTimeMillis();
                out.println(a);
                String chuj = in.readLine();
                System.out.println(chuj);
            } catch (IOException e) {
                System.out.println("Wszysko poszlo w pizdę: " + e.getMessage());
            }
        }
    }
}


