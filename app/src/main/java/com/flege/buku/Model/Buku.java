package com.flege.buku.Model;

import com.google.gson.annotations.SerializedName;

public class Buku {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("author_name")
    private String author_name;

    public Buku(){}

    public Buku(String id, String title, String author_name) {
        this.id = id;
        this.title = title;
        this.author_name = author_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String nama) {
        this.title = nama;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
