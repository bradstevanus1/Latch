package com.brad.latch.events;

@FunctionalInterface
public interface EventHandler {

    boolean onEvent(Event event);

}
