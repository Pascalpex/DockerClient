package net.pascalpex.controller;

import com.google.gson.Gson;
import net.pascalpex.model.Event;
import net.pascalpex.model.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeAggregator {

    private final String input;
    private Map<String, HashMap<String, Long>> eventStarts;

    private Map<String, Long> times;

    public TimeAggregator(String input) {
        this.input = input;
        eventStarts = new HashMap<>();
        times = new HashMap<>();
        loadEvents();
    }

    private void loadEvents() {
        Gson gson = new Gson();
        Events events = gson.fromJson(input, Events.class);
        List<Event> eventList = events.getEvents();
        for(Event event : eventList) {
            if (event.getEventType().equalsIgnoreCase("start")) {
                HashMap<String, Long> eventMap = eventStarts.getOrDefault(event.getCustomerId(), new HashMap<>());
                eventMap.put(event.getWorkloadId(), event.getTimestamp());
                eventStarts.put(event.getCustomerId(), eventMap);
            }
        }
        for(Event event : eventList) {
            if(event.getEventType().equalsIgnoreCase("stop")) {
                Long startTime = eventStarts.get(event.getCustomerId()).get(event.getWorkloadId());
                Long stopTime = event.getTimestamp();
                Long diff = stopTime - startTime;
                Long current = times.getOrDefault(event.getCustomerId(), 0L);
                times.put(event.getCustomerId(), current + diff);
            }
        }
    }

    public Map<String, Long> getTimes() {
        return times;
    }
}
