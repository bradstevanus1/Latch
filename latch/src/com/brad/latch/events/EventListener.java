package com.brad.latch.events;

@FunctionalInterface
public interface EventListener {

    void onEvent(Event event);

}
