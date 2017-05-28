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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/Management")
public class TicketService {

	TicketDao ticketDao = new TicketDao();
	EventDao eventDao = new EventDao();
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@GET
	@Path("/tickets")
	@Produces(MediaType.APPLICATION_XML)
	public List<Ticket> getTickets() {
		return ticketDao.getAllTickets();
	}

	@POST
	@Path("/buy_ticket")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createEvent(@FormParam("ticketId") int ticketId, @FormParam("eventId") int eventId,
			@FormParam("ticketHolder") String ticketHolder, @FormParam("ticketTransferable") String ticketTransferable,
			@FormParam("ticketRefundable") String ticketRefundable, @Context HttpServletResponse servletResponse)
					throws IOException {
		Ticket ticket = new Ticket(ticketId, eventId, ticketHolder, ticketTransferable, ticketRefundable);
		int result = 0;
		// verificare ca evenimentul pentru care este creat biletul sa existe
		if (eventDao.getEvent(eventId) != null) {
			result = ticketDao.addTicket(ticket);
		}
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@GET
	@Path("/ticket_details")
	@Produces(MediaType.APPLICATION_XML)
	public Ticket getTicket(@QueryParam("ticketId") int ticketId) {
		return ticketDao.getTicket(ticketId);
	}

	@POST
	@Path("/cancel_ticket")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cancelTicket(@QueryParam("ticketId") int ticketId) {
		int result = ticketDao.deleteTicket(ticketId);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
}
