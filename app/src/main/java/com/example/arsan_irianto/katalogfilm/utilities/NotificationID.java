package com.example.arsan_irianto.katalogfilm.utilities;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by arsan-irianto on 12/19/17.
 */

public class NotificationID {
    private final static AtomicInteger c = new AtomicInteger(0);

    public static int getID() {
        return c.incrementAndGet();
    }
}
