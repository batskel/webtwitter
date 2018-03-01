/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threads;

import com.telegram.Bot;
import com.telegram.InitializerBot;
import com.twiter.FactoryTopNews;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import twitter4j.Status;

/**
 *
 * @author dbatskel
 */
public class UpdateTelegram implements Runnable {

    private Status status;
    private String groupId = "-1001304354897";

    HashMap<String, String> map = new HashMap<>();

    public UpdateTelegram(Status status) {
        this.status = status;
    }

    public void startBot() {
        Bot bot = InitializerBot.getBot();
        whatSend(bot);
    }

    private void whatSend(Bot bot) {
        try {
            if (!status.getText().equals("")) {
                if (status.getMediaEntities().length > 0 && status.getMediaEntities()[0].getDisplayURL() != null) { // фото 
                    SendPhoto message = getPhoto(status.getMediaEntities()[0].getDisplayURL(), FactoryTopNews.getNews(status.getText()));
                    if (status.getURLEntities().length > 0 && status.getURLEntities()[0].getExpandedURL() != null) { // фото+ ссылка
                        message.setReplyMarkup(getReplyKeyboard(status.getURLEntities()[0].getExpandedURL()));
                    }
                    bot.sendPhoto(message);
                } else { //Если нету фото
                    SendMessage message = null;
                    if (status.getURLEntities().length > 0 && status.getURLEntities()[0].getExpandedURL() != null) {
                        map = FactoryTopNews.getMapTwitt(status.getURLEntities()[0].getExpandedURL());
                        SendPhoto photo = getPhoto(map.get("image"), FactoryTopNews.getNews(status.getText()));
                        message = getMessagehtml(map.get("title"), map.get("description"));
                        message.setReplyMarkup(getReplyKeyboard(status.getURLEntities()[0].getExpandedURL()));
                        bot.sendPhoto(photo);
                        bot.sendMessage(message);
                    } else {
                        bot.sendMessage(getMessage(status.getText()));
                    }
                }
            }
        } catch (TelegramApiException ex) {
            System.err.println("----------\n Error with "+status.getId()+"\n----------");
//            Logger.getLogger(UpdateTelegram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        startBot();
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    public ReplyKeyboard getReplyKeyboard(String url) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Читать всю новость").setUrl(url));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    public SendPhoto getPhoto(String url, String caption) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(Long.parseLong(groupId));
        photo.setPhoto(url);
        if (caption.length() > 200) {
            caption = caption.subSequence(0, 197) + "...";
        }
        photo.setCaption(caption);
        return photo;
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    public SendMessage getMessage(String text) {
        SendMessage s = new SendMessage();
        s.setChatId(Long.parseLong(groupId)); // TestTwitterNews
        s.setText(text);
        return s;
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    public SendMessage getMessagehtml(String title, String description) {
        SendMessage s = new SendMessage();
        s.setParseMode("HTML");
        s.setChatId(Long.parseLong(groupId)); // TestTwitterNews
        s.setText("<b>" + title + "</b>"
                + "\n"
                + FactoryTopNews.getNews(description));
        return s;
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    public SendPhoto getPhotoWithText(HashMap<String, String> mapLink) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(Long.parseLong(groupId));
        photo.setPhoto(map.get("twitter:image"));
        String capture = mapLink.get("text");
        photo.setCaption(capture);
//        photo.se
        return photo;
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    public SendPhoto getTESTPhoto(HashMap<String, String> mapLink) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(Long.parseLong(groupId));
        photo.setPhoto(map.get("twitter:image"));
//        capture = capture.subSequence(0, 197) + "...";
        photo.setCaption("ASDASDASDASDA SASDASDASD ASDASDASDASDASDASD");
        return photo;
    }
}
