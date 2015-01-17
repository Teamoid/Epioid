package epitech.epioid.API.Items;

import java.util.ArrayList;
import java.util.List;

public class Projects extends EpitechItem {
    public List<Project> items = new ArrayList<>();

    public static class Project extends EpitechItem {
        public String codemodule;
        public String project;
        public String end_acti;
        public String acti_title;
        public String num_event;
        public String seats;
        public String title_module;
        public String begin_event;
        public List<String> rights = new ArrayList<>();
        public String num;
        public String begin_acti;
        public String scolaryear;
        public String code_location;
        public String end_event;
        public String type_acti_code;
        public String codeacti;
        public String info_creneau;
        public String registered;
        public String codeinstance;
        public String type_acti;
    }
}
