package ru.rinet.questik.data.room.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Job implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "job_id")
    private int _id;
    private String name;
    private double price;
    private int count;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "metrics_id")
    private int metricsId;
    @ColumnInfo(name = "project_id")
    private int projectId;
    @Ignore
    private boolean isSelected;

    public Job() {
        super();
    }


    private Job(Parcel in) {
        super();
        this._id = in.readInt();
        this.name = in.readString();
        this.price = in.readDouble();
        this.count = in.readInt();
        this.categoryId = in.readInt();
        this.metricsId = in.readInt();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getMetricsId() {
        return metricsId;
    }

    public void setMetricsId(int metricsId) {
        this.metricsId = metricsId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void toogle() {
        this.isSelected = !this.isSelected;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int code = this.name.hashCode() + (int) this.price;
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Job other = (Job) obj;
        if (_id != other._id)
            return false;
        //  return true;
        return obj instanceof Job &&
                this.name.equals(((Job) obj).name) &&
                this.price == ((Job) obj).price &&
                this.count == ((Job) obj).count &&
                this.categoryId == ((Job) obj).categoryId &&
                this.metricsId == ((Job) obj).metricsId;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
        parcel.writeDouble(getPrice());
        parcel.writeInt(getCount());
        parcel.writeInt(getCategoryId());
        parcel.writeInt(getMetricsId());
    }
    public static final Creator<Job> CREATOR = new Creator<Job>() {
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

}
