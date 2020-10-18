package ru.rinet.questik.data.room.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithJob extends Category {
    @Relation(parentColumn = "category_id", entityColumn = "mCategory", entity = Job.class)
    public List<Job> jobs;
}

