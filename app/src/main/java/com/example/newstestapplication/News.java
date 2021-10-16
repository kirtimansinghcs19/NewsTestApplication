package com.example.newstestapplication;

public class News  {
    public String url;
    public String urlToImage;
    public String author;
    public String title;

    public News(String title, String author, String url, String urlToImage) {
        this.url = url;
        this.urlToImage = urlToImage;
        this.author = author;
        this.title = title;
    }
    public News(){}
}
