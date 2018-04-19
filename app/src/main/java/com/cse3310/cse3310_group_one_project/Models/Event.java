package com.cse3310.cse3310_group_one_project.Models;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Arsalan on 4/12/2018.
 */

public class Event {

    //Probably need to change certain data types
    int event_id;
    int owner_id;
    int caterer_id = -1; // this won't be determined until a caterer selects the event
    int party_size;
    String date;
    String time;
    int duration; //hours
    String Meal_type;
    String Meal_venue;
    String Formality;
    String Drink_venue;
    String Hall;

    //Default constructor
    public Event(){

    }

    public Event(int owner_id, int party_size, String date, String time, int duration,
                 String meal_type, String meal_venue, String formality, String drink_venue) {
        this.owner_id = owner_id;
        this.party_size = party_size;
        this.date = date;
        this.time = time;
        this.duration = duration;
        Meal_type = meal_type;
        Meal_venue = meal_venue;
        Formality = formality;
        Drink_venue = drink_venue;
    }
    // Getters and Setters
    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getOwner_id() { return this.owner_id; }

    public void setOwner_id(int owner_id) { this.owner_id = owner_id; }

    public int getCaterer_id() { return this.caterer_id; }

    public void setCaterer_id(int caterer_id) { this.caterer_id = caterer_id; }

    public String getHall() {
        return Hall;
    }

    public void setHall(String hall) {
        Hall = hall;
    }

    public int getParty_size() {
        return party_size;
    }

    public void setParty_size(int party_size) {
        this.party_size = party_size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String  getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMeal_type() {
        return Meal_type;
    }

    public void setMeal_type(String meal_type) {
        Meal_type = meal_type;
    }

    public String getMeal_venue() {
        return Meal_venue;
    }

    public void setMeal_venue(String meal_venue) {
        Meal_venue = meal_venue;
    }

    public String getFormality() {
        return Formality;
    }

    public void setFormality(String formality) {
        Formality = formality;
    }

    public String getDrink_venue() {
        return Drink_venue;
    }

    public void setDrink_venue(String drink_venue) {
        Drink_venue = drink_venue;
    }


}
