import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by azatfanisovic on 18.01.18.
 */
public class WordCounterThread implements Runnable {

    private String fileName;

    private Preprocess preprocess = new Preprocessor();

    private WordCounter parent;
    WordCounterThread(String fileName,WordCounter parent){
        this.fileName = fileName;
        this.parent = parent;
    }

    @Override
    public void run() {
        if(fileName.length()>0){
            try{
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = br.readLine()) !=null ){
                    if(parent.error){
                        System.out.println("Thread have been interrupted by error " + fileName);
                        break;
                    }
                    if(!preprocess.isValid(line)){
                        parent.interruptOnError();
                    }

                    ArrayList tokens = preprocess.getTokens(line);
                    parent.getStorage().add(tokens);
                }
                System.out.println("Thread for " +fileName + " finished execution");
                br.close();
            }catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error occured during the processing of " + fileName + " execution is stoped");
            }
        }else {
            System.out.print("File name is not specified correctly");
        }
    }
}
