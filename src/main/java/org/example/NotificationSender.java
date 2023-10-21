package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;

public class NotificationSender {
    // Create a scheduler
    private final ScheduledExecutorService scheduler;

    public NotificationSender() {
        // Create a scheduler
        scheduler = Executors.newScheduledThreadPool(1);

        // Schedule notifications at 9:00 AM every working day
        scheduleNotifications();
    }

    private void scheduleNotifications() {
        scheduler.scheduleAtFixedRate(() -> {
            // Get the current time
            Calendar now = Calendar.getInstance();
            int currentHour = now.get(Calendar.HOUR_OF_DAY);
            int currentMinute = now.get(Calendar.MINUTE);
            int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);


            if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY &&
                    currentHour == 9 && currentMinute == 0) {
                // Send the notification to users here
                System.out.println("Sending notification at 9:00 AM");
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        try {
            new NotificationSender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
