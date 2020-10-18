package ru.rinet.questik.data.room.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.util.List;

import ru.rinet.questik.data.room.models.Departament;


public class DepartmentConverter {
    @TypeConverter
    public static List<Departament> getDepartament(String mediaListString) {
        Type myType = new TypeToken<List<Departament>>() {}.getType();
        return new Gson().fromJson(mediaListString, myType);
    }
    @TypeConverter
    public static String getDepartament(List<Departament> mediaItems) {
        if (mediaItems== null || mediaItems.size() == 0) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Departament>>() {
        }.getType();
        String json = gson.toJson(mediaItems, type);
        return json;
    }
}
