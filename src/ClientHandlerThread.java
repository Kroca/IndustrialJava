import java.io.*;
import java.net.Socket;

public class ClientHandlerThread extends Thread {

    private Socket socket;
    private Server server;
    private PrintWriter printWriter;

    private String clientName;
    private int roomId;

    public ClientHandlerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            printWriter = new PrintWriter(socket.getOutputStream(), true);

            clientName = in.readLine();
            System.out.println(clientName + " has connected");

            try {
                roomId = Integer.parseInt(in.readLine());
                server.setChatRoom(this, roomId);
            }catch (NumberFormatException ex){
                // client ensures that input is correct if somehow error occurs, users stays in the same room
                // and asked to try again
                System.out.println("Wrong input");
                sendMessage("Your input was incorrect, try again");
            }
            String clientMessage;
            String serverMessage;

            boolean stop = false;
            while (!stop&&(clientMessage = in.readLine())!=null){
                if (isSpecialCommand(clientMessage)) {
                    switch (Commands.valueOf(clientMessage)){
                        case cr:
                            sendMessage("Select new room id");
                            int newRoomId = Integer.valueOf(in.readLine())-1;
                            server.changeRoom(this, newRoomId);
                            roomId = newRoomId;
                            break;
                        case exit:
                            stop = true;
                            break;
                    }

                }else {
                    serverMessage = clientName + ": " + clientMessage;
                    server.sendMessage(serverMessage, this);
                }
            }
            System.out.println(clientName + " disconnected");
        } catch (IOException e) {
            System.out.println("Connection with "+ clientName + " lost");
        }finally {
            if(clientName!=null){
                server.removeClient(this);
            }
            try {
                socket.close();
            }catch (Exception ex){
                System.out.println("error during disconnect");
            }
            printWriter.close();

        }
    }
    private boolean isSpecialCommand(String input){
        for(Commands command : Commands.values()){
            if(command.name().equals(input)){
                return true;
            }
        }
        return false;
    }


    public int getRoomId() {
        return roomId;
    }

    public String getClientName() {
        return clientName;
    }

    void sendMessage(String message) {
        printWriter.println(message);
    }

}
