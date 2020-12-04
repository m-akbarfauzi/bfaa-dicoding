package com.icm.githubusers;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int avatar;
    private String username;
    private String Name;
    private String location;
    private String repository;
    private String company;
    private String followers;
    private String following;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.avatar);
        dest.writeString(this.Name);
        dest.writeString(this.username);
        dest.writeString(this.location);
        dest.writeString(this.repository);
        dest.writeString(this.company);
        dest.writeString(this.followers);
        dest.writeString(this.following);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.avatar = in.readInt();
        this.Name = in.readString();
        this.username = in.readString();
        this.location = in.readString();
        this.repository = in.readString();
        this.company = in.readString();
        this.followers = in.readString();
        this.following = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
