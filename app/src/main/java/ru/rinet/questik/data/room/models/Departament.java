
package ru.rinet.questik.data.room.models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index("departament_id")})
public class Departament implements Parcelable {

    @SerializedName("id")
    @Expose
    @NonNull
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "departament_id")
    private Long mId;
    @SerializedName("name")
    @Expose
    private String mName;

    public Departament() {
        super();
    }


    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    protected Departament(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Departament> CREATOR = new Creator<Departament>() {
        @Override
        public Departament createFromParcel(Parcel in) {
            return new Departament(in);
        }

        @Override
        public Departament[] newArray(int size) {
            return new Departament[size];
        }
    };
}
