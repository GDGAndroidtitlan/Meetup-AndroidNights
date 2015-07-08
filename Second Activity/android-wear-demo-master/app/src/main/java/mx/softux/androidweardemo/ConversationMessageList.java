package mx.softux.androidweardemo;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationMessageList extends LinkedList<ConversationMessage> {
    public ConversationMessageList() {
        super();
    }

    public ConversationMessageList(Collection<? extends ConversationMessage> collection) {
        super(collection);
    }

    @Override
    public void add(int location, ConversationMessage object) {
        if(contains(object)) return;

        super.add(location, object);
    }

    @Override
    public boolean add(ConversationMessage object) {
        if(contains(object)) return true;

        return super.add(object);
    }

    @Override
    public boolean addAll(int location, Collection<? extends ConversationMessage> collection) {
        if(collection == this) return true;

        for(ConversationMessage e : collection) {
            add(location, e);
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends ConversationMessage> collection) {
        if(collection == this) return true;

        for(ConversationMessage e : collection) {
            if(contains(e)) continue;
            add(e);
        }
        return true;
    }

    @Override
    public void addFirst(ConversationMessage object) {
        add(0, object);
    }

    @Override
    public void addLast(ConversationMessage object) {
        add(size(), object);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(ConversationMessage message : this) {
            stringBuilder.append(message.sender);
            stringBuilder.append(" ");
            stringBuilder.append(message.text);
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
