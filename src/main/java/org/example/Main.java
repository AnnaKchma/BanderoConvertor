package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

public class MainApplication {
    public static void main(String[] args) {
        // Initialize and start the LanguageTranslation bot
        LanguageTranslation languageBot = new LanguageTranslation();
        TelegramBotsApi languageBotApi = new TelegramBotsApi();

        try {
            BotSession languageBotSession = languageBotApi.registerBot(languageBot);
            // You can also use languageBotSession to control the bot's lifecycle.
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // Initialize and start the NotificationSender
        NotificationSender notificationSender = new NotificationSender();
    }
}