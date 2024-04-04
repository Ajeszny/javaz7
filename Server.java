import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

class clientServer extends Thread {
    ServerSocket serv;

    clientServer(ServerSocket s) {
        this.serv = s;
    }
    public void run() {
        Socket client;
        PrintWriter out;
        BufferedReader in;
        String message;
        String time;
        long a = System.currentTimeMillis();
        try {
            try {
                serv.setSoTimeout(1);
                client = serv.accept();
            } catch (SocketTimeoutException e) {
                return;
            }
        } catch (IOException e) {
            return;
        }
        try
        {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            return;
        }
        try {
            message = in.readLine();
            time = in.readLine();
        } catch (IOException e) {
            System.out.println("Zdechło!!!!");
            return;
        }
        System.out.println(message + ", time = " + time);
        out.println(a);
        try {
            client.close();
        } catch (IOException e) {
            System.out.println("Nie zamknięto klienta, więc watch ur ass");
        }
    }
}

class ScanForQuit extends Thread {
    public void run() {
        while(true){Scanner s = new Scanner(System.in);
            String quitter = s.nextLine();
            if (Objects.equals(quitter, "quit")) {
                return;
            }}
    }
}

public class Server {

    public static void main(String[] args) {
        ServerSocket serv;
        try {
            serv = new ServerSocket(1337);
        } catch (IOException e) {
            System.out.println("Server zdox!!!");
            e.printStackTrace();
            return;
        }
        ScanForQuit sc = new ScanForQuit();
        sc.start();
        while(sc.isAlive()) {
            for (int index = 0; index < 4; index++) {
                clientServer s = new clientServer(serv);
                s.start();
            }
        }

    }
}