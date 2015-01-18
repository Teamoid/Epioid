package epitech.epioid.API.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michelantoine on 15/01/15.
 */
public class Information extends EpitechItem {
    public String ip;
    public Board board = new Board(); //TODO Should not be empty...
    public List<History> history = new ArrayList<>();
    public Info infos = new Info();
    public Current current = new Current();

    private static class Board {
        List<Project> projets = new ArrayList<>();
        List<Note> notes = new ArrayList<>();
        List<Susie> susies = new ArrayList<>();
        List<Activity> activites = new ArrayList<>();
        List<Module> modules = new ArrayList<>();
        List<Stage> stages = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();

        private static class Project {
            public String title;
            public String title_link;
            public String timeline_start;
            public String timeline_end;
            public String timeline_barre;
            public String date_inscription;
            public String id_activite;
            public String soutenance_name;
            public String soutenance_link;
            public String soutenance_date;
            public String soutenance_salle;
        }

        private static class Note {
            public String title;
            public String title_link;
            public String note;
            public String noteur;
        }

        private static class Susie {
            public String title;
            public String creneau_link;
            public String timeline_start;
            public String timeline_barre;
            public String timeline_end;
            public String salle;
            public String intervenant;
            public String etat;
            public String type;
        }

        private static class Activity {
            public String title;
            public String module;
            public String module_link;
            public String module_code;
            public String title_link;
            public String intervenant;
            public String token;
            public String token_link;
            public String register_link;
        }

        private static class Module {
            public String title;
            public String title_link;
            public String timeline_start;
            public String timeline_end;
            public String timeline_barre;
            public String date_inscription;
        }

        private static class Stage {
            // TODO find JSON
        }

        private static class Ticket {
            // TODO find JSON
        }
    }

    private static class History {
        public String title;
        public User user = new User();
        public String content;
        public String date;
        public String id;
        public String visible;
        public String id_activite;
        public String _class;

        public class User {
            public String picture;
            public String title;
            public String url;
        }
    }

    public static class Info {
        public String id;
        public String login;
        public String title;
        public String email;
        public String internal_email;
        public String lastname;
        public String firstname;
        public String referent_used;
        public String picture;
        public String picture_fun;
        public String email_referent;
        public String pass_referent;
        public String promo;
        public String semester;
        public String uid;
        public String gid;
        public String location;
        public String documents;
        public String userdocs;
        public String shell;
        public String netsoul;
        public String close;
        public String close_reason;
        public String ctime;
        public String mtime;
        public String comment;
        public String id_promo;
        public String id_history;
        public String course_code;
        public String school_code;
        public String school_title;
        public String old_id_promo;
        public String old_id_location;
        public String invited;
        public String studentyear;
        public String admin;
    }

    private static class Current {
        public String active_log;
        public String credits_min;
        public String credits_norm;
        public String credits_obj;
        public String nslog_min;
        public String nslog_norm;
        public String semester_code;
        public String semester_num;
        public String achieved;
        public String failed;
        public String inprogress;
    }
}
