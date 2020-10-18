package ru.rinet.questik.data.room.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ru.rinet.questik.data.room.models.Category;


public class CategoryConverter {
@TypeConverter
   public static List<Category> getCategory(String mediaListString) {
      Type myType = new TypeToken<List<Category>>() {}.getType();
      return new Gson().fromJson(mediaListString, myType);
   }
   @TypeConverter
   public static String getCategory(List<Category> mediaItems) {
      if (mediaItems== null || mediaItems.size() == 0) {
        return (null);
      }
      Gson gson = new Gson();
      Type type = new TypeToken<List<Category>>() {
      }.getType();
      String json = gson.toJson(mediaItems, type);
      return json;
   }
}
