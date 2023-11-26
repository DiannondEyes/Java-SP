import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Increment {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("\"server\" or \"client\"?: ");
        String role = scanner.nextLine();
        if (role.equalsIgnoreCase("server")) {
            runServer();
        } else if (role.equalsIgnoreCase("client")) {
            System.out.print("Enter server's IP: ");
            String serverIP = scanner.nextLine();
            runClient(serverIP);
        } else {
            System.out.println("Read better!");
        }
        scanner.close();
    }

    public static void runServer() {
        try {
            ServerSocket servSocket = new ServerSocket(8070);
            System.out.println("Server is started, waiting for client");
            Socket clientSocket = servSocket.accept();

            System.out.println("Client is connected from address: " + clientSocket.getInetAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                String message = scanner.nextLine();
                if (message.startsWith("+") && ismessage.substring(1)) {
                    out.println("{1,"+message.substring(1)+"}");
                    System.out.println("+ "+message.substring(1)+"!");
                }
                else if (message.startsWith("-")) {
                    out.println("{0,"+message.substring(0))
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}