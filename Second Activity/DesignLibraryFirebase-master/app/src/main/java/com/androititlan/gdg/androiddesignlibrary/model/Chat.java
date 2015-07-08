package com.androititlan.gdg.androiddesignlibrary.model;

/**
 * Created by Jhordan on 06/07/15.
 */
public class Chat {

    private String message;
    private String id;
    private String urlImg;

    public Chat() {
    }

    public Chat(String message, String id, String urlImg) {
        this.message = message;
        this.id = id;
        this.urlImg = urlImg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

}
