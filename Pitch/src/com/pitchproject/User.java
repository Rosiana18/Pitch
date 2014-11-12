package com.pitchproject;

import java.util.ArrayList;

import com.google.appengine.api.images.Image;

public class User extends Entity {

	private ArrayList<String> strikes;
	private ArrayList<String> ipaddress;
	private ArrayList<String> pitches;
	private ArrayList<Bid> bids;
	private ArrayList<String> friends;
	private Image Picture;
	private ArrayList<String> skills;
	private String missionStatement;

}
