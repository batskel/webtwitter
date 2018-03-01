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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

/**
 *
 * @author dbatskel
 */
public class UpdateDate implements Runnable {

    private int group; // 15687962 marvel  228661749 lenta.ru

    public UpdateDate(int group) {
        this.group = group;
    }

    private void start() {
        int update = 0;
        while (true) {
            long begintime = System.currentTimeMillis();
            System.out.println("Update times is " + (update++));
            Twitter twitter = TwitFactory.getTwitter();
            try {
                List<String> oldNews = FactoryDataBase.readIdFromNews();
                List<Status> status = twitter.getUserListStatuses(Long.parseLong("965906490283511808"), new Paging(1));
                List<News> news = new ArrayList<News>();
                for (Status statu : status) {
                    if (!contain(oldNews, statu)) {
                        news.add(AddNews.add(statu));
                        User use = statu.getUser();
                        System.out.println("---------------------------------");
                        System.out.println(use.getName() + "\n" + statu.getText());
                        System.out.println("---------------------------------");
//                        Thread thr3 = new Thread(new UpdateTelegram(use.getName() + "\n" + statu.getText()));
//                        thr3.start();
                    }
                }
                if (news.size() != 0) {
                    FactoryDataBase.writeInNews(news);
                }
                System.out.println("Time of working is " + (System.currentTimeMillis() - begintime) / 1000 + " sec");
                Thread.sleep(3000);
            } catch (Exception ex) {
                try {
                    ex.printStackTrace();
                    Thread.sleep(10000);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(UpdateDate.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    public static boolean contain(List<String> oldNews, Status statu) {
        for (String oldNew : oldNews) {
            if (Long.toString(statu.getId()).equalsIgnoreCase(oldNew)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        start();
    }
}
