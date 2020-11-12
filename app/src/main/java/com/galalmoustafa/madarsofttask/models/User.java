package com.galalmoustafa.madarsofttask.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User implements Parcelable {

    @PrimaryKey
    @NonNull
    int id;
    String name;
    String age;
    String gender;
    String job_title;

    public User() {
    }

    public User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readString();
        gender = in.readString();
        job_title = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(gender);
        dest.writeString(job_title);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
}
