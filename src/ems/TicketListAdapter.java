package ems;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.*;

public class TicketListAdapter implements JsonSerializer<ArrayList<Ticket>>, JsonDeserializer<ArrayList<Ticket>>{
	
	@Override
	public JsonElement serialize(ArrayList<Ticket> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        for(Ticket ticket: src) {
        	JsonObject jsonObject = new JsonObject();
        	jsonObject.add("type", new JsonPrimitive(ticket.getClass().getSimpleName()));
        	jsonObject.add("properties", context.serialize(ticket, ticket.getClass()));
        	jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
	
	@Override
    public ArrayList<Ticket> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        JsonArray jsonArray = json.getAsJsonArray();
        for(JsonElement jsonElement: jsonArray) {
        	JsonObject jsonObject = jsonElement.getAsJsonObject();
        	String type = jsonObject.get("type").getAsString();
            JsonElement properties = jsonObject.get("properties");
            try {
            	tickets.add(context.deserialize(properties, Class.forName("ems." + type)));
            } catch (ClassNotFoundException e) {
                throw new JsonParseException("Unknown element type: " + type, e);
            }
        }
        return tickets;
    }

}