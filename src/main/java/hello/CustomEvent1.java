package hello;

/**
 * Created by laci on 3/2/2015.
 */
public class CustomEvent1 {
    private String eventName;
    private String eventContent;

    public CustomEvent1(String eventName, String eventContent) {
        this.eventName = eventName;
        this.eventContent = eventContent;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventContent() {
        return eventContent;
    }

    @Override
    public String toString() {
        return "CustomEvent1 {" +
                "eventName='" + eventName + '\'' +
                ", eventContent='" + eventContent + '\'' +
                '}';
    }
}