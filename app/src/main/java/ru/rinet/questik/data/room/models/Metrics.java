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

@Entity(indices = {@Index("metrics_id")})
public class Metrics implements Parcelable {

    @SerializedName("id")
    @Expose
    @NonNull
    @ColumnInfo(name = "metrics_id")
    @PrimaryKey(autoGenerate = true)
    private Long mId;
    @SerializedName("name")
    @Expose
    private String mName;

    public Metrics() {
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

    protected Metrics(Parcel in) {
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

    public static final Creator<Metrics> CREATOR = new Creator<Metrics>() {
        @Override
        public Metrics createFromParcel(Parcel in) {
            return new Metrics(in);
        }

        @Override
        public Metrics[] newArray(int size) {
            return new Metrics[size];
        }
    };
}
