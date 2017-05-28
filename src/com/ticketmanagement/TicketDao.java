package com.ticketmanagement;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class TicketDao {
	public List<Ticket> getAllTickets() {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("tickets");
		MongoCursor<Document> cursor = mc.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				int ticketId = doc.get("ticketId", Integer.class);
				int eventId = doc.get("eventId", Integer.class);
				String ticketHolder = doc.get("ticketHolder", String.class);
				String ticketRefundable = doc.get("ticketRefundable", String.class);
				String ticketTransferable = doc.get("ticketTransferable", String.class);
				Ticket t = new Ticket(ticketId, eventId, ticketHolder, ticketTransferable, ticketRefundable);
				ticketList.add(t);

			}
		} finally {
			cursor.close();
		}

		return ticketList;
	}
	
	public Ticket getTicket(int ticketId) {
		Ticket t = null;
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("tickets");
		Document doc = mc.find(new Document("ticketId", new Document("$eq", ticketId))).first();
		if(doc!=null)
		{
			int eventId = doc.get("eventId", Integer.class);
			String ticketHolder = doc.get("ticketHolder", String.class);
			String ticketRefundable = doc.get("ticketRefundable", String.class);
			String ticketTransferable = doc.get("ticketTransferable", String.class);
			t = new Ticket(ticketId, eventId, ticketHolder, ticketTransferable, ticketRefundable);
		}
		return t;
	}

	private void saveTicketList(List<Ticket> ticketList) {
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("tickets");
		for (Ticket ticket : ticketList) {
			Document doc = new Document("ticketId", ticket.getTicketId()).append("eventId", ticket.getEventId())
					.append("ticketHolder", ticket.getTicketHolder())
					.append("ticketTransferable", ticket.getTicketTransferable())
					.append("ticketRefundable", ticket.getTicketRefundable());
			mc.insertOne(doc);
		}
	}

	public int addTicket(Ticket newTicket) {
		List<Ticket> ticketList = getAllTickets();
		boolean ticketExists = false;
		for (Ticket ticket : ticketList) {
			if (ticket.getTicketId() == newTicket.getTicketId()) {
				ticketExists = true;
				break;
			}
		}
		if (!ticketExists) {
			saveTicket(newTicket);
			return 1;
		}
		return 0;
	}

	private void saveTicket(Ticket newTicket)
	{
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("tickets");
		Document doc = new Document("ticketId", newTicket.getTicketId()).append("eventId", newTicket.getEventId())
				.append("ticketHolder", newTicket.getTicketHolder())
				.append("ticketTransferable", newTicket.getTicketTransferable())
				.append("ticketRefundable", newTicket.getTicketRefundable());
		mc.insertOne(doc);
	}
	

	public int deleteTicket(int ticketId) {
		List<Ticket> ticketList = getAllTickets();
		MongoCollection<Document> mc = (new MongoConnection()).getConnection("tickets");
		for (Ticket ticket : ticketList) {
			if (ticket.getTicketId() == ticketId) {
				Bson filter = new Document("ticketId", new Document("$eq", ticketId));
				mc.deleteOne(filter);
				return 1;
			}
		}
		return 0;
	}
}
