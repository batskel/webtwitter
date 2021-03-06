package com.database;
// Generated Mar 1, 2018 1:28:15 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * News generated by hbm2java
 */
@Entity
@Table(name="news"
    ,catalog="twitter01"
)
public class News  implements java.io.Serializable {


     private String id;
     private Date date;
     private Integer likes;
     private Integer twitts;
     private byte[] object;

    public News() {
    }

	
    public News(String id) {
        this.id = id;
    }
    public News(String id, Date date, Integer likes, Integer twitts, byte[] object) {
       this.id = id;
       this.date = date;
       this.likes = likes;
       this.twitts = twitts;
       this.object = object;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=20)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", length=19)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    
    @Column(name="likes")
    public Integer getLikes() {
        return this.likes;
    }
    
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    
    @Column(name="twitts")
    public Integer getTwitts() {
        return this.twitts;
    }
    
    public void setTwitts(Integer twitts) {
        this.twitts = twitts;
    }

    
    @Column(name="object")
    public byte[] getObject() {
        return this.object;
    }
    
    public void setObject(byte[] object) {
        this.object = object;
    }




}


