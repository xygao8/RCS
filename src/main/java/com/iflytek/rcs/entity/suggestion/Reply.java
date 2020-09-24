package com.iflytek.rcs.entity.suggestion;

public class Reply {
    /**
     * 显示文本
     */
    public String displayText;
    /**
     * 回发内容—对象
     */
    public Postback postback;

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Postback getPostback() {
        return postback;
    }

    public void setPostback(Postback postback) {
        this.postback = postback;
    }
}