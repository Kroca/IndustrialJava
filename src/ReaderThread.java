import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//Reads messages from other users.
public class ReaderThread extends Thread{

    private BufferedReader reader;
    private Socket socket;
    private Client client;


    public ReaderThread(Socket socket,Client client){
        this.client = client;
        this.socket = socket;
        setupReader();

    }
    private void setupReader(){
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                System.out.println("Error closing socket");
            }
            System.out.println("Error getting input stream!!!");
            e.printStackTrace();
        }
    }

    void restore(Socket socket){
        this.socket = socket;
        setupReader();
    }
    public void run(){
        try {
            String response;
            while (!client.disconnect){
                if(!client.reconnecting) {
                    response = reader.readLine();
                    if (response != null) {
                        System.out.println(response);
                    } else {
                        client.reconnect();
                    }
                }
            }

            System.out.println("socket closed");
        } catch (IOException e) {
            System.out.println("Error reading from server");
            try {
                reader.close();
            } catch (IOException e1) {
                System.out.println("Error closing reader");
            }
            try {
                socket.close();
            } catch (IOException e1) {
                System.out.println("Error closing socket");
            }
        }
    }
}