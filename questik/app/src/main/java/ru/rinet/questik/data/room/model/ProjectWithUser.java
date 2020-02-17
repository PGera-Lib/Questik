package ru.rinet.questik.data.room.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProjectWithUser {
    @Embedded
    public Project project;
    @Relation(parentColumn = "_id", entityColumn = "project_id")
    public List<Users> users;
}


