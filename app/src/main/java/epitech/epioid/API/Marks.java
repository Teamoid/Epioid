package epitech.epioid.API;

import java.util.ArrayList;
import java.util.List;

import epitech.epioid.API.Items.EpitechItem;

/**
 * Created by michelantoine on 17/01/15.
 */

public class Marks extends EpitechItem {
    public List<Mark> notes = new ArrayList<>();

    public static class Mark {
        public String scolaryear;
        public String codemodule;
        public String titlemodule;
        public String codeinstance;
        public String codeacti;
        public String title;
        public String date;
        public String correcteur;
        public String final_note;
        public String comment;
    }
}
