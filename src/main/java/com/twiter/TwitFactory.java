/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author dbatskel
 */
public class TwitFactory {

    private static final String consumerKey = "EzNPgxnbX4STHqEvrpxynfpEr";
    private static final String consumerSecret = "2reQYGlpDkEW9tJjYn5XY8p75LMXEzmWAqpE2OTGc5Qx9EigmP";
    private static final String accessToken = "965857967844265985-pS7x9XJzvyidc7bVsBMXfMl1QSpF8Qu";
    private static final String accessTokenSecret = "wqdhz3BL6CjB2kGXbSB4AG2grKC2pYHaQ0qWM7X4Ik3am";
    private static Twitter twitt;

    private static Twitter createTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    public static  Twitter getTwitter() {
        if (twitt == null) {
            twitt = createTwitter();
        }
        return twitt;
    }

}
