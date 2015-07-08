package mx.softux.androidweardemo;

import java.util.Date;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationMessage {
    public Integer id;
    public String text;
    public String sender;
    public Date createdAt;

    public ConversationMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ConversationMessage))
            return false;
        if (obj == this)
            return true;

        ConversationMessage that = (ConversationMessage) obj;

        return this.id == null ? this.id == that.id : id.equals(that.id);
    }
}
