package com.ticketmanagement;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	private int eventId;
	private String eventName;
	private String eventLocation;
	private String eventPerformer;

	public Event(){}

	public Event(int eventId, String eventName, String eventLocation, String eventPerformer){
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventLocation = eventLocation;
		this.eventPerformer = eventPerformer;
	}

	public int getEventId() {
		return eventId;
	}
	
	@XmlElement
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	@XmlElement
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventLocation() {
		return eventLocation;
	}
	
	@XmlElement
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public String getEventPerformer() {
		return eventPerformer;
	}
	
	@XmlElement
	public void setEventPerformer(String eventPerformer) {
		this.eventPerformer = eventPerformer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		result = prime * result + ((eventLocation == null) ? 0 : eventLocation.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((eventPerformer == null) ? 0 : eventPerformer.hashCode());
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
		Event other = (Event) obj;
		if (eventId != other.eventId)
			return false;
		if (eventLocation == null) {
			if (other.eventLocation != null)
				return false;
		} else if (!eventLocation.equals(other.eventLocation))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventPerformer == null) {
			if (other.eventPerformer != null)
				return false;
		} else if (!eventPerformer.equals(other.eventPerformer))
			return false;
		return true;
	}
	
	
}
