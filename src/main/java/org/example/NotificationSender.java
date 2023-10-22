package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class NotificationSender extends TelegramLongPollingBot {
    private final ScheduledExecutorService scheduler;
    private long chatId;
    private ScheduledFuture<?> notificationTask;

    public NotificationSender() {
        scheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String userText = update.getMessage().getText();
            chatId = update.getMessage().getChatId();

            if (userText.equals("/start")) {
                sendNotificationMenu();
            } else if (userText.equals("/stop") || (isStopCommand(update))) {
                stopNotifications();
            }
        }
    }

    private boolean isStopCommand(Update update) {
        return update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("stop_notifications");
    }

    private void sendNotificationMenu() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (String day : new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}) {
            InlineKeyboardButton button = new InlineKeyboardButton(day).setCallbackData(day.toUpperCase());
            rows.add(Collections.singletonList(button));
        }

        for (int hour = 9; hour < 19; hour++) {
            InlineKeyboardButton button = new InlineKeyboardButton(hour + ":00").setCallbackData("TIME_" + hour);
            rows.add(Collections.singletonList(button));
        }

        InlineKeyboardButton stopButton = new InlineKeyboardButton("Stop Notifications").setCallbackData("stop_notifications");
        rows.add(Collections.singletonList(stopButton));

        keyboardMarkup.setKeyboard(rows);

        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("Select the day and time for notifications (9:00 AM - 6:00 PM):")
                .setReplyMarkup(keyboardMarkup);

        executeAndHandleExceptions(message);

        scheduleCurrencyRateNotifications();
    }

    private void stopNotifications() {
        if (notificationTask != null && !notificationTask.isDone()) {
            notificationTask.cancel(false);
        }
    }

    private void scheduleCurrencyRateNotifications() {
        notificationTask = scheduler.scheduleAtFixedRate(this::sendCurrencyRateNotification, 0, 1, TimeUnit.MINUTES);
    }

    private void sendCurrencyRateNotification() {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("Here is the latest currency rate information.");
        executeAndHandleExceptions(message);
    }

    private void executeAndHandleExceptions(SendMessage message) {
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "bandero_converter_bot";
    }

    @Override
    public String getBotToken() {
        return "6805411508:AAFSxU76lQliK8Qb87xNI0YjrAlZsoNLmE4";2
    }

    public static void main(String[] args) {
        try {
            new NotificationSender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}