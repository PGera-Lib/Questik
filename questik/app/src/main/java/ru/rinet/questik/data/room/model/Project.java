package ru.rinet.questik.data.room.model;

import android.os.*;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;

import java.util.*;


@Entity
public class Project implements Parcelable {
	public static final int ADAPTER_ITEM_TYPE = 0;
	@PrimaryKey
    private int _id;
    private String name;
	private String user;
	private String client;
	private String phone;
	private String mail;
	private LocalDate startDate;
	private LocalDate endDate;
	private int color;
	@Ignore
	private List<Job> joblist;
	@Ignore
	private boolean isSelected;

    

    public Project() {
        super();
    }


	protected Project(Parcel in) {
		_id = in.readInt();
		name = in.readString();
		user = in.readString();
		client = in.readString();
		phone = in.readString();
		mail = in.readString();
		color = in.readInt();
		joblist = in.createTypedArrayList(Job.CREATOR);
		isSelected = in.readByte() != 0;
	}



	public void setIsSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}

	public boolean isSelected()
	{
		return isSelected;
	}

	public void setId(int _id)
	{
		this._id = _id;
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

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getUser()
	{
		return user;
	}

	public void setClient(String client)
	{
		this.client = client;
	}

	public String getClient()
	{
		return client;
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
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getColor() {
		return color;
	}

	/**
	 * Sets the task color.
	 *
	 * @param color task color.
	 */

	public void setColor(int color) {
		this.color = color;
	}

/*	@Override
	public int getType() {
		return ADAPTER_ITEM_TYPE;
	}*/

	public void setJoblist(List<Job> joblist)
	{
		this.joblist = joblist;
	}

	public List<Job> getJoblist()
	{
		return joblist;
	}
	public void toogle() {
        this.isSelected = !this.isSelected;
	}

    @Override
    public String toString() {
        return name;
    }

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
		Project other = (Project) obj;
		if (_id != other._id)
			return false;
		return true;
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
		parcel.writeString(getUser());
		parcel.writeString(getClient());
		parcel.writeString(getPhone());
		parcel.writeString(getMail());
		parcel.writeValue(getStartDate());
		parcel.writeValue(getEndDate());
		//parcel.writeBoolean(isSelected());
		parcel.writeTypedList(joblist);
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

	/*@Override
	public int getType() {
		return ADAPTER_ITEM_TYPE;
	}*/
}
