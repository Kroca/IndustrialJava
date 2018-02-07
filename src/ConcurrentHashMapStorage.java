import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by azatfanisovic on 26.01.18.
 */
public class ConcurrentHashMapStorage extends Storage {

    public ConcurrentHashMapStorage() {
        super(new ConcurrentHashMap());
    }


}
