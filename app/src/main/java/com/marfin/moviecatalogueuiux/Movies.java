package com.marfin.moviecatalogueuiux;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;


public class Movies implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String photo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.photo);
    }


    private Movies(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.photo = in.readString();
    }
    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }
        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    Movies(JSONObject object) {
        try {
            int id = object.getInt("id");
            String name = object.getString("title");
            String description = object.getString("overview");
            String photo = object.getString("poster_path");

            this.id = id;
            this.name = name;
            this.description = description;
            this.photo = photo;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
