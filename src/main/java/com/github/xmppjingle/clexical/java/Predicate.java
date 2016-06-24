package com.github.xmppjingle.clexical.java;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by thiago on 6/24/16.
 *
 -type kind() :: preposition
 |verb.
 -record(predicate, {
 id = <<>> :: binary(),
 subject = <<>> :: binary(),
 action :: {kind(), binary()},
 adjectives = undefined :: dict()
 |undefined,
 abstract = undefined :: any(),
 author = undefined :: any()
 }).

 */

public class Predicate {

    private String id;
    private String subject;
    private String author;
    private Action action;
    private ConcurrentSkipListMap adjectives = new ConcurrentSkipListMap<String, String>();

    public Predicate(String id, String subject, String author, Action action) {
        this.id = id;
        this.subject = subject;
        this.author = author;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public ConcurrentMap getAdjectives() {
        return adjectives;
    }

    public static class Action{

        public enum Type{
            preposition, verb
        }

        private Type type;
        private String name;

        public Action(final Type type, final String name) {
            this.type = type;
            this.name = name;
        }

        public Type getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

}
