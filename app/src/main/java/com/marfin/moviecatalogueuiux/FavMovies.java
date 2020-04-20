package com.marfin.moviecatalogueuiux;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.marfin.moviecatalogueuiux.DatabaseContract.getColumnInt;
import static com.marfin.moviecatalogueuiux.DatabaseContract.getColumnString;

public class FavMovies implements Parcelable {
    private int id;
    private String name;
    private String desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.desc);
        dest.writeString(this.photo);
    }

    public FavMovies () {

    }

    public FavMovies(int id, String title, String overview, String posterpath) {
        this.id = id;
        this.name = title;
        this.desc = overview;
        this.photo = posterpath;
    }

    public FavMovies(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.name = getColumnString(cursor, DatabaseContract.FavMovColumns.NAME);
        this.desc = getColumnString(cursor, DatabaseContract.FavMovColumns.DESC);
        this.photo = getColumnString(cursor, DatabaseContract.FavMovColumns.PHOTO);
    }

    private FavMovies(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.desc = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<FavMovies> CREATOR = new Parcelable.Creator<FavMovies>() {
        @Override
        public FavMovies createFromParcel(Parcel source) {
            return new FavMovies(source);
        }

        @Override
        public FavMovies[] newArray(int size) {
            return new FavMovies[size];
        }
    };
}
