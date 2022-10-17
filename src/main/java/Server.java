import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    private static final int PORT = 10000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(
                             clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(
                             clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted");
                    String name = in.readLine();
                    List<String> deniedList = List.of("Коля", "Вика", "Антон");
                    if (deniedList.contains(name)) {
                        out.println("Access denied!");
                    } else {
                        out.println(String.format("Hi, %s, your port is %d", name, clientSocket.getPort()));
                    }
                }
            }
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
}
