
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatRoom {
    private int roomId;
    private Set<ClientHandlerThread> clients = Collections.synchronizedSet(new HashSet<>());

    public ChatRoom(int roomId){
        this.roomId = roomId;

    }

    public Set<ClientHandlerThread> getClients() {
        return clients;
    }

    public void migrate(ChatRoom room, ClientHandlerThread client){
        remove(client);
        room.addClient(client);

    }

    public boolean remove(ClientHandlerThread clientHandlerThread){
        synchronized (clients){
            return clients.remove(clientHandlerThread);
        }

    }

    public synchronized void addClient(ClientHandlerThread client){
        synchronized (client) {
            clients.add(client);
        }
    }

}
