import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2050);
            System.out.println("Establishing connection");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String state = reader.readLine().toLowerCase();
                System.out.println("Received state from client: " + state);

                String cost = switch (state) {
                    case "arizona" -> "$500";
                    case "new york" -> "$600";
                    case "utah" -> "$300";
                    default -> "unknown state";
                };
                writer.println(cost);

                clientSocket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
