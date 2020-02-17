package ru.rinet.questik.data.room.model;

import androidx.room.ColumnInfo;


public class JobFull {
    public String name;
    public double price;
    public int count;
    @ColumnInfo(name = "category_name")
    public String categoryName;
    @ColumnInfo(name = "metrics_name")
    public String metricsName;
    @ColumnInfo(name = "project_name")
    public String projectName;
}
