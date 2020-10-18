package ru.rinet.questik.data.room.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(indices = {@Index("category_id")})
public class Category implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    @SerializedName("id")
    @NonNull
    @Expose
    @ColumnInfo(name = "category_id")
    private Long mId;
    @SerializedName("name")
    @Expose
    private String name;
    @Ignore
    private boolean isSelected;

    public Category() {
        super();
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
	
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            Category category = new Category();
            category.mId = in.readLong();
            category.name = in.readString();
            return category;
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(name);
    }
}
