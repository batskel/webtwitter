/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dbatskel
 */
public class FactoryTopNews {

    private static HashMap<String, String> map = new HashMap<String, String>();

    static {
        map.put("twitter:title", "title");
        map.put("og:title", "title");

        map.put("og:description", "description");
        map.put("twitter:description", "description");

        map.put("twitter:image", "image");
        map.put("twitter:image:src", "image");
        map.put("og:image", "image");
    }

    private static String getHTML(String url) {
        try {
            String html = "";
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                html += inputLine + "\n";
            }
            in.close();
            return html;
        } catch (Exception ex) {
            System.err.println("------------\n Error with formation HTML \n ------------");
            return "";
        }

    }

    public static HashMap<String, String> getMapTwitt(String url) {
        Matcher mat = null;
        HashMap<String, String> map2 = new HashMap<String, String>();
        for (String str : getHTML(url).split("\n")) {
            for (String key : map.keySet()) {
                if (str.contains(key)) {
                    mat = Pattern.compile("content=\"([^\"])+").matcher(str);
                    if (mat.find()) {
                        if (map.get(key).equals("title")) {
                            map2.put("title", mat.group().substring(9));
                        } else if (map.get(key).equals("description")) {
                            map2.put("description", mat.group().substring(9));
                        } else if (map.get(key).equals("image")) {
                            if (str.contains(".jpg")|| str.contains(".png") ||str.contains(".jpeg")) {
                                if (str.contains(":large")) {
                                    map2.put("image", mat.group().substring(9, (mat.group().length() - ":large".length())));
                                } else {
                                    map2.put("image", mat.group().substring(9));
                                }
                            }
                        }
                    }

                }
            }
        }
        return map2;
    }

    public static String getNews(String text) {
        Matcher mat;
        boolean index = true;
        while (index) {
            mat = Pattern.compile("https:[/]+([^\\s])+").matcher(text);
            if (mat.find()) {
                text = text.replace(mat.group(), "");
            } else {
                index = false;
            }
        }
        return text;
    }

}
