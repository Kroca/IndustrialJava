import java.util.ArrayList;

/**
 * Created by azatfanisovic on 26.01.18.
 */
public interface Preprocess {
    boolean isValid(String str);
    ArrayList<String> getTokens(String str);
}
