package epitech.epioid.API.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michelantoine on 17/01/15.
 */

public class Messages extends EpitechItem {
    public List<Message> items = new ArrayList<>();

    public static class Message extends EpitechItem {
        public String title;
        public User user;
        public String content;
        public String date;
    }
}
