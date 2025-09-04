package com.entrega1.trabajo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "referee")
public class Referee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @Column(name = "password", nullable = false, length = 70)
    private String password;

    @Column(name = "special", nullable = false, length = 70)
    private String special;

    @Column(name = "league", nullable = false, length = 70)
    private String league;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;


    //---------------------Constructor---------------------

    public Referee() {

    }

    public Referee(String name, String password, String special, String league, byte[] photo) {
        this.name = name;
        this.password = password;
        this.special = special;
        this.league = league;
        this.photo = photo;

    }

    //---------------------Getters y Setters---------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }



}
