/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threads;

import com.database.News;
import com.serializable.ModifyObject;
import java.sql.Date;
import twitter4j.Status;

/**
 *
 * @author dbatskel
 */
public class AddNews {

    public static News add(Status statu) {
        return new News(Long.toString(statu.getId()),
               statu.getCreatedAt(),
                statu.getFavoriteCount(),
                statu.getRetweetCount(),
                ModifyObject.getByteObject(statu));
    }

}
