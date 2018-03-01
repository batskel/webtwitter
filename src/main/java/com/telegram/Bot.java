/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telegram;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author dbatskel
 */
public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Administrator";
    }

    @Override
    public void onUpdateReceived(Update e) {
        try {
//                    Message msg = e.getChannelPost(); // Это нам понадобится
//        String txt = msg.getText();
//        if (txt.equals("/start")) {
//            sendMsg(msg, "Hello, world! This is simple bot!");
//        } else {
//            sendMsg(msg, "Dima");
//        }
//            EditMessageText new_message = new EditMessageText().
//                    setChatId(e.getCallbackQuery().getMessage().getChatId())
//                    .setMessageId(e.getCallbackQuery().getMessage().getMessageId())
//                    .setText("re");
        
            ReplyKeyboard t = getReplyKeyboard();
            InlineKeyboardMarkup name = (InlineKeyboardMarkup) t;

            if (name.getKeyboard().get(0).get(0).getText().equalsIgnoreCase(e.getCallbackQuery().getData())) {
                name.getKeyboard().get(0).get(0).setText(name.getKeyboard().get(0).get(0).getText() + "yyy");

            } else if (name.getKeyboard().get(0).get(1).getText().equalsIgnoreCase(e.getCallbackQuery().getData())) {
                name.getKeyboard().get(0).get(1).setText(name.getKeyboard().get(0).get(1).getText() + "yyy");
            }

            EditMessageReplyMarkup edmark = new EditMessageReplyMarkup().
                    setChatId(e.getCallbackQuery().getMessage().getChatId()).
                    setMessageId(e.getCallbackQuery().getMessage().getMessageId()).
                    setReplyMarkup(name);

            editMessageReplyMarkup(edmark);
//            editMessageText(new_message);
            System.out.println("-------------");
        } catch (TelegramApiException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getBotToken() {
        return "514367015:AAH4gGWD_PuH8VdQMXd1Tj662x-KHjQAQBo";
    }
    
    
    
    
    
    
    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    public ReplyKeyboard getReplyKeyboard() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Не нрав").setCallbackData("не нрав"));
        rowInline.add(new InlineKeyboardButton().setText("Нрав").setCallbackData("нрав"));
//        rowInline.add(new InlineKeyboardButton().setText("Нрав").setUrl("vk.com"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

}
