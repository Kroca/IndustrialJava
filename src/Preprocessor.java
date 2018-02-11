import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by azatfanisovic on 26.01.18.
 */
public class Preprocessor implements Preprocess{

    @Override
    public boolean isValid(String str) {
        Pattern p = Pattern.compile("([a-zA-Z])");
        Matcher m = p.matcher(str);
        return !m.find();
    }


    @Override
    public ArrayList<String> getTokens(String str){
        Tokenizer tokenizer = (input) -> input.split(" ");
        ArrayList<String> result = new ArrayList(Arrays.stream(tokenizer.tokenize(str))
                .map(s -> s.replaceAll("[^а-яА-ЯёЁ]"," "))
                .filter(s -> s.length()>0 && s.startsWith("ч"))
                .collect(Collectors.toList()));

        return result;
    }

}
