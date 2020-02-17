package ru.rinet.questik.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ru.rinet.questik.data.room.model.Category;
import ru.rinet.questik.data.room.model.CategoryDao;
import ru.rinet.questik.data.room.model.Job;
import ru.rinet.questik.data.room.model.JobDao;
import ru.rinet.questik.data.room.model.Metrics;
import ru.rinet.questik.data.room.model.MetricsDao;
import ru.rinet.questik.data.room.model.Project;
import ru.rinet.questik.data.room.model.ProjectDao;
import ru.rinet.questik.data.room.model.Users;
import ru.rinet.questik.data.room.model.UsersDao;
import ru.rinet.questik.utils.LocalDateTypeConverter;

@Database(entities = {Job.class, Category.class, Metrics.class, Project.class, Users.class}, version = 1)
@TypeConverters({LocalDateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract JobDao jobDao();
    public abstract CategoryDao categoryDao();
    public abstract MetricsDao metricsDao();
    public abstract ProjectDao projectDao();
    public abstract UsersDao usersDao();
}

