import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 2050);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner keyboard = new Scanner(System.in);
            System.out.println("Choose a trip: Arizona, Utah, or New York");
            String state = keyboard.nextLine();
            writer.println(state);

            String cost = reader.readLine();
            System.out.println("Cost of travel to " + state + ": " + cost);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
