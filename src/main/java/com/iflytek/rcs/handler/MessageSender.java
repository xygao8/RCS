package com.iflytek.rcs.handler;

import com.alibaba.fastjson.JSON;
import com.iflytek.rcs.CardLayout;
import com.iflytek.rcs.config.MessageConfig;
import com.iflytek.rcs.entity.card.*;
import com.iflytek.rcs.entity.file.FileInfo;
import com.iflytek.rcs.entity.file.FileMsg;
import com.iflytek.rcs.entity.suggestion.Suggestions;
import com.iflytek.rcs.utils.HttpKit;
import com.iflytek.rcs.utils.MsgBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;





/**
 * @author chenzhuolin
 * @Description
 */
public class MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
    private static final String DELIMITER = "\\|\\|";

    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        try {
            switch (args[0]) {
                case "text":
                    sendText(args);
                    break;
                case "file":
                    sendFileMsg(args);
                    break;
                case "card":
                    sendCardMsg(args);
                    break;
                case "cards":
                    sendCardMsgs(args);
                    break;
                case "upload":
                    uploadFile(args);
                    break;
                default:
                    info();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            info();
            return;
        }
    }

    public static void info() {
        logger.info("文本消息：text [浮动菜单json] [消息内容] [接收消息号码，逗号分隔]");
        logger.info("文件消息：file [浮动菜单json] [接收消息号码，逗号分隔] [文件类型||文件链接||文件大小||缩略图类型||缩略图链接||缩略图大小]");
        logger.info("单卡片消息：card [浮动菜单json] [接收消息号码，逗号分隔] [文件类型||文件链接||缩略图类型||缩略图链接||卡片描述||卡片标题||卡片按钮json]");
        logger.info("多卡片消息：cards [浮动菜单json] [接收消息号码，逗号分隔] [文件类型||文件链接||缩略图类型||缩略图链接||卡片描述||卡片标题||卡片按钮json]");
        logger.info("多卡片media之间用&&分隔");
        logger.info("文件上传：upload [文件类型][文件链接][文件名][缩略图类型][缩略图链接][缩略图文件名]");
    }

    public static void sendText(String[] args) {
        logger.info("sendText begin,args:{}",args);
        String res = MessageSend.sendText(args[2], Arrays.asList(args[3].split(",")), Arrays.asList("Delivered","Displayed","Failed"), args[1] == null || args[1] == "" ? null : JSON.parseObject(args[1], Suggestions.class));
        logger.info("sendText end,res:{}",res);
    }


    public static void sendFileMsg(String[] args) {
        logger.info("sendFileMsg begin ,args:{}",args);
        String[] media = args[3].split(DELIMITER);
        FileMsg fileMsg = new FileMsg();
        FileInfo file = new FileInfo();
        FileInfo thumb=null;
        if (media.length == 6) {
             thumb = new FileInfo();
            thumb.setContentType(media[3]);
            thumb.setUrl(media[4]);
            thumb.setFileSize(Integer.parseInt(media[5]));
        }
        file.setFileSize(Integer.parseInt(media[2]));
        file.setUrl(media[1]);
        file.setContentType(media[0]);
        fileMsg.setFileInfo(file);
        fileMsg.setThumbInfo(thumb);
        String res = MessageSend.sendFile(fileMsg, Arrays.asList(args[2].split(",")),  Arrays.asList("Delivered","Displayed","Failed"), args[1] == null || args[1] == "" ? null : JSON.parseObject(args[1], Suggestions.class));
        logger.info("sendFileMsg end ,res:{}",res);
    }

    public static void sendCardMsg(String[] args) {
        logger.info("sendCardMsg begin ,args:{}",args);
        MessageConfig config = new MessageConfig();
        String[] medias = args[3].split(DELIMITER);
        CardMsg cardMsg = new CardMsg();
        Message message = new Message();
        GeneralPurposeCard card = new GeneralPurposeCard();
        Content content = new Content();
        Layout layout = new Layout();
        Media media = new Media();

        media.setHeight(CardLayout.MEDIA_HEIGHT_MEDIUM);
        media.setMediaContentType(medias[0]);
        media.setMediaUrl(medias[1]);
        media.setThumbnailContentType(medias[2]);
        media.setThumbnailUrl(medias[3]);

        layout.setCardOrientation(CardLayout.CARD_ORIENTATION_VERTICAL);
        layout.setCardWidth(CardLayout.CARD_WIDTH_MEDIUM);
        layout.setImageAlignment(CardLayout.IMAGE_ALIGNMENT_LEFT);

        content.setDescription(medias[4]);
        content.setTitle(medias[5]);
        content.setMedia(media);
        Suggestions suggestions = JSON.parseObject(medias[6], Suggestions.class);
        content.setSuggestions(medias[6] == null ? null : suggestions.getSuggestions());
        card.setContent(content);
        card.setLayout(layout);
        message.setGeneralPurposeCard(card);
        cardMsg.setMessage(message);
        String res = MessageSend.sendCard(cardMsg, Arrays.asList(args[2].split(",")),  Arrays.asList("Delivered","Displayed","Failed"), args[1] == null || args[1] == "" ? null : JSON.parseObject(args[1], Suggestions.class));
        logger.info("sendCardMsg end ,res:{}",res);
    }

    public static void sendCardMsgs(String[] args) {
        logger.info("sendCardMsgs begin ,args:{}",args);
        MessageConfig config = MessageConfig.getInstance();
        String[] medias = args[3].split("&&");
        CardMsg cardMsg = new CardMsg();
        Message message = new Message();
        GeneralPurposeCardCarousel card = new GeneralPurposeCardCarousel();
        List<Content> collect = Stream.of(medias).map(item -> {
                    String[] md = item.split(DELIMITER);
                    Content content = new Content();
                    Media media = new Media();
                    media.setHeight(CardLayout.MEDIA_HEIGHT_MEDIUM);
                    media.setMediaContentType(md[0]);
                    media.setMediaUrl(md[1]);
                    media.setThumbnailContentType(md[2]);
                    media.setThumbnailUrl(md[3]);

                    content.setDescription(md[4]);
                    content.setTitle(md[5]);
                    content.setSuggestions(md[6] == null ? null : JSON.parseObject(md[6], Suggestions.class).getSuggestions());
                    content.setMedia(media);
                    return content;
                }
        ).collect(Collectors.toList());
        Layout layout = new Layout();
        layout.setCardWidth(CardLayout.CARD_WIDTH_MEDIUM);
        card.setContent(collect.toArray(new Content[collect.size()]));
        card.setLayout(layout);
        message.setGeneralPurposeCardCarousel(card);
        cardMsg.setMessage(message);
        String res = MessageSend.sendCard(cardMsg, Arrays.asList(args[2].split(",")), null, args[1] == null || args[1] == "" ? null : JSON.parseObject(args[1], Suggestions.class));
        logger.info("sendCardMsgs end ,res:{}",res);
    }

    public static void uploadFile(String[] args) {
        logger.info("uploadFile begin ,args:{}",args);
        try {
            byte[] fileBytes = getFileBytes(args[2]);
            byte[] thumbnailBytes = args.length == 7 ? getFileBytes(args[5]) : null;
            Date date = new Date();
            String time = MsgBuilder.getGMT(date);
            MessageConfig config = MessageConfig.getInstance();
            final String boundary = System.currentTimeMillis() + "";
            String tidContent="--" + boundary + "\r\nContent-Disposition: form-data; name=\"tid\"\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n" +
                    UUID.randomUUID().toString().replace("-", "") + "\r\n";
            String thumbContent = "--" + boundary +
                    "\r\nContent-Disposition: form-data; name=\"Thumbnail\";filename=\"%s\"\r\nContent-Type: %s\r\nContent-Transfer-Encoding: binary\r\nContent-Length: %s\r\n\r\n";

            String fileContent = "--" + boundary + "\r\nContent-Disposition: form-data; name=\"File\"; filename=\"%s\"\r\nContent-Type: %s\r\nContent-Transfer-Encoding: 8bits\r\n" +
                    "Content-Length: %s\r\n\r\n";
            byte[] end = ("\r\n--" + boundary + "--").getBytes("UTF-8");
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Basic " + MsgBuilder.generateAuthorization(config.getProperty("chatbot.appid"), config.getProperty("chatbot.token"), date));
            headers.put("Date", time);
            headers.put("X-3GPP-Intended-Identity", config.getProperty("chatbot.uri").replace("sip:", ""));
            headers.put("User-Agent", config.getProperty("chatbot.uri").replace("sip:", ""));
            headers.put("Content-Type", "multipart/form-data; boundary=" + boundary);
            headers.put("Terminal-type", "Chatbot");
            headers.put("NotifyUrl", "http://124.42.103.137:8089/chatbot");
            byte[] tidContentBytes = tidContent.getBytes("UTF-8");
            int tidContentLength = tidContentBytes.length;
            int thumbBytesLength = args.length == 7 ? thumbnailBytes.length : 0;
            String thumb = args.length == 7 ? String.format(thumbContent, args[6], args[4], thumbBytesLength) : null;
            String file = String.format(fileContent, args[3], args[1], fileBytes.length);
            logger.info("thumb:{}",thumb);
            logger.info("file:{}",file);
            byte[] body1 = args.length == 7 ? thumb.getBytes("UTF-8") : null;
            int thumbContentBodyLength = args.length == 7 ? body1.length : 0;
            byte[] body2 = file.getBytes("UTF-8");
            byte[] body = new byte[thumbContentBodyLength + (args.length == 7 ? thumbBytesLength : 0 )+ body2.length+ fileBytes.length + end.length];
            System.arraycopy(tidContentBytes,0,body,0, tidContentLength);
            int offset =0;
            if (args.length == 7) {
                System.arraycopy(body1, 0, body, offset, thumbContentBodyLength);
                offset+=thumbBytesLength;
                System.arraycopy(thumbnailBytes, 0, body, offset, thumbBytesLength);
                offset+=thumbBytesLength;
            }
            System.arraycopy(body2, 0, body, offset, body2.length);
            offset+=body2.length;
            System.arraycopy(fileBytes, 0, body, offset, fileBytes.length);
            offset+=fileBytes.length;
            System.arraycopy(end, 0, body, offset, end.length);
            System.out.println("文件上传请求头：");
            headers.entrySet().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue() + "\n"));
            logger.info("文件上传请求地址：" + config.getProperty("upload.url"));
            String res = HttpKit.uploadFile(config.getProperty("upload.url"), body, headers);

            logger.info("uploadFile end ,res:{}",res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getFileBytes(String url) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, KeyManagementException {
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        try {
            conn = HttpKit.getHttpConnection(url, "GET", null);
            inputStream = conn.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                byteArrayOutputStream.write(buff, 0, rc);
            }
            return byteArrayOutputStream.toByteArray();
        }  finally {
            try {
                if (conn != null) conn.disconnect();
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
