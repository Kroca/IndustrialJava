import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private ChatRoom[] chatRooms = {new ChatRoom(1),new ChatRoom(2),new ChatRoom(3),new ChatRoom(4)};
    private ArrayList<Socket> clientSockets = new ArrayList<>();
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer(9191);
    }

    void startServer(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server have been started");
            while (true) {
                Socket clientSocket = server.accept();
                clientSockets.add(clientSocket);
                System.out.println("New user connected");
                ClientHandlerThread newClient = new ClientHandlerThread(clientSocket, this);
                newClient.start();
            }
        } catch (IOException e) {

            System.out.println("Server error occurred");
        }finally {
            for (Socket s:clientSockets) {
                try {
                    s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void sendMessage(String message, ClientHandlerThread sender) {
        for (ClientHandlerThread ct : chatRooms[sender.getRoomId()].getClients()) {
            if (ct != sender) {
                ct.sendMessage(message);
            }
        }
    }

    public void changeRoom(ClientHandlerThread client, int newRoomId) {
        chatRooms[client.getRoomId()].migrate(chatRooms[newRoomId], client);
    }

    public void setChatRoom(ClientHandlerThread client, int roomId) {
        if(roomId<chatRooms.length) {
            chatRooms[roomId].addClient(client);
        }
    }

    void removeClient(ClientHandlerThread client) {
        chatRooms[client.getRoomId()].remove(client);
        System.out.println("Client " + client.getClientName() + " quitted");
    }
}
