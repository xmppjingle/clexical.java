package com.github.xmppjingle.clexical.java;

/**
 * Created by thiago on 6/24/16.
 */
public interface Scribe {

    boolean curb(final String key, final Predicate predicate);
    Predicate recall(final String key);

}
