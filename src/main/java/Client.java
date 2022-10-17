import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 10000;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try (Socket clientSocket = new Socket(HOST, PORT);
                 PrintWriter out = new PrintWriter(
                         clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(
                         clientSocket.getInputStream()))) {
                String input = sc.nextLine();
                out.println(input);
                System.out.println(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
