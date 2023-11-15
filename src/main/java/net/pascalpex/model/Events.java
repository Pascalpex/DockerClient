package net.pascalpex.model;

import java.util.List;

/**
 * The Events class is a data class used to receive a list of events from the server
 */
public class Events {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Event event : events) {
            stringBuilder.append(event.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
