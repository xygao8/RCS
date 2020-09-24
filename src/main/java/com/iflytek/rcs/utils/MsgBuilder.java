package com.iflytek.rcs.utils;


import com.alibaba.fastjson.JSON;
import com.iflytek.rcs.config.MessageConfig;
import com.iflytek.rcs.entity.card.CardMsg;
import com.iflytek.rcs.entity.common.InternationalMsgType;
import com.iflytek.rcs.entity.file.FileMsg;
import com.iflytek.rcs.entity.request.OutboundIMMessage;
import com.iflytek.rcs.entity.request.RequestBody;
import com.iflytek.rcs.entity.suggestion.Suggestions;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @Description 消息体构造器
 */
public class MsgBuilder {

    private static final String BOUNDARY = "next";
    private static final String CHARSET = "UTF-8";
    private static final String CONTENT = "--" + BOUNDARY + "\nContent-Type: %s\n\n";

    public static <T> String build(T t, List<String> mobiles, List<String> reportRequest, Suggestions suggestions, String inReplyToContributionID) {
        if (t instanceof String)
            return buildMsg(InternationalMsgType.TEXT, t, mobiles, reportRequest, suggestions, inReplyToContributionID);
        if (t instanceof FileMsg)
            return buildMsg(InternationalMsgType.FILE, t, mobiles, reportRequest, suggestions, inReplyToContributionID);
        if (t instanceof CardMsg)
            return buildMsg(InternationalMsgType.CARD, t, mobiles, reportRequest, suggestions, inReplyToContributionID);
        throw new RuntimeException("创建消息体失败");
    }


    private static <T> String buildMsg(InternationalMsgType msgType, T t, List<String> mobiles, List<String> reportRequest, Suggestions suggestions, String inReplyToContributionID) {
        if (t == null)
            throw new RuntimeException("bodyText不能为空");
        String contentType = msgType.getValue();
        List<String> phones = mobiles.stream().map(mobile -> "tel:+86" + mobile).collect(Collectors.toList());
        OutboundIMMessage outboundIMMessage = new OutboundIMMessage();
        outboundIMMessage.setContentType(contentType);
        outboundIMMessage.setContributionID(UUID.randomUUID().toString());
        outboundIMMessage.setConversationID(UUID.randomUUID().toString());
        outboundIMMessage.setReportRequest(reportRequest);
        outboundIMMessage.setInReplyToContributionID(inReplyToContributionID);
        outboundIMMessage.setMessageId(UUID.randomUUID().toString());
        String body = null;
        if (InternationalMsgType.FILE.equals(msgType)) {
            if (!(t instanceof FileMsg))
                throw new RuntimeException("消息类型不匹配");
            body = ((FileMsg) t).toXMLString();
        }
        if (InternationalMsgType.TEXT.equals(msgType)) {
            if (!(t instanceof String))
                throw new RuntimeException("消息类型不匹配");
            body = (String) t;
        }
        if (InternationalMsgType.CARD.equals(msgType)) {
            if (!(t instanceof CardMsg))
                throw new RuntimeException("消息类型不匹配");
            body = JSON.toJSONString(t);
        }
        if (suggestions != null) {
            try {
                outboundIMMessage.setContentType(InternationalMsgType.MULTIPART.getValue() + "&quot;" + BOUNDARY + "&quot;");
                StringBuilder builder = new StringBuilder();
                String suggestionBody = JSON.toJSONString(suggestions);
                builder.append(String.format(CONTENT, contentType));
                builder.append(body + "\n");
                builder.append(String.format(CONTENT, InternationalMsgType.SUGGESTION.getValue()));
                builder.append(suggestionBody + "\n--" + BOUNDARY + "--\n");
                body = builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        outboundIMMessage.setBodyText(body);
        RequestBody requestBody = new RequestBody();
        requestBody.setSenderAddress(MessageConfig.getInstance().getProperty("chatbot.uri"));
        requestBody.setAddress(phones.get(0));
        requestBody.setDestinationAddress(phones);
        requestBody.setOutboundIMMessage(outboundIMMessage);
//        requestBody.setClientCorrelator("567895");
        return XmlUtil.bean2xml(requestBody, CHARSET).replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&");
    }
    public static String getGMT(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        return format.format(date);
    }

    public static Map<String, String> buildHeaders(String address) {
        Date date = new Date();
        String time = getGMT(date);
        MessageConfig config = MessageConfig.getInstance();
        String authorization = generateAuthorization(config.getProperty("chatbot.appid"), config.getProperty("chatbot.token"), date);
        Map<String, String> headers = new HashMap<>(3);
        headers.put("Content-Type", "application/xml");
        headers.put("Authorization", String.format("Basic %s", authorization));
        headers.put("Date", time);
        if (address != null)
            headers.put("Address", address);
        System.out.println(headers);
        return headers;
    }

    public static String generateAuthorization(String appid, String token, Date date) {
        try {
            String GMT = getGMT(date);
            System.out.println(GMT);
            StringBuffer sb = new StringBuffer();
            byte[] bytes = sb.append(appid).append(":").append(sha256(token + GMT)).toString().getBytes(CHARSET);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("token生成失败");
    }

    public static String generateAuthorizationByPwd(String appid, String password, Date date) {
        try {
            String GMT = getGMT(date);
            System.out.println(GMT);
            String token = sha256(password);
            StringBuffer sb = new StringBuffer();
            byte[] bytes = sb.append(appid).append(":").append(sha256(token + GMT)).toString().getBytes(CHARSET);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("token生成失败");
}

    private static String sha256(String val) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(val.getBytes(CHARSET));
            byte[] bytes = sha256.digest();
            StringBuffer builder = new StringBuffer();
            String temp = null;
            for (int i = 0; i < bytes.length; i++) {
                temp = Integer.toHexString(bytes[i] & 0xFF);
                if (temp.length() == 1)
                    builder.append("0");
                builder.append(temp);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
