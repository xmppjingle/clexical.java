package com.github.xmppjingle.clexical.java;

import java.util.ArrayList;

/**
 * Created by thiago on 6/24/16.
 */
public class Letter {

    public enum Type{
        decree, bulletin
    }
    private String author;
    private Type type;
    private ArrayList predicates = new ArrayList<Predicate>();

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayList<Predicate> getPredicates() {
        return predicates;
    }

}
