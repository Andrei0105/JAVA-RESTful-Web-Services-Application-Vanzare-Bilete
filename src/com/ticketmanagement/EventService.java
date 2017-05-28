package com.ticketmanagement;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/Management")
public class EventService {

	EventDao eventDao = new EventDao();
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@GET
	@Path("/list_events")
	@Produces(MediaType.APPLICATION_XML)
	public List<Event> getEvents() {
		return eventDao.getAllEvents();
	}

	@POST
	@Path("/events")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createEvent(@FormParam("eventId") int eventId, @FormParam("eventName") String eventName,
			@FormParam("eventLocation") String eventLocation, @FormParam("eventPerformer") String eventPerformer,
			@Context HttpServletResponse servletResponse) throws IOException {
		Event event = new Event(eventId, eventName, eventLocation, eventPerformer);
		int result = eventDao.addEvent(event);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
}
