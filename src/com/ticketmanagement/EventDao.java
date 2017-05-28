package com.ticketmanagement;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class EventDao {
	public List<Event> getAllEvents() {
		List<Event> eventList = new ArrayList<Event>();
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("events");
		MongoCursor<Document> cursor = mc.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				int eventId = doc.get("eventId", Integer.class);
				String eventLocation = doc.get("eventLocation", String.class);
				String eventName = doc.get("eventName", String.class);
				String eventPerformer = doc.get("eventPerformer", String.class);
				Event e = new Event(eventId, eventLocation, eventName, eventPerformer);
				eventList.add(e);

			}
		} finally {
			cursor.close();
		}

		return eventList;
	}
	
	public Event getEvent(int eventId) {
		Event e = null;
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("events");
		Document doc = mc.find(new Document("eventId", new Document("$eq", eventId))).first();
		if (doc != null) {
			String eventLocation = doc.get("eventLocation", String.class);
			String eventName = doc.get("eventName", String.class);
			String eventPerformer = doc.get("eventPerformer", String.class);
			e = new Event(eventId, eventLocation, eventName, eventPerformer);
		}
		return e;
	}

	private void saveEventList(List<Event> eventList) {
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("events");
		for (Event event : eventList) {
			Document doc = new Document("eventId", event.getEventId()).append("eventLocation", event.getEventLocation())
					.append("eventName", event.getEventName()).append("eventPerformer", event.getEventPerformer());
			mc.insertOne(doc);
		}
	}
	
	private void saveEvent(Event newEvent)
	{
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("events");
		Document doc = new Document("eventId", newEvent.getEventId()).append("eventLocation", newEvent.getEventLocation())
				.append("eventName", newEvent.getEventName()).append("eventPerformer", newEvent.getEventPerformer());
		mc.insertOne(doc);
	}


	public int addEvent(Event newEvent) {
		List<Event> eventList = getAllEvents();
		boolean eventExists = false;
		for (Event event : eventList) {
			if (event.getEventId() == newEvent.getEventId()) {
				eventExists = true;
				break;
			}
		}
		if (!eventExists) {
			saveEvent(newEvent);
			return 1;
		}
		return 0;
	}
}
