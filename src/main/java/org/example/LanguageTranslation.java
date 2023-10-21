package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class LanguageTranslation extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String userText = message.getText();

            // Translate the user's text to different languages
            String translatedTextUkrainian = translateText(userText, "uk");
            String translatedTextEnglish = translateText(userText, "en");

            // Create a response with translated text
            StringBuilder responseText = new StringBuilder("Translations:\n");
            responseText.append("Ukrainian: ").append(translatedTextUkrainian).append("\n");
            responseText.append("English: ").append(translatedTextEnglish).append("\n");

            SendMessage response = new SendMessage()
                    .setChatId(message.getChatId())
                    .setText(responseText.toString());

            try {
                execute(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String translateText(String text, String targetLanguage) {
        // Implement translation logic here, e.g., using a translation service or library
        return "Translated text in " + targetLanguage + ": " + text;
    }

    @Override
    public String getBotUsername() {
        return "YourCurrencyConverterBot";
    }

    @Override
    public String getBotToken() {
        return "YOUR_BOT_TOKEN";
    }
}}