package com.github.xmppjingle.clexical.java;

import java.util.ArrayList;

/**
 * Created by thiago on 6/24/16.
 */
public interface Herald {
    void proclaim(final Letter letter);

    Letter letterFromBinary(final String str);

    String toBinary(final Letter letter);

    ArrayList<Predicate> excerpts(Predicate predicate);
}