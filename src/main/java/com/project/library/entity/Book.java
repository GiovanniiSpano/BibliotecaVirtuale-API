package com.project.library.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @NonNull
    @Column(name="TITLE")
    private String title;

    @NonNull
    @Column(name="AUTHOR")
    private String author;

    @NonNull
    @Column(name="GENRE")
    private String genre;

    @NonNull
    @Column(name="PUBLISHED_YEAR")
    private Integer publishedYear;

    @NonNull
    @Column(name="NUM_AVAILABLE")
    private Integer numAvailable = 1;

    @NonNull
    @Column(name="IS_AVAILABLE")
    private Boolean isAvailable = true;    

    @ManyToOne(optional=true)
    @JoinColumn(name = "USER_ID", referencedColumnName="ID")
    private User user;

    public Book() {
    }

    public Book(String title, String author, String genre, Integer publishedYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublishedYear() {
        return this.publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Integer getNumAvailable() {
        return this.numAvailable;
    }

    public void setNumAvailable(Integer numAvailable) {
        this.numAvailable = numAvailable;
    }

    public Boolean isIsAvailable() {
        return this.isAvailable;
    }

    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        if (this.numAvailable == 0) {
            this.isAvailable = false;
        }

        this.isAvailable = isAvailable;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
