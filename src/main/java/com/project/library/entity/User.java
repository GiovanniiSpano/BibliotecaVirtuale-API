package com.project.library.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @NonNull
    @Column(name="NAME")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Book> booksBorrowed;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, List<Book> booksBorrowed) {
        this.name = name;
        this.booksBorrowed = booksBorrowed;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksBorrowed() {
        return this.booksBorrowed;
    }

    public void setBooksBorrowed(List<Book> booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

}
