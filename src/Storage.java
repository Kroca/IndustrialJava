import java.util.ArrayList;
import java.util.Map;

/**
 * Created by azatfanisovic on 26.01.18.
 */
public abstract class Storage {
    Map<String,Integer> map;
    public Storage(Map map){
        this.map = map;
    }
    void add(String token){
        map.put(token,checkEntryCount(token)+1);
    }
    void add(ArrayList<String> tokens){
        for (String token :tokens){
            add(token);
        }
    }
    int checkEntryCount(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
