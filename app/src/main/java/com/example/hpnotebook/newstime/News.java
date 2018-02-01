package com.example.hpnotebook.newstime;

/**
 * Created by Hp Notebook on 29-01-2018.
 */

public class News {

    private String section;
    private String heading;
    private String details;
    private String date;
    private String author;
    private String img;
    private String url;

    public News(String section, String heading, String details, String date, String author, String img, String url) {
        this.section = section;
        this.heading = heading;
        this.details = details;
        this.date = date;
        this.author = author;
        this.img = img;
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public String getHeading() {
        return heading;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }
}
