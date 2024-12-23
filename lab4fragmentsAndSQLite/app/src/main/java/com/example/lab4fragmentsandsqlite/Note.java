package com.example.lab4fragmentsandsqlite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Note implements Parcelable {
    private String article;
    private String name;
    private String description;


    public Note(String article, String name, String description){
        this.article = article;
        this.name = name;
        this.description = description;

    }

    protected Note(Parcel in) {
        article = in.readString();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getArticle() {
        return article;
    }
    public String getName() {
        return name;
    }
    public String getDescription(){return description;}

    public void setArticle(String article) {
        this.article = article;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(article);
        dest.writeString(name);
        dest.writeString(description);
    }
}
