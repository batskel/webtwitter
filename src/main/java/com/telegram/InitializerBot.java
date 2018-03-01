/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author dbatskel
 */
public class InitializerBot {
    
    private  static  Bot bot = null;
    
    private static synchronized Bot Initializer (){
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            Bot bot = new Bot();
            botapi.registerBot(new Bot());
            return bot;
            
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    } 
    
    
    public static synchronized Bot getBot(){
        if (bot==null){
            bot = Initializer();
        }
        return bot;
    }
    
}
