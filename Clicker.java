import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Clicker {
    public static Scanner scanner = new Scanner(System.in);
    // Количество кликов каждого клиента будет храниться в hashmap, где ключ - айпи адрес
    private static Map<String, Integer> scoreboard = new ConcurrentHashMap<>(); // Таблица лидеров
    // CoucurrentHashMap потокобезопасен

    // Я решил написать комментарии, представляете?
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
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void runClient(String serverIP) {
        try {
            Socket socket = new Socket(serverIP, 8070);
            System.out.println("Connected to server");
            System.out.println("Type:\n+1 to make 1 click, +50 to make 50 clicks.\n-1 to remove 1 click, -50 to remove 50 clicks.\nJust enter will be treat as +1 click.\nType scoreboard to view scoreboard!\n");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String message = scanner.nextLine();
                /* отправляем серверу массив {действие, количество}
                 действие 1 - увеличить, 0 - уменьшить, 2 - показать таблицу лидеров */
                if (message.startsWith("+") && isNumeric(message.substring(1))) {
                    out.println("{1," + message.substring(1) + "}");
                    System.out.println("+ " + message.substring(1) + "!");
                } else if (message.startsWith("-") && isNumeric(message.substring(1))) {
                    out.println("{0," + message.substring(1) + "}");
                    System.out.println("- " + message.substring(1) + "!");
                } else if (message.isEmpty()) { // нажатие Enter (то есть пустая строка) будет считаться как +1
                    out.println("{1,1}");
                    System.out.println("+1");
                } else if (message.equals("scoreboard")) { // клиент может написать scoreboard, чтобы ему вывелась таблица лидеров
                    out.println("{2,0}");
                    new Thread(() -> {
                        String line;
                        try {
                            while ((line = in.readLine()) != null) {
                                System.out.println(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } else {
                    System.out.println("I didn't understand you.");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8070);
            System.out.println("Server is running and waiting for clients");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection from " + clientSocket.getInetAddress());
                // Чтобы могло подключиться сразу много клиентов, каждый клиент будет обрабатываться в отдельном потоке
                new Thread(() -> observeClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void observeClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                String[] action = message.substring(1, message.length() - 1).split(",");
                // action = {действие, количество}
                // действие 1 - увеличить, 0 - уменьшить, 2 - показать таблицу лидеров
                int currentClicks = scoreboard.getOrDefault(clientSocket.getInetAddress().getHostAddress(), 0);
                // Нужно получить, сколько кликов уже сделал клиент в таблице лидеров, чтобы потом заносить новое значение: старые клики + новые клики
                // getOrDefault вернёт 0, если такого айпи адреса в таблице лидеров нет
                if (action[0].equals("1")) { // Увеличить клики
                    scoreboard.put(clientSocket.getInetAddress().getHostAddress(), currentClicks + Integer.parseInt(action[1]));
                } else if (action[0].equals("0")) { // Уменьшить
                    // Кликов не может быть меньше 0, правда?
                    scoreboard.put(clientSocket.getInetAddress().getHostAddress(), Math.max(0, currentClicks - Integer.parseInt(action[1])));
                } else if (action[0].equals("2")) { // Отправить таблицу лидеров
                    StringBuilder scoreboardString = new StringBuilder("Scoreboard:\n");

                    scoreboard.forEach((ip, clicks) -> scoreboardString.append(ip).append(": ").append(clicks).append("\n"));
                    System.out.println(scoreboardString.toString());
                    out.println(scoreboardString.toString());
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
}