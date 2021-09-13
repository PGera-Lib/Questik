
package ru.rinet.questik.data.room.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.rinet.questik.data.room.converters.DepartmentConverter;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = Departament.class,parentColumns = "departament_id", childColumns = "mDepartament")},indices = {@Index("user_id")})
public class User  implements Parcelable {
    @SerializedName("Departament")
    @Expose
    private Long mDepartament;
    @PrimaryKey (autoGenerate = true)
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "user_id")
    private Long mId;
    @SerializedName("mail")
    @Expose
    private String mMail;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("phone")
    @Expose
    private String mPhone;
    @SerializedName("uid")
    @Expose
    private String mUid;
    @SerializedName("password")
    @Expose
    private String mPassword;



    public User() {
        super();
    }


    public Long getDepartament() {
        return mDepartament;
    }

    public void setDepartament(Long departament) {
        mDepartament = departament;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        mMail = mail;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public String getPassword() {
        return mPassword;
    }
    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    protected User(Parcel in) {
        mDepartament = in.readLong();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mMail = in.readString();
        mName = in.readString();
        mPhone = in.readString();
        mUid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mDepartament);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mMail);
        dest.writeString(mName);
        dest.writeString(mPhone);
        dest.writeString(mUid);
    }

    @Override
    public int describeContents() {
        return 0;
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
    public String toString() {
        return mName;
    }
}
