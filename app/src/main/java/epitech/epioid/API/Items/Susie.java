package epitech.epioid.API.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michelantoine on 17/01/15.
 */
public class Susie extends EpitechItem {
    public String id;
    public String id_calendar;
    public String location;
    public String type_room;
    public String type;
    public String start;
    public String end;
    public String title;
    public String description;
    public String nb_place;
    public String color;
    public String confirm_owner;
    public String confirm_maker;
    public String id_owner;
    public String id_maker;
    public String rating_event;
    public String rating_student;
    public String duration;
    public List<Login> logins = new ArrayList<>();
    public Right rights = new Right();
    public Calendar calendar = new Calendar();
    public Owner owner = new Owner();
    public Maker maker = new Maker();

    private static class Owner {
        public String picture;
        public String title;
        public String login;
    }

    private static class Maker {
        public String title;
        public String login;
    }

    private static class Login {
        public String login;
        public String picture;
        public String title;
        public String promo;
        public String present;
    }

    private static class Right {
        public String planning_visible;
    }

    private static class Calendar {
        public String id;
        public String start;
        public String end;
        public String type;
        public String registered;
    }
}
