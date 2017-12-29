package com.dhirain.myapplication.model;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class HistoryModel {

    private Long _id; // for cupboard
    private int contentType;
    private String text;
    private long time;

    public HistoryModel() {
    }

    public HistoryModel(int contentType, String text, long time) {
        this.contentType = contentType;
        this.text = text;
        this.time = time;
    }

    public Long get_id() {
        return _id;
    }

    public int getContentType() {
        return contentType;
    }

    public String getText() {
        return text;
    }

    public long getTime() {
        return time;
    }
}
