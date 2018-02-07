import java.util.HashMap;

/**
 * Created by azatfanisovic on 26.01.18.
 */
public class RegularHashMapStorage extends Storage {


    public RegularHashMapStorage() {
        super(new HashMap());
    }

    @Override
    void add(String token) {
        synchronized (map){
            super.add(token);
        }
    }

    @Override
    public String toString() {
        synchronized (map){
            return super.toString();
        }
    }
}
