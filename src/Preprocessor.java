import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String prep = str.replaceAll("[^а-яА-ЯёЁ]"," ");
        ArrayList<String> temp = new ArrayList<>();
        for (String s : prep.split(" ")){
            if(s.length()>0 && s.startsWith("ч")) {
                temp.add(s);
            }
        }
        return temp;
    }

}
