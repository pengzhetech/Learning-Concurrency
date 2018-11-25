package com.javaman.concurrency.book.detail.chapter5;

import java.util.LinkedList;

/**
 * @author pengzhe
 * @date 2018-11-24 21:41
 * @description
 */

public class EventQueue {

    private final int max;

    static class Event {
    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private static final Object LOCK = new Object();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                try {
                    console("the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                try {
                    console("the queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            eventQueue.notify();
            Event event = eventQueue.removeFirst();
            console("the event" + event + "is handled");
            return event;
        }
    }

    private void console(String message) {
        System.out.printf("%s:%s\n", Thread.currentThread().getName(), message);
    }
}
