package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationSender {
    private final ScheduledExecutorService scheduler;

    public NotificationSender() {
        // Create a scheduler
        scheduler = Executors.newScheduledThreadPool(1);

        // Schedule notifications at specific times (e.g., 9:00 AM)
        scheduleNotification();
    }

    private void scheduleNotification() {
        // Calculate the delay until the next occurrence of the specified time
        long initialDelay = calculateInitialDelay();

        scheduler.scheduleAtFixedRate(() -> {
            // Send the notification to users here
        }, initialDelay, 24, TimeUnit.HOURS);
    }

    private long calculateInitialDelay() {
        // Calculate the initial delay until the specified time
        long currentMillis = System.currentTimeMillis();
        long targetMillis = getTimeInMillis();

        // Calculate the delay in milliseconds
        long initialDelay = targetMillis - currentMillis;

        if (initialDelay < 0) {
            // If the specified time has already passed today, add 24 hours to schedule for tomorrow
            initialDelay += TimeUnit.HOURS.toMillis(24);
        }

        return initialDelay;
    }

    private long getTimeInMillis() {
        long currentTime = System.currentTimeMillis();
        long currentHour = TimeUnit.MILLISECONDS.toHours(currentTime);
        long currentMinute = TimeUnit.MILLISECONDS.toMinutes(currentTime - TimeUnit.HOURS.toMillis(currentHour));

        long targetTime = TimeUnit.HOURS.toMillis(9) + TimeUnit.MINUTES.toMillis(0);

        // Calculate the target time in milliseconds
        return currentTime - TimeUnit.HOURS.toMillis(currentHour) - TimeUnit.MINUTES.toMillis(currentMinute) + targetTime;
    }

    public static void main(String[] args) {
        new NotificationSender();
    }
}