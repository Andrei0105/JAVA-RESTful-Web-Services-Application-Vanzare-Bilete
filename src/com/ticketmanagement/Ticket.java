package com.ticketmanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ticket")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ticketId;
	private int eventId;
	private String ticketHolder;
	private String ticketTransferable;
	private String ticketRefundable;

	public Ticket(){}
	
	public Ticket(int ticketId, int eventId, String ticketHolder,
			String ticketTransferable, String ticketRefundable){
		this.ticketId = ticketId;
		this.eventId = eventId;
		this.ticketHolder = ticketHolder;
		this.ticketTransferable = ticketTransferable;
		this.ticketRefundable = ticketRefundable;
	}

	public int getTicketId() {
		return ticketId;
	}

	@XmlElement
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketHolder() {
		return ticketHolder;
	}

	@XmlElement
	public void setTicketHolder(String ticketHolder) {
		this.ticketHolder = ticketHolder;
	}

	public String getTicketTransferable() {
		return ticketTransferable;
	}

	@XmlElement
	public void setTicketTransferable(String ticketTransferable) {
		this.ticketTransferable = ticketTransferable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		result = prime * result + ((ticketHolder == null) ? 0 : ticketHolder.hashCode());
		result = prime * result + ticketId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (eventId != other.eventId)
			return false;
		if (ticketHolder == null) {
			if (other.ticketHolder != null)
				return false;
		} else if (!ticketHolder.equals(other.ticketHolder))
			return false;
		if (ticketId != other.ticketId)
			return false;
		return true;
	}

	public String getTicketRefundable() {
		return ticketRefundable;
	}

	@XmlElement
	public void setTicketRefundable(String ticketRefundable) {
		this.ticketRefundable = ticketRefundable;
	}
	
	@XmlElement
	public int getEventId() {
		return eventId;
	}
	
	
	
}
