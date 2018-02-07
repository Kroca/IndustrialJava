/**
 * Created by azatfanisovic on 18.01.18.
 */
public class Main {

    public static void main(String[] args){
        String[] fileNames = {"1.txt","2.txt","3.txt","4.txt","5.txt","6.txt","7.txt"};
        WordCounter wc = new WordCounter(fileNames);
        wc.startExecution();
    }
}

