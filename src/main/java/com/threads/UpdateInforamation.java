/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threads;

import com.database.FactoryDataBase;
import com.database.News;
import com.serializable.ModifyObject;
import com.twiter.TwitFactory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author dbatskel
 */
public class UpdateInforamation implements Runnable {

    private List<News> lNews;

    private void setUpNewInformation() {
        Twitter twit = TwitFactory.getTwitter();
        for (News lNew : lNews) {
            try {
                Status stut = twit.showStatus(Long.parseLong(lNew.getId()));
                lNew.setLikes(stut.getFavoriteCount());
                lNew.setTwitts(stut.getRetweetCount());
            } catch (Exception ex) {
                try {
                    Status t = (Status) ModifyObject.getObject(lNew.getObject());
                    Status stut = twit.showStatus(t.getId());
                    System.err.println("-------------- \n\n Error with id of :" + lNew.getId() + "\n\n--------------");
                } catch (TwitterException ex1) {
                }
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(90000);
                Thread.sleep(1);
                lNews = FactoryDataBase.readAllNews();
                setUpNewInformation();
                FactoryDataBase.updateNews(lNews);
                System.out.println("--------\n Update is done, size: "+lNews.size()+" \n---------");
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateInforamation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
