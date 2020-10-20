package ru.rinet.questik.data.room.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;

public class JobsConverter {
    @TypeConverter
    public static List<Job> getJobs(String mediaListString) {
        Type myType = new TypeToken<List<Job>>() {}.getType();
        return new Gson().fromJson(mediaListString, myType);
    }
    @TypeConverter
    public static String getJobs(List<Job> mediaItems) {
        if (mediaItems== null || mediaItems.size() == 0) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Job>>() {
        }.getType();
        String json = gson.toJson(mediaItems, type);
        return json;
    }
}
