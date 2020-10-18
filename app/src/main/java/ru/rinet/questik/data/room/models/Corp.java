
package ru.rinet.questik.data.room.models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index("corp_id")})
public class Corp implements Parcelable {

    @SerializedName("account")
    @Expose
    private String mAccount;
    @SerializedName("id")
    @Expose
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "corp_id")
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

    public Corp() {
        super();
    }


    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
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

    protected Corp(Parcel in) {
        mAccount = in.readString();
        mId = in.readLong();
        mMail = in.readString();
        mName = in.readString();
        mPhone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAccount);
        dest.writeLong(mId);
        dest.writeString(mMail);
        dest.writeString(mName);
        dest.writeString(mPhone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Corp> CREATOR = new Creator<Corp>() {
        @Override
        public Corp createFromParcel(Parcel in) {
            return new Corp(in);
        }

        @Override
        public Corp[] newArray(int size) {
            return new Corp[size];
        }
    };

}
