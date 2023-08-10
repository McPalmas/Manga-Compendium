package com.example.mainactivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Thread {
    private Integer id;
    private String title;
    private String description;
    private String image;
    private Integer id_creator;
    private Date timeStamp;


    public Thread(Integer id, String image, String title, String description, Integer id_creator, String timeStamp){
        this.id=id;
        this.image=image;
        this.description=description;
        this.title=title;
        this.id_creator=id_creator;
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.timeStamp = formatter.parse(timeStamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Thread(String image, String title, String description, Integer id_creator){
        this.image=image;
        this.title=title;
        this.description=description;
        this.id_creator=id_creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

}
