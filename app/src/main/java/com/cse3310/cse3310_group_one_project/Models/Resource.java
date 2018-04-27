package com.cse3310.cse3310_group_one_project.Models;

public class Resource {

    String resource_food;
    String resource_drink;
    String resource_ent;
    int event_id;

    public Resource(String resource_food,String resource_drink, String resource_ent, int event_id) {
        this.resource_food = resource_food;
        this.resource_drink = resource_drink;
        this.resource_ent = resource_ent;
        this.event_id = event_id;
    }
    public Resource(){

    }

    public String getResource_food() {
        return resource_food;
    }

    public void setResource_food(String resource_type) {
        this.resource_food = resource_type;
    }

    public String getResource_drink() {
        return resource_drink;
    }

    public void setResource_drink(String resource_type) {
        this.resource_drink = resource_type;
    }

    public String getResource_ent() {
        return resource_ent;
    }

    public void setResource_ent(String resource_type) {
        this.resource_ent = resource_type;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

}
