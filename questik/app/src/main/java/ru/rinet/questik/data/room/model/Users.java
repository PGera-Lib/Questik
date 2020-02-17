package ru.rinet.questik.data.room.model;

import android.os.*;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
	private int _id;
    private String name;
	private String phone;
	private String mail;
	private String company;
	private String uid;


	@ColumnInfo(name = "project_id")
	private int projectId;

	private boolean isSelected;


	
	public Users(){
		super();
	}
	public void setId(int id)
	{
		_id = id;
	}

	public int getId()
	{
		return _id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getMail()
	{
		return mail;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getCompany()
	{
		return company;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getUid()
	{
		return uid;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public void setIsSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}

	public boolean isSelected()
	{
		return isSelected;
	}
	private Users(Parcel in) {
        super();
        this._id = in.readInt();
        this.name = in.readString();
		this.phone = in.readString();
		this.mail = in.readString();
		this.company = in.readString();
		this.projectId = in.readInt();
		this.uid = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
		parcel.writeString(getPhone());
		parcel.writeString(getMail());
		parcel.writeString(getCompany());
		parcel.writeInt(getProjectId());
		parcel.writeString(getUid());
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        public Users[] newArray(int size) {
            return new Users[size];
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
        Users other = (Users) obj;
        if (_id != other._id)
            return false;
        return true;
    }
	
	}
