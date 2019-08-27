package net.kathir.myapplication.LiveDataWithLocalDB;

public class NameModel {

    public long mId;
    public String mUrl;
    public long mDate;

    public NameModel(long id, String name, long date) {
        mId = id;
        mUrl = name;
        mDate = date;
    }

    public NameModel(NameModel favourites) {
        mId = favourites.mId;
        mUrl = favourites.mUrl;
        mDate = favourites.mDate;
    }
}
