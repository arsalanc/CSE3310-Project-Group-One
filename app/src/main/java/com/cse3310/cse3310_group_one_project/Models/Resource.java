package com.cse3310.cse3310_group_one_project.Models;

public class Resource {

    int resource_amount;
    String resource_type;
    int event_id;

    public Resource(int resource_amount, String resource_type, int event_id) {
        this.resource_amount = resource_amount;
        this.resource_type = resource_type;
        this.event_id = event_id;
    }

    public int getResource_amount() {
        return resource_amount;
    }

    public void setResource_amount(int resource_amount) {
        this.resource_amount = resource_amount;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

}
