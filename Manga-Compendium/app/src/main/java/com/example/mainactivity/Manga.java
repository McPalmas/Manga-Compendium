package com.example.mainactivity;

import androidx.annotation.NonNull;

public class Manga {

    private Integer id;
    private String img;
    private String title;
    private String author;
    private String publisher;
    private String magazine;
    private String genre;
    private Integer volumes;
    private Integer chapters;
    private Integer year;
    private String plot;

    public Manga(Integer id, String title, String author, Integer year, String publisher, String magazine, String genre, Integer volumes, Integer chapters, String plot, String img ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher=publisher;
        this.magazine = magazine;
        this.genre=genre;
        this.volumes=volumes;
        this.chapters = chapters;
        this.plot=plot;
        this.img=img;
        //new Manga( title,  author,  publisher,  magazine,  genre,  volumes,  plot,  img );
    }

    public Manga( String title, String author, Integer year, String publisher, String magazine, String genre, Integer volumes, Integer chapters, String plot, String img ) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.magazine = magazine;
        this.genre = genre;
        this.volumes = volumes;
        this.chapters = chapters;
        this.plot = plot;
        this.img=img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String image) {
        img = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public Integer getVolumes() {
        return volumes;
    }

    public void setVolumes(Integer volumes) {
        this.volumes = volumes;
    }

    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

}



