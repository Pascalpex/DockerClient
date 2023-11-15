package net.pascalpex.model;

/**
 * The Event class is a data class used by the Events class which stores all the information about one event
 */
public class Event {

    private String customerId;
    private String workloadId;
    private long timestamp;
    private String eventType;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "customerId: " + customerId + ", workloadId: " + workloadId + ", timestamp: " + timestamp + ", eventType: " + eventType;
    }
}
