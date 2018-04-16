package com.binguner.crazytalk.Model;

public class CircleChatMessageModel {
    public static final int MESSAGE_LEFT = 0;
    public static final int MESSAGE_RIGHT = 1;
    private String msg;
    private int type;

    public CircleChatMessageModel(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
