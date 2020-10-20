
package ru.rinet.questik.data.room.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import ru.rinet.questik.data.room.converters.CorpConverter;
import ru.rinet.questik.data.room.converters.JobsConverter;
import ru.rinet.questik.data.room.converters.LocalDateTypeConverter;
import ru.rinet.questik.data.room.converters.UserConverter;

import static androidx.room.ForeignKey.CASCADE;

//, foreignKeys = @ForeignKey(entity = Job.class,parentColumns = "mId", childColumns = "")
@Entity(foreignKeys = {@ForeignKey(entity = Corp.class,parentColumns = "corp_id", childColumns = "mCorp"),
        @ForeignKey(entity = User.class,parentColumns = "user_id", childColumns = "mUser")},
        indices = {@Index("project_id"), @Index("mCorp"), @Index("mUser")})
public class Project implements Parcelable {


    @SerializedName("Corp")
    @Expose
   // @Embedded(prefix = "Corp")
    @ColumnInfo(name = "mCorp")
    private Long mCorp;
    @SerializedName("dataOff")
    @Expose
   // @TypeConverters(LocalDateTypeConverter.class)
    private String mDataOff;
    @SerializedName("dataOn")
    @Expose
   // @TypeConverters(LocalDateTypeConverter.class)
    private String mDataOn;
    @SerializedName("id")
    @Expose
    @NonNull
    @ColumnInfo(name = "project_id")
    @PrimaryKey (autoGenerate = true)
    private Long mId;
    @SerializedName("Jobs")
    @Expose
    @TypeConverters(JobsConverter.class)
    private List<Job> mJobs;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("uid")
    @Expose
    private String mUid;
    @SerializedName("User")
    @Expose
   // @Embedded(prefix = "User")
    @ColumnInfo(name = "mUser")
    private Long mUser;

    public Project() {
       super();
    }

    public String getDataOff() {
        return mDataOff;
    }

    public void setDataOff(String mDataOff) {
        this.mDataOff = mDataOff;
    }

    public String getDataOn() {
        return mDataOn;
    }

    public void setDataOn(String mDataOn) {
        this.mDataOn = mDataOn;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public List<Job> getJobs() {
        return mJobs;
    }

    public void setJobs(List<Job> mJobs) {
        this.mJobs = mJobs;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }

    public Long getCorp() {
        return mCorp;
    }

    public void setCorp(Long mCorp) {
        this.mCorp = mCorp;
    }

    public Long getUser() {
        return mUser;
    }

    public void setUser(Long mUser) {
        this.mUser = mUser;
    }

    protected Project(Parcel in) {
        mCorp = in.readLong();
        mDataOff = in.readString();
        mDataOn = in.readString();
        mId = in.readLong();
        mJobs = in.createTypedArrayList(Job.CREATOR);
        mName = in.readString();
        mUid = in.readString();
        mUser = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mCorp);
        dest.writeString(mDataOff);
        dest.writeString(mDataOn);
        dest.writeLong(mId);
        dest.writeTypedList(mJobs);
        dest.writeString(mName);
        dest.writeString(mUid);
        dest.writeLong(mUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return mId == project.mId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
