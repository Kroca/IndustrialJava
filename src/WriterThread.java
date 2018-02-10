import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
//Listens for clients input and sends it to the server

public class WriterThread extends Thread {

    private PrintWriter writer;
    private Socket socket;
    private Client client;
    public WriterThread(Socket socket,Client client){
        this.client= client;
        this.socket =socket;
        setupWriter();
    }
    private void setupWriter(){
        try {
            writer = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                System.out.println("Error on socket closing at initialization");
            }
            writer.close();
            System.out.println("Error during creation of printWriter, please try again");
        }
    }
    void restore(Socket socket){
        this.socket= socket;
        setupWriter();
        writer.println(client.clientName);
        writer.println(client.roomId);
    }
    public void run(){
        if(writer==null){
            System.out.println("Error occurred, try again later");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String userName = scanner.nextLine();
        client.clientName = userName;
        writer.println(userName);

        System.out.println("Now please choose the room from 1 to 4");
        while (!client.disconnect){
            if(scanner.hasNextInt()){
                int roomId = scanner.nextInt();
                if(roomId>0 && roomId <5){
                    writer.println(roomId-1);
                    client.roomId = roomId-1;
                    break;
                }else {
                    System.out.println("Wrong Room id Try again");
                }

            }else {
                scanner.nextLine();
            }
        }

        String text = "";
        while (!client.disconnect && !text.equals(Commands.exit.name())){
            text = scanner.nextLine();
            if(text.length()>0){
                writer.println(text);
            }
        }


        writer.close();
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error writing to server");
        }
    }
}
