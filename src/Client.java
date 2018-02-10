import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String hostname;
    private int port;
    private Socket socket;
    public int roomId = -1;
    public String clientName = "";

    boolean disconnect = false;
    boolean reconnecting = false;

    private ReaderThread reader;
    private WriterThread writer;

    public Client(String hostname,int port){
        this.hostname = hostname;
        this.port = port;
    }
    // exit - чтобы выйти из чата
    // cr - чтобы сменить комнату
    public void start(){
        try {
            socket = new Socket(hostname,port);
            reader = new ReaderThread(socket,this);
            writer = new WriterThread(socket,this);
            reader.start();
            writer.start();
            System.out.println("Connected to chat");
        } catch (IOException e) {
            System.out.println("Error connecting to server, check if server is alive");
        }
    }
    private void restoreFromReconnect(){
        if(reader!=null && writer!=null) {
            reader.restore(socket);
            writer.restore(socket);
            System.out.println("Successfully reconnected");
        }
    }
    public void reconnect(){
        reconnecting =true;
        try {
            socket =  new Socket(hostname,port);
            restoreFromReconnect();
        } catch (IOException e) {
            System.out.println("Cannot connect");
            try {
                Thread.sleep(5000);
                System.out.println("Trying again");
                socket = new Socket(hostname,port);
                restoreFromReconnect();
            } catch (InterruptedException e1) {
                System.out.println("Cannot fall asleep");
            } catch (IOException e1) {
                System.out.println("Cannot reconnect from second time, try again later manually");
                disconnect = true;
            }

        }finally {
            System.out.println();
            reconnecting = false;
        }

    }
    public static void main(String[] args){
        Client client = new Client("localhost",9191);
        client.start();
    }

}
