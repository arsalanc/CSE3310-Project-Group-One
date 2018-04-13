package com.cse3310.cse3310_group_one_project;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Arsalan on 4/12/2018.
 */

public class Event {


    //Probably need to change certain data types
    int event_id;
    int party_size;
    Date date;
    Time time;
    double duration; //hours
    String Meal_type;
    String Meal_venue;
    String Formality;
    String Drink_venue;
    String Hall;

    //Default constructor
    public Event(){

    }
    // Getters and Setters
    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
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
