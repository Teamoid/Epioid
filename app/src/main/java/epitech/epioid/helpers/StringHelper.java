package epitech.epioid.helpers;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class StringHelper {

    public static String extractLink(String rawStringContainingHTML)
    {
        Pattern pattern = Pattern.compile("\"([^\\'\" >]+)\"");
        Matcher matcher = pattern.matcher(rawStringContainingHTML);
        while (matcher.find()) {
            if (matcher.group().startsWith("http")) {
                return (matcher.group());
            }
        }
        return (null);
    }

    public static String replaceLink(String rawString, String link)
    {
        Pattern pattern = Pattern.compile("<a.*<\\/a>");
        Matcher matcher = pattern.matcher(rawString);
        return (matcher.replaceAll(link));
    }

}
