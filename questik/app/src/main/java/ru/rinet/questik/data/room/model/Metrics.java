package ru.rinet.questik.data.room.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {
        @Index("name")})
public class Metrics implements Parcelable {
    @PrimaryKey
    private int _id;
    private String name;

    public Metrics() {
        super();
    }


    private Metrics(Parcel in) {
        super();
        this._id = in.readInt();
        this.name = in.readString();
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "за "+name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
    }

    public static final Creator<Metrics> CREATOR = new Creator<Metrics>() {
        public Metrics createFromParcel(Parcel in) {
            return new Metrics(in);
        }

        public Metrics[] newArray(int size) {
            return new Metrics[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + _id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Metrics other = (Metrics) obj;
        if (_id != other._id)
            return false;
        return true;
    }
}
