package ru.rinet.questik.data.retrofit.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ru.rinet.questik.data.room.models.Job;

public class MyJobDeserializer<J> implements JsonDeserializer<Job>
{
    @Override
    public Job deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        // Get the "content" element from the parsed JSON
        JsonElement content = je.getAsJsonObject().get("Job");

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return new Gson().fromJson(content, Job.class);

    }
}