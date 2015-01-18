package epitech.epioid.API.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michelantoine on 16/01/15.
 */
public class Planning extends EpitechContainer {
    public static class PlanningItem extends EpitechItem {
        public List<User> prof_inst = new ArrayList<>();
        public String title;
        public String rdv_indiv_registered;
        public String allowed_planning_end;
        public String nb_group;
        public String start;
        public String register_month;
        public String allowed_planning_start;
        public String project;
        public String event_registered;
        public String total_students_registered;
        public String allow_register;
        public String codemodule;
        public String rdv_group_registered;
        public String semester;
        public String type_code;
        public String is_rdv;
        public String allow_token;
        public String titlemodule;
        public String in_more_than_one_month;
        public String acti_title;
        public String instance_location;
        public String nb_hours;
        public String register_prof;
        public String nb_max_students_projet;
        public Room room;
        public String codeacti;
        public String codeevent;
        public String codeinstance;
        public String dates;
        public String register_student;
        public String type_title;
        public String num_event;
        public String end;
        public String scolaryear;
        public String module_registered;
        public String past;
        public String module_available;

        private static class Room {
            public String type;
            public String seats;
            public String code;
        }
    }
}
