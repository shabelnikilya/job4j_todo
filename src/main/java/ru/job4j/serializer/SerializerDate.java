package ru.job4j.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerializerDate implements JsonSerializer<Date> {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("HH:mm dd-MMMM-yyyy");

    @Override
    public JsonElement serialize(Date date, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(FORMATTER.format(date));
    }
}
