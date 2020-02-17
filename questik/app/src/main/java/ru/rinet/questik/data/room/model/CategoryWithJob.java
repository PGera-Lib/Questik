package ru.rinet.questik.data.room.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithJob {
    @Embedded
    public Category category;

    @Relation(parentColumn = "_id", entityColumn = "category_id")
    public List<Job> jobs;

}

