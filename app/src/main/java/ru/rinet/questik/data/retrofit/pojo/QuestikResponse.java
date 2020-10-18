
package ru.rinet.questik.data.retrofit.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.converters.CorpConverter;
import ru.rinet.questik.data.room.converters.DepartmentConverter;
import ru.rinet.questik.data.room.converters.JobsConverter;
import ru.rinet.questik.data.room.converters.ProjectConverter;
import ru.rinet.questik.data.room.converters.UserConverter;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
@Entity(tableName = "QuestikResponse")
public class QuestikResponse implements Serializable {

    @SerializedName("Corp")
    @Expose
    @TypeConverters(CorpConverter.class)
    private List<Corp> mCorp;
    @SerializedName("Database")
    @Expose
    private String mDatabase;
    @SerializedName("Departament")
    @Expose
    @TypeConverters(DepartmentConverter.class)
    private List<Departament> mDepartament;
    @SerializedName("Job")
    @Expose
    @TypeConverters(JobsConverter.class)
    private List<Job> mJobs;
    @SerializedName("Project")
    @Expose
    @TypeConverters(ProjectConverter.class)
    private List<Project> mProject;
    @SerializedName("releaseDate")
    @Expose
    private String mReleaseDate;
    @SerializedName("User")
    @Expose
    @TypeConverters(UserConverter.class)
    private List<User> mUser;
    @SerializedName("version")
    @Expose
    @PrimaryKey
    @NonNull
    private String mVersion;

    public List<Corp> getCorp() {
        return mCorp;
    }

    public void setCorp(List<Corp> corp) {
        mCorp = corp;
    }

    public String getDatabase() {
        return mDatabase;
    }

    public void setDatabase(String database) {
        mDatabase = database;
    }

    public List<Departament> getDepartament() {
        return mDepartament;
    }

    public void setDepartament(List<Departament> departament) {
        mDepartament = departament;
    }

    public List<Job> getJobs() {
        return mJobs;
    }

    public void setJobs(List<Job> jobs) {
        mJobs = jobs;
    }

    public List<Project> getProject() {
        return mProject;
    }

    public void setProject(List<Project> project) {
        mProject = project;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public List<User> getUser() {
        return mUser;
    }

    public void setUser(List<User> user) {
        mUser = user;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

}
