/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.database.FactoryDataBase;
import com.database.News;
import com.serializable.ModifyObject;
import java.util.AbstractList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import twitter4j.Status;

/**
 *
 * @author dbatskel
 */
@Named(value = "recurs")
@RequestScoped
public class Recurs {

    private List<News> list;
    private List<Status> listSt = new ArrayList<Status>();
    /**
     * Creates a new instance of Recurs2
     */
    public Recurs() {
        list = FactoryDataBase.readTopNews();
        for (News news : list) {
            Status stat = (Status) ModifyObject.getObject(news.getObject());
            listSt.add(stat);
        }
        System.out.println("------------\n work\n------------");
    }


    /**
     * Creates a new instance of Recurs
     */
    public List<News> getList() {

        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }

    public List<Status> getListSt() {
        return listSt;
    }

    public void setListSt(List<Status> listSt) {
        this.listSt = listSt;
    }

}
