package us.ferox.client.api.event;

import git.littledraily.eventsystem.cancellable.Cancellable;
import git.littledraily.eventsystem.event.EventState;

public class Event extends Cancellable {
    private EventState eventState = EventState.PRE;

    public Event() {}

    public Event(EventState eventState) {
        this.eventState = eventState;
    }

    public EventState getEventState() {
        return eventState;
    }
}
