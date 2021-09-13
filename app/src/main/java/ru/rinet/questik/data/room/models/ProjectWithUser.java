package ru.rinet.questik.data.room.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProjectWithUser {
    @Embedded
    public User user;

    @Relation(parentColumn = "user_id", entityColumn = "mUser")
    public List<User> users;
}
