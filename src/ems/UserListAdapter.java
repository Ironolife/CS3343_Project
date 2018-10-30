package ems;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.*;

public class UserListAdapter implements JsonSerializer<ArrayList<User>>, JsonDeserializer<ArrayList<User>>{
	
	@Override
	public JsonElement serialize(ArrayList<User> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        for(User user: src) {
        	JsonObject jsonObject = new JsonObject();
        	jsonObject.add("type", new JsonPrimitive(user.getClass().getSimpleName()));
        	jsonObject.add("properties", context.serialize(user, user.getClass()));
        	jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
	
	@Override
    public ArrayList<User> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		ArrayList<User> users = new ArrayList<User>();
        JsonArray jsonArray = json.getAsJsonArray();
        for(JsonElement jsonElement: jsonArray) {
        	JsonObject jsonObject = jsonElement.getAsJsonObject();
        	String type = jsonObject.get("type").getAsString();
            JsonElement properties = jsonObject.get("properties");
            try {
                users.add(context.deserialize(properties, Class.forName("ems." + type)));
            } catch (ClassNotFoundException e) {
                throw new JsonParseException("Unknown element type: " + type, e);
            }
        }
        return users;
    }

}