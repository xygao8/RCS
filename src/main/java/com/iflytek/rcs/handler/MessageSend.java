package com.iflytek.rcs.handler;

import com.iflytek.rcs.config.MessageConfig;
import com.iflytek.rcs.entity.card.CardMsg;
import com.iflytek.rcs.entity.file.FileMsg;
import com.iflytek.rcs.entity.suggestion.Suggestions;
import com.iflytek.rcs.utils.HttpKit;
import com.iflytek.rcs.utils.MsgBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author chenzhuolin
 * @Description 消息发送处理类
 */
public class MessageSend {

    public static String sendText(String text, List<String> mobile, List<String> report) {
        return send(text, mobile, report, null, null);
    }

    public static String sendText(String text, List<String> mobile, List<String> report, Suggestions suggestions) {
        return send(text, mobile, report, suggestions, null);
    }

    public static String sendFile(FileMsg fileMsg, List<String> mobile, List<String> report) {
        return send(fileMsg, mobile, report, null, null);
    }

    public static String sendFile(FileMsg fileMsg, List<String> mobile, List<String> report, Suggestions suggestions) {
        return send(fileMsg, mobile, report, suggestions, null);
    }

    public static String sendCard(CardMsg cardMsg, List<String> mobile, List<String> report) {
        return send(cardMsg, mobile, report, null, null);
    }

    public static String sendCard(CardMsg cardMsg, List<String> mobile, List<String> report, Suggestions suggestions) {
        return send(cardMsg, mobile, report, suggestions, null);
    }

    public static String ReplyText(String text, List<String> mobile, List<String> report, String inReplyToContributionID) {
        return send(text, mobile, report, null, inReplyToContributionID);
    }

    public static String replyText(String text, List<String> mobile, List<String> report, Suggestions suggestions, String inReplyToContributionID) {
        return send(text, mobile, report, suggestions, inReplyToContributionID);
    }

    public static String replyFile(FileMsg fileMsg, List<String> mobile, List<String> report, String inReplyToContributionID) {
        return send(fileMsg, mobile, report, null, inReplyToContributionID);
    }

    public static String replyFile(FileMsg fileMsg, List<String> mobile, List<String> report, Suggestions suggestions, String inReplyToContributionID) {
        return send(fileMsg, mobile, report, suggestions, inReplyToContributionID);
    }

    public static String replyCard(CardMsg cardMsg, List<String> mobile, List<String> report, String inReplyToContributionID) {
        return send(cardMsg, mobile, report, null, inReplyToContributionID);
    }

    public static String replyCard(CardMsg cardMsg, List<String> mobile, List<String> report, Suggestions suggestions, String inReplyToContributionID) {
        return send(cardMsg, mobile, report, suggestions, inReplyToContributionID);
    }


    private static <T> String send(T t, List<String> mobile, List<String> report, Suggestions suggestions, String inReplyToContributionID) {
        String body = MsgBuilder.build(t, mobile, report, suggestions, inReplyToContributionID);
        System.out.println(body);
        MessageConfig config = MessageConfig.getInstance();
        String address = null;
        address = "+86" + mobile.get(0);
        Map<String, String> headers = MsgBuilder.buildHeaders(address);
        System.out.println(config.getProperty("message.url"));
        String res = HttpKit.post(config.getProperty("message.url"), body, headers);
        return res;
    }
}
