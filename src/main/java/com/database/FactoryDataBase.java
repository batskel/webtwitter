/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import com.database.News;
import com.database.Posttelegram;
import com.serializable.ModifyObject;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import twitter4j.Status;

/**
 *
 * @author dbatskel
 */
public class FactoryDataBase {

    public static void writeInNews(List<News> list) {
        Session session = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        for (News news : list) {
            session.merge(news);
        }
        session.getTransaction().commit();
        session.flush();
       session.clear();
       session.close();
    }

    public static void writeInNews(Posttelegram post) {
        Session session = null;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(post);
        session.getTransaction().commit();
        session.flush();
        session.clear();
        session.close();
    }

    public static Status getStatusID(String id) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(
                    "from News where id =:id");
            query.setParameter("id", id);
            return (Status) ModifyObject.getObject(((News) query.list().get(0)).getObject());
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("---- not find id Usersecurity ---");
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.clear();
            session.close();
        }
    }

    public static List readIdFromNews() {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(
                    "select id from News order by id desc");
//            query.setDate(string, date);
            query.setMaxResults(30);
            return query.list();
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("---- not find id Usersecurity ---");
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.clear();
            session.close();
        }
    }

    public static List readAllNews() {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            Query query = session.createQuery("from News where day(date) =  day(current_date()) and  (hour(date) = hour(current_time()) or hour(date) = hour(current_time())-1) order by id desc");
            return query.list();
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("---- not find id Usersecurity ---");
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.clear();
            session.close();
        }
    }

    public static void updateNews(List<News> list) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            for (News news : list) {
                News news1 = (News) session.merge(news);
                session.saveOrUpdate(news1);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.clear();
            session.close();
        }
    }

}
