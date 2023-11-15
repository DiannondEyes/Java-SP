import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Messenger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String nickname = scanner.nextLine();

        System.out.print("\"server\" or \"client\"?: ");
        String role = scanner.nextLine();
        if (role.equalsIgnoreCase("server")) {
            runServer(nickname);
        } else if (role.equalsIgnoreCase("client")) {
            System.out.print("Enter server's IP: ");
            String serverIP = scanner.nextLine();
            runClient(nickname, serverIP);
        } else {
            System.out.println("Read better!");
        }
        scanner.close();
    }

    private static void runServer(String nickname) {
        try {
            ServerSocket serverSocket = new ServerSocket(8070);
            System.out.println("Server is started, waiting for client");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client is connected from address: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Chat with " + nickname + "!");
            Thread.sleep(200);

            new Thread(() -> {
                try {
                    while (true) {
                        String clientMessage = in.readLine();
                        if (clientMessage == null) {
                            break;
                        }
                        System.out.println(clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            try {
                while (true) {
                    System.out.print(nickname + ": ");
                    String serverMessage = consoleInput.readLine();
                    out.println(nickname + ": " + serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runClient(String nickname, String serverIP) {
        try {
            Socket socket = new Socket(serverIP, 8070);
            System.out.println("Connected to server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Chat with " + nickname + "!");
            Thread.sleep(200);

            new Thread(() -> {
                try {
                    while (true) {
                        String serverMessage = in.readLine();
                        if (serverMessage == null) {
                            break;
                        }
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            try {
                while (true) {
                    System.out.print(nickname + ": ");
                    String clientMessage = consoleInput.readLine();
                    out.println(nickname + ": " + clientMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}