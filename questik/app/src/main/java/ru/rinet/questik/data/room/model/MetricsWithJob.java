package ru.rinet.questik.data.room.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

class MetricsWithJob {
    @Embedded
    public Metrics metrics;

    @Relation(parentColumn = "_id", entityColumn = "metrics_id")
    public List<Job> jobs;
}
