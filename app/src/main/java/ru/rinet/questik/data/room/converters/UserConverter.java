package ru.rinet.questik.data.room.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.util.List;

import ru.rinet.questik.data.room.models.User;

public class UserConverter {
    @TypeConverter
    public static List<User> getUser(String mediaListString) {
        Type myType = new TypeToken<List<User>>() {}.getType();
        return new Gson().fromJson(mediaListString, myType);
    }
    @TypeConverter
    public static String getUser(List<User> mediaItems) {
        if (mediaItems== null || mediaItems.size() == 0) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        String json = gson.toJson(mediaItems, type);
        return json;
    }
}
