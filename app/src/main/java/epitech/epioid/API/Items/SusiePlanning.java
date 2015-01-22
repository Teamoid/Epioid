package epitech.epioid.API.Items;

/**
 * Created by michelantoine on 17/01/15.
 */

public class SusiePlanning extends EpitechContainer {
    public static class SusiePlanningItem extends EpitechItem {
        public String type;
        public String id;
        public String duration;
        public String event_registered;
        public String weeks_left;
        public String type_room;
        public Owner owner = new Owner();
        public Maker maker = new Maker();
        public String rating_event;
        public String id_calendar;
        public String start;
        public String end;
        public String id_maker;
        public String has_to_rate;
        public String title;
        public String registered;
        public String description;
        public String confirm_owner;
        public String location;
        public String calendar_type;
        public String id_owner;
        public String nb_rated;
        public String confirm_maker;
        public String nb_place;
        public String color;

        public static class Owner {
            public String picture;
            public String title;
            public String login;
        }

        public static class Maker {
            public String title;
            public String login;
        }
    }
}
