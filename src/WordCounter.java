import java.util.ArrayList;
import java.util.List;

/**
 * Created by azatfanisovic on 19.01.18.
 */
public class WordCounter {

    public boolean error = false;

    private String[] fileNames;
    private List<Thread> threads = new ArrayList<>();
    //you can swicth between storage based on regular hashmap and concurrent hashmap
    //private Storage storage = new ConcurrentHashMapStorage();
    private Storage storage = new RegularHashMapStorage();


    public  WordCounter(String[] fileNames){
        this.fileNames = fileNames;
    }


    public void startExecution(){
        for (String fileName : fileNames){
            if(!error) {
                WordCounterThread wtc = new WordCounterThread(fileName , this);
                Thread th = new Thread(wtc);
                threads.add(th);
                th.start();
            }
        }
        displayCurrentState();
    }

    Storage getStorage(){
        return storage;
    }

    private void displayCurrentState(){
        int running = 0;
        do{
            running= 0;
            for (Thread th:threads){
                if(th.isAlive()){
                    running++;
                }
            }
            System.out.println(storage);
        } while (running>0);
    }
    synchronized void interruptOnError(){
        error = true;
        System.out.print("All threads interrupted due to error");
    }

}
