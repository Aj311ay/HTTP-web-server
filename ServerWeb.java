import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerWeb {
    public static void main(String[] args) throws IOException {
        // Create a new HTTP server on port 8020
        HttpServer server = HttpServer.create(new InetSocketAddress(8010), 0);

        // Create a simple handler that serves the index.html file
        server.createContext("/", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                String response = readFile("index.html");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // Start the server
        server.start();
        System.out.println("Server is running on port 8010");
        InetAddress address=InetAddress.getLocalHost();
        String ipa = address.getHostAddress();
        System.out.println("use this address : " + ipa);
    }

    private static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
