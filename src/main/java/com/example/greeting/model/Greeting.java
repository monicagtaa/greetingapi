package com.example.greeting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.sql.Timestamp;

//marks this as the entity for creating our table in database
@Entity
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String createdBy;
    private String greeting;
    private String originCountry;
    private Timestamp dateCreated;

    public Greeting(String createdBy, String greeting, String originCountry) {
        this.createdBy = createdBy;
        this.greeting = greeting;
        this.originCountry = originCountry;
        this.dateCreated = new Timestamp(new Date().getTime());
    }


//java beans...
    public Greeting() {
        this.dateCreated = new Timestamp(new Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", greeting='" + greeting + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
