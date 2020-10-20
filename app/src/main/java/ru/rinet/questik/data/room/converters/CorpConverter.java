package ru.rinet.questik.data.room.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;


import java.util.List;


import ru.rinet.questik.data.room.models.Corp;


public class CorpConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromOptionValuesList(List<Corp> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Corp>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<Corp> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Corp>>() {
        }.getType();
        List<Corp> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

}