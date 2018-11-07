package ems;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.*;

public class TicketAdapter implements JsonSerializer<Ticket>, JsonDeserializer<Ticket>{
	
	@Override
	public JsonElement serialize(Ticket src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
    	jsonObject.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
    	jsonObject.add("properties", context.serialize(src, src.getClass()));
    	return jsonObject;
    }
	
	@Override
    public Ticket deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
    	String type = jsonObject.get("type").getAsString();
        JsonElement properties = jsonObject.get("properties");
        try {
            return context.deserialize(properties, Class.forName("ems." + type));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown element type: " + type, e);
        }
    }

}
