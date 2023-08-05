package com.example.mainactivity;

public class Message {
    private Integer id;
    private String text;
    private Integer id_user;
    private Integer id_thread;
    private String date;

    public Message(Integer id, String text, Integer id_user, Integer id_thread, String date){
        this.id=id;
        this.text=text;
        this.id_user=id_user;
        this.id_thread=id_thread;
        this.date=date;
    }

    public Message(String text, Integer id_user,Integer id_thread, String date){
        this.text=text;
        this.id_user=id_user;
        this.id_thread=id_thread;
        this.date=date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId_thread() {
        return id_thread;
    }

    public void setId_thread(Integer id_thread) {
        this.id_thread = id_thread;
    }
}
