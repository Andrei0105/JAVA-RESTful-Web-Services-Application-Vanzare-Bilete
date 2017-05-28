package com.ticketmanagement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
	private static final String user = "user";
	private static final String password = "user";
	private MongoClientURI connectionString = new MongoClientURI(
			"mongodb://" + user + ":" + password + "@ds151661.mlab.com:51661/ubisapp");
	private MongoClient mongoClient = new MongoClient(connectionString);
	private MongoDatabase database = mongoClient.getDatabase("ubisapp");

	public MongoConnection() {
	}

	public MongoCollection<Document> getConnection(String type) {
		if (type == "events") {
			return database.getCollection("events");
		} else if (type == "tickets") {
			return database.getCollection("tickets");
		}
		return null;
	}

}
