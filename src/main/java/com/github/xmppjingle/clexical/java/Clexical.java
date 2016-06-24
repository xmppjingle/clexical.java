package com.github.xmppjingle.clexical.java;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by thiago on 6/24/16.
 */
public class Clexical {

    private final Herald herald;
    private final Scribe scribe;
    private final Vassal vassal;
    private final AtomicInteger counter;

    public Clexical(final Herald herald, final Scribe scribe, final Vassal vassal) {
        this.herald = herald;
        this.scribe = scribe;
        this.vassal = vassal;
        this.counter = new AtomicInteger(0);
    }

    public void recite(final Letter letter){
        letter.getPredicates().forEach(predicate -> pronounce(predicate, null));
    }

    private void pronounce(final Predicate predicate, final Predicate lastPredicate){
        fillID(predicate, lastPredicate);
        if(predicate.getAction().getType()== Predicate.Action.Type.preposition){
            refrain(predicate);
        }else{
            say(predicate, lastPredicate);
        }
    }

    private void say(final Predicate predicate, final Predicate lastPredicate) {
        herald.excerpts(predicate).forEach(p -> pronounce(p, lastPredicate));
        final Letter letter = vassal.work(predicate, lastPredicate);
        attend(letter);
        proclaim(letter);
    }

    private void refrain(final Predicate predicate) {
        final String key = composeKey(predicate);
        scribe.curb(key, predicate);
    }

    public void attend(final Letter letter){
        letter.getPredicates().forEach(predicate -> hear(predicate));
    }

    private void hear(final Predicate predicate){
        final Predicate memory = scribe.recall(composeKey(predicate));
        if(memory!=null){
            pronounce(memory, predicate);
        }else {
            final Predicate fallbackMemory = scribe.recall(composeEmptyKey(predicate));
            if(fallbackMemory!=null){
                pronounce(fallbackMemory, predicate);
            }
        }
    }

    public void proclaim(final Letter letter){
        herald.proclaim(letter);
    }

    private String freshID(){
        return String.valueOf(counter.getAndIncrement());
    }

    public ArrayList<Predicate> fillIDs(final ArrayList<Predicate> predicates, final Predicate base){
        predicates.forEach(predicate -> fillID(predicate, base));
        return predicates;
    }

    public Predicate fillID(final Predicate predicate, final Predicate base){
           if(base==null||base.getId()==null){
               predicate.setId(freshID());
           }else {
               predicate.setId(base.getId());
           }
        return predicate;
    }

    public static String composeKey(final Predicate predicate){
        return composeEmptyKey(predicate) +
                predicate.getAdjectives().values().stream().reduce(new StringBuilder(), (k,v) -> ((StringBuilder)k).append(v)).toString();
    }

    public static String composeEmptyKey(final Predicate predicate){
        return predicate.getAction().getName() + predicate.getId() + predicate.getSubject();
    }
}
