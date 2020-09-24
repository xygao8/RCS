package com.iflytek.rcs.controller;


import com.alibaba.fastjson.JSON;
import com.iflytek.rcs.entity.card.CardParamInfo;
import com.iflytek.rcs.entity.file.FileSendInfo;
import com.iflytek.rcs.entity.file.UploadFileInfo;
import com.iflytek.rcs.entity.suggestion.Suggestion;
import com.iflytek.rcs.handler.MessageSender;
import com.iflytek.rcs.pojo.A2PMessagePojo;
import com.iflytek.rcs.pojo.standard.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/chatbot")
public class MessageOptController {
    private Logger logger = LoggerFactory.getLogger(MessageOptController.class);
    private static final String DELIMITER = "\\|\\|";
    private static final String LISTDELIMITER = "&&";

    @RequestMapping(name = "发送A2P消息", value = "/plainTextMessage/{userId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = "application/json")
    @ResponseBody
    public ResponseResult plainTextMessageSend(@PathVariable String userId,@RequestBody A2PMessagePojo a2PMessagePojo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("收到请求参数,userId:{},messageParams:{}",userId,a2PMessagePojo.toString());

        //step1:必填参数 消息类型不能为空
        String messageType = a2PMessagePojo.getMessageType();
        if(null==messageType || "".equals(messageType)){
            logger.error("消息类型messageType字段不可为空，请检查参数");
            return ResponseResult.error("消息类型messageType字段不可为空，请检查参数");
        }

        // step2:组装suggesstions
        List<Suggestion> suggestions = a2PMessagePojo.getSuggestions();
        String suggesstionsStr = "";
        if(null!=suggestions){
            suggesstionsStr = JSON.toJSONString(suggestions);
        }

        //step3: 组装fileMsg
        FileSendInfo fileSendInfo = a2PMessagePojo.getFileSendInfo();
        String fileMsgStr = "";
        if(null!=fileSendInfo){
            if(null!=fileSendInfo.getFileType() && !"".equals(fileSendInfo.getFileType())){
                fileMsgStr +=fileSendInfo.getFileType()+DELIMITER;
            }

            if(null!=fileSendInfo.getFileUrl() && !"".equals(fileSendInfo.getFileUrl())){
                fileMsgStr +=fileSendInfo.getFileUrl()+DELIMITER;
            }

            if(null!=fileSendInfo.getFileSize() && !"".equals(fileSendInfo.getFileSize())){
                fileMsgStr +=fileSendInfo.getFileSize()+DELIMITER;
            }

            if(null!=fileSendInfo.getThumbnailType()){
                fileMsgStr +=fileSendInfo.getThumbnailType()+DELIMITER;
            }

            if(null!=fileSendInfo.getThumbnailUrl()){
                fileMsgStr +=fileSendInfo.getThumbnailUrl()+DELIMITER;
            }

            if(null!=fileSendInfo.getThumbnailSize()){
                fileMsgStr +=fileSendInfo.getThumbnailSize();
            }
            logger.info("组装发送文件信息：{}",fileMsgStr);
        }


        //step4:组装cardMsg
        List<CardParamInfo> cardMsgsList = a2PMessagePojo.getCardInfoList();
        StringBuilder sb = new StringBuilder();
        String cardMsgStr = null;
        if(null!=cardMsgsList){
            for(int i=0;i<cardMsgsList.size();i++){
                CardParamInfo cardParamInfo =cardMsgsList.get(i);
                //文件类型||文件链接||缩略图类型||缩略图链接||卡片描述||卡片标题||卡片按钮json
                sb.append(cardParamInfo.getFileType()).append(DELIMITER)
                        .append(cardParamInfo.getFileUrl()).append(DELIMITER)
                        .append(cardParamInfo.getThumbnailType()).append(DELIMITER)
                        .append(cardParamInfo.getThumbnailUrl()).append(DELIMITER)
                        .append(cardParamInfo.getCardDesc()).append(DELIMITER)
                        .append(cardParamInfo.getCardTitle()).append(DELIMITER)
                        .append(cardParamInfo.getSuggestions())
                        .append(LISTDELIMITER);
            }
            //最后两个分隔符 删掉
            cardMsgStr = sb.substring(0,cardMsgStr.length()-2).toString();
            logger.info("组装卡片消息:{}",cardMsgStr);
        }

        //step5:组装upload
        UploadFileInfo uploadFileInfo = a2PMessagePojo.getUploadFileInfo();
        if(null!=uploadFileInfo){
            logger.info("文件上传信息：uploadFileInfo:{}",uploadFileInfo);
        }

        //根据消息类型调用不同的消息发送方式
        switch (messageType) {
            case "text":
                //文本消息：text [浮动菜单json] [消息内容] [接收消息号码，逗号分隔]
                String[] textMessage = new String[]{messageType,suggesstionsStr,a2PMessagePojo.getTextMessage(),a2PMessagePojo.getDesPhoneNbr()};
                MessageSender.sendText(textMessage);
                break;
            case "file":
                //文件消息：file [浮动菜单json] [接收消息号码，逗号分隔] [文件类型||文件链接||文件大小||缩略图类型||缩略图链接||缩略图大小]
                String[] fileMessage = new String[]{messageType,suggesstionsStr,a2PMessagePojo.getDesPhoneNbr(),fileMsgStr};
                MessageSender.sendFileMsg(fileMessage);
                break;
            case "card":
               //单卡片消息：card [浮动菜单json] [接收消息号码，逗号分隔] [文件类型||文件链接||缩略图类型||缩略图链接||卡片描述||卡片标题||卡片按钮json]
                String[] cardMessage = new String[]{messageType,suggesstionsStr,a2PMessagePojo.getDesPhoneNbr(),cardMsgStr};
                MessageSender.sendCardMsg(cardMessage);
                break;
            case "cards":
                //多卡片消息：card [浮动菜单json] [接收消息号码，逗号分隔] [文件类型||文件链接||缩略图类型||缩略图链接||卡片描述||卡片标题||卡片按钮json]
                //多卡片media之间用&&分隔
                String[] cardsMessage = new String[]{messageType,suggesstionsStr,a2PMessagePojo.getDesPhoneNbr(),cardMsgStr};
                MessageSender.sendCardMsgs(cardsMessage);
                break;
            case "upload":
                //文件上传：upload [文件类型][文件链接][文件名][缩略图类型][缩略图链接][缩略图文件名]
                String[] uploadFileMessage = new String[]{messageType,uploadFileInfo.getFileType(),
                            uploadFileInfo.getFileUrl(),uploadFileInfo.getFileName(),
                            uploadFileInfo.getThumbnailType(),uploadFileInfo.getThumbnailUrl(),
                            uploadFileInfo.getThumbnailName()};
                MessageSender.uploadFile(uploadFileMessage);
                break;
            default:
                MessageSender.info();
                break;
        }
        String[] params = new String[]{"text","",a2PMessagePojo.getTextMessage(),a2PMessagePojo.getDesPhoneNbr()};
        MessageSender.sendText(params);
        return ResponseResult.success("success");
    }


    @RequestMapping(name = "接收Maap平台消息", value = "/InboundMessageNotification/{userId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = "application/json")
    @ResponseBody
    public ResponseResult receiveMaapMessage(@PathVariable String userId, @RequestBody(required = false) String body, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("收到请求参数,userId:{}",userId);
        if (body != null && body.length() > 0) {
            response.setStatus(204);
            logger.info("body:{}",body);
        }

        String uri = request.getRequestURI();
        String address = request.getHeader("Address");
        logger.info("request uri:{},address:{}",uri,address);
        //获取请求body内容
        InputStream is = null;
        is = request.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] b = new byte[4096];
        try
        {
            for (int n; (n = is.read(b)) != -1;) {
                sb.append(new String(b, 0, n));
            }
            logger.info("读取到请求内容为：{}",sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseResult.success(sb.toString(),"success");
    }











}
