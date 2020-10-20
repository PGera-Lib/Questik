
package ru.rinet.questik.data.room.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;

import static androidx.room.ForeignKey.CASCADE;


/*@Entity(tableName = "Job",
        foreignKeys ={
                @ForeignKey(entity = Project.class, parentColumns = "mId", childColumns = "mProjectId")
        })*/

@Entity(foreignKeys = {@ForeignKey(entity = Project.class,parentColumns = "project_id", childColumns = "mProjectId", onDelete = CASCADE),
        @ForeignKey(entity = Metrics.class,parentColumns = "metrics_id", childColumns = "mMetrics"),
        @ForeignKey(entity = Category.class,parentColumns = "category_id", childColumns = "mCategory")},
        indices = {@Index("job_id"), @Index("mProjectId"), @Index("mMetrics"), @Index("mCategory")})
public class Job implements Parcelable {

    @SerializedName("category")
    @Expose
    @ColumnInfo(name = "mProjectId")
    private Long mProjectId;
    @SerializedName("category")
    @Expose
    @ColumnInfo(name = "mCategory")
    private Long mCategory;
    @SerializedName("count")
    @Expose
    private String mCount;
    @SerializedName("discount")
    @Expose
    private String mDiscount;
    @SerializedName("id")
    @Expose
    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "job_id")
    private Long mId;
    @SerializedName("metrics")
    @Expose
    @ColumnInfo(name = "mMetrics")
    private Long mMetrics;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("price")
    @Expose
    private String mPrice;



    public Job() {
        super();
    }



    public Long getProjectId() {
        return mProjectId;
    }

    public void setProjectId(Long mProjectId) {
        this.mProjectId = mProjectId;
    }

    public Long getCategory() {
        return mCategory;
    }

    public void setCategory(Long mCategory) {
        this.mCategory = mCategory;
    }

    public String getCount() {
        return mCount;
    }

    public void setCount(String mCount) {
        this.mCount = mCount;
    }

    public String getDiscount() {
        return mDiscount;
    }

    public void setDiscount(String mDiscount) {
        this.mDiscount = mDiscount;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public Long getMetrics() {
        return mMetrics;
    }

    public void setMetrics(Long mMetrics) {
        this.mMetrics = mMetrics;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mProjectId);
        dest.writeLong(mCategory);
        dest.writeString(mCount);
        dest.writeString(mDiscount);
        dest.writeLong(mId);
        dest.writeLong(mMetrics);
        dest.writeString(mName);
        dest.writeString(mPrice);
    }
    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            Job job = new Job();
            job.mProjectId = in.readLong();
            job.mCategory = in.readLong();
            job.mCount = in.readString();
            job.mDiscount = in.readString();
            job.mId = in.readLong();
            job.mMetrics = in.readLong();
            job.mName = in.readString();
            job.mPrice = in.readString();
            return job;
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
}
