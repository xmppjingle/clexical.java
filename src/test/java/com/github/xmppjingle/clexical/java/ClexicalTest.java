package com.github.xmppjingle.clexical.java;

import junit.framework.TestCase;

/**
 * Created by thiago on 6/24/16.
 */
public class ClexicalTest extends TestCase{

    public void testComposeKey(){
        final Predicate p = new Predicate("1", "", "a", new Predicate.Action(Predicate.Action.Type.verb,"try"));
        p.getAdjectives().putIfAbsent("t", "u");
        assertEquals("try1u", Clexical.composeKey(p));
    }

    public void testComposeEmptyKey(){
        final Predicate p = new Predicate("1", "", "a", new Predicate.Action(Predicate.Action.Type.verb,"try"));
        p.getAdjectives().putIfAbsent("t", "u");
        assertEquals("try1", Clexical.composeEmptyKey(p));
    }

}
