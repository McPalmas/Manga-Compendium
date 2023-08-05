package com.example.mainactivity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DBhelper extends SQLiteOpenHelper
    {
        public static final String DBNAME="MANGACOMPENDIUM";
        public DBhelper(Context context) {

            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String q="CREATE TABLE "+ DbStrings.User.TABLE+
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbStrings.User.USERNAME+" TEXT," +
                    DbStrings.User.EMAIL+" TEXT," +
                    DbStrings.User.PASSWORD+" TEXT," +
                    DbStrings.User.IMAGE+" TEXT)";
            db.execSQL(q);

             q="CREATE TABLE "+ DbStrings.Manga.TABLE+
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbStrings.Manga.TITLE+" TEXT," +
                    DbStrings.Manga.AUTHOR+" TEXT," +
                    DbStrings.Manga.YEAR+" INTEGER," +
                    DbStrings.Manga.PUBLISHER+" TEXT," +
                    DbStrings.Manga.MAGAZINE+" TEXT," +
                    DbStrings.Manga.GENRE+" TEXT," +
                    DbStrings.Manga.VOLUMES+" INTEGER," +
                    DbStrings.Manga.CHAPTERS+" INTEGER," +
                    DbStrings.Manga.PLOT+" TEXT," +
                    DbStrings.Manga.IMAGE+" TEXT)";
            db.execSQL(q);

            db.execSQL("CREATE TABLE "+ DbStrings.UserManga.TABLE+
                    " ( id_user INTEGER ," +
                    DbStrings.UserManga.FIELD_ID_MANGA+" INTEGER," +
                    DbStrings.UserManga.FIELD_STATE_MANGA+" INTEGER," +
                    "FOREIGN KEY (id_manga) REFERENCES " +DbStrings.Manga.TABLE + " (_id),"+
                    "FOREIGN KEY (id_user) REFERENCES " +DbStrings.User.TABLE + " (_id))");


            db.execSQL("CREATE TABLE "+ DbStrings.Thread.TABLE+
                    " ( " + DbStrings.Thread.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbStrings.Thread.IMAGE+" TEXT," +
                    DbStrings.Thread.TITLE+" TEXT," +
                    DbStrings.Thread.DESCRIPTION+" TEXT," +
                    DbStrings.Thread.ID_USER_CREATOR +" INTEGER)");


            db.execSQL("CREATE TABLE "+ DbStrings.Message.TABLE+
                    " ( " + DbStrings.Message.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbStrings.Message.TEXT+" TEXT," +
                    DbStrings.Message.ID_THREAD+" INTEGER," +
                    DbStrings.Message.ID_USER+" INTEGER," +
                    DbStrings.Message.DATE+" TEXT," +
                    "FOREIGN KEY (id_thread) REFERENCES " +DbStrings.Thread.TABLE + " (_id))");

            db.execSQL("CREATE TABLE "+ DbStrings.UserThread.TABLE+
                    " (" + DbStrings.UserThread.ID_USER+" INTEGER," +
                    DbStrings.UserThread.ID_THREAD+" TEXT," +
                    "FOREIGN KEY (id_user) REFERENCES " +DbStrings.User.TABLE + " (_id),"+
                    "FOREIGN KEY (id_thread) REFERENCES " +DbStrings.Thread.TABLE + " (_id))");

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        { }
    }

