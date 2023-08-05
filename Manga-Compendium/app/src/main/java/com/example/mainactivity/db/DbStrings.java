package com.example.mainactivity.db;

public class DbStrings
{

    public static class User {
        public static final String TABLE = "user";
        public static final String FIELD_ID = "_id";

        public static final String USERNAME = "username";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String IMAGE = "image";

    }



    public static class Manga {
        public static final String TABLE = "manga";
        public static final String FIELD_ID = "_id";
        public static final String TITLE = "title";
        public static final String AUTHOR = "author";
        public static final String YEAR = "year";
        public static final String PUBLISHER = "publisher";
        public static final String MAGAZINE = "magazine";
        public static final String GENRE = "genre";
        public static final String VOLUMES = "volumes";
        public static final String CHAPTERS = "chapters";
        public static final String PLOT = "plot";
        public static final String IMAGE = "image";

    }

    public enum State {
        NON_LETTO("Non Letto"),
        IN_CORSO("In Corso"),
        LETTO("Letto")
        ;

        private final String text;

        State(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static class UserManga {
        public static final String TABLE = "libreria";
        public static final String FIELD_ID_USER = "id_user";
        public static final String FIELD_ID_MANGA = "id_manga";
        public static final String FIELD_STATE_MANGA = "state_manga";

    }

    public static class Thread {
        public static final String TABLE = "thread";
        public static final String ID = "_id";
        public static final String IMAGE = "image";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String ID_USER_CREATOR = "id_user_creator";
    }


    public static class Message{
        public static final String TABLE = "message";
        public static final String ID = "_id";
        public static final String TEXT = "text";
        public static final String ID_USER = "id_user";
        public static final String DATE = "date";
        public static final String ID_THREAD = "id_thread";
    }


    public static class UserThread {
        public static final String TABLE = "userThread";
        public static final String ID_USER = "id_user";
        public static final String ID_THREAD = "id_thread";
    }


}
