package com.example.mainactivity;

public class Thread {
    private Integer id;
    private String title;
    private String image;
    private Integer id_creator;
    private Integer id_msg;

    public Thread(Integer id, String image, String title, Integer id_creator, Integer id_msg){
        this.id=id;
        this.image=image;
        this.title=title;
        this.id_creator=id_creator;
        this.id_msg=id_msg;
    }

    public Thread(String image, String title, Integer id_creator, Integer id_msg){
        this.image=image;
        this.title=title;
        this.id_creator=id_creator;
        this.id_msg=id_msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId_creator() {
        return id_creator;
    }

    public void setId_creator(Integer id_creator) {
        this.id_creator = id_creator;
    }

    public Integer getId_msg() {
        return id_msg;
    }

    public void setId_msg(Integer id_msg) {
        this.id_msg = id_msg;
    }
}
