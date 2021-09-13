package ru.rinet.questik.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import javax.inject.Singleton;

import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.dao.CategoryDao;
import ru.rinet.questik.data.room.dao.CorpDao;
import ru.rinet.questik.data.room.dao.DepartmentDao;
import ru.rinet.questik.data.room.dao.JobDao;
import ru.rinet.questik.data.room.dao.MetricsDao;
import ru.rinet.questik.data.room.dao.ProjectDao;
import ru.rinet.questik.data.room.dao.QuestikDao;
import ru.rinet.questik.data.room.dao.UserDao;
import ru.rinet.questik.data.room.models.Category;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Metrics;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;

@Singleton
@Database(entities = {Job.class, Category.class, Project.class, User.class, Corp.class, Departament.class, Metrics.class, QuestikResponse.class}, version = 1)
@TypeConverters({})
//, exportSchema=false
public abstract class AppDatabase extends RoomDatabase {
    public abstract JobDao getJobDao();
    public abstract CategoryDao getCategoryDao();
    public abstract ProjectDao getProjectDao();
    public abstract UserDao getUserDao();
    public abstract CorpDao getCorpDao();
    public abstract DepartmentDao getDepartmentDao();
    public abstract MetricsDao getMetricsDao();
    public abstract QuestikDao getQuestikDao();
}

