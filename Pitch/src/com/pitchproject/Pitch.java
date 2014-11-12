package com.pitchproject;

import java.util.ArrayList;

public class Pitch extends Entity {
	
	private String description;
	private User owner;
	private ArrayList<User> teammates;
	private ArrayList<Resource> resources;
	private ArrayList<Bid> bids;
	private ArrayList<Message> feedback;

}
