package com.example.listadapter;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private String article;
    private String name;
    private float cost;
    private boolean check = false;

    public Product(String article, String name, float cost){
        this.article = article;
        this.name = name;
        this.cost = cost;

    }

    protected Product(Parcel in) {
        article = in.readString();
        name = in.readString();
        cost = in.readFloat();
        check = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getArticle() {
        return article;
    }
    public String getName() {
        return name;
    }
    public float getCost() {
        return cost;
    }
    public boolean getCheck() {
        return check;
    }
    public void setArticle(String article) {
        this.article = article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(article);
        dest.writeString(name);
        dest.writeFloat(cost);
        dest.writeByte((byte) (check ? 1 : 0));
    }
}
