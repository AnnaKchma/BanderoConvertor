package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LanguageSettingsMenu {
    public static ReplyKeyboardMarkup getLanguageSelectionMenu() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        // Creating buttons for language selection
        KeyboardButton buttonUkrainian = new KeyboardButton("Українська");
        KeyboardButton buttonEnglish = new KeyboardButton("English");

        // Creating a list of buttons for each language
        List<KeyboardButton> row1 = Collections.singletonList(buttonUkrainian);
        List<KeyboardButton> row2 = Collections.singletonList(buttonEnglish);

        // Adding the rows to the keyboard
        List<List<KeyboardButton> > keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
