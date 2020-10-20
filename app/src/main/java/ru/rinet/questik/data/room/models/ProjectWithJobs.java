package ru.rinet.questik.data.room.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProjectWithJobs extends Project{

    @Relation(parentColumn = "project_id", entityColumn = "mProjectId", entity = Job.class)
    public List<Job> jobs;
}

