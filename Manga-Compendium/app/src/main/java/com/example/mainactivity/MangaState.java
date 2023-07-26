package com.example.mainactivity;

public class MangaState extends Manga {

    private Integer state;

    public MangaState(Integer id, String title, String author, Integer year,String publisher, String magazine, String genre, Integer volumes, Integer chapters, String plot, String img, Integer state) {
        super(id, title, author, year,publisher, magazine, genre, volumes, chapters, plot, img);
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
