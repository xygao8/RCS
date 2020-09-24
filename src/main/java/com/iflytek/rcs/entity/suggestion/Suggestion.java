package com.iflytek.rcs.entity.suggestion;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Description 浮动菜单||卡片按钮
 * @Author chenzhuolin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suggestion {
    private Reply reply;
    private Action action;

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
