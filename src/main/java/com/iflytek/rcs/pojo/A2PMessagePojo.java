package com.iflytek.rcs.pojo;

import com.iflytek.rcs.entity.card.CardMsg;
import com.iflytek.rcs.entity.card.CardParamInfo;
import com.iflytek.rcs.entity.file.FileMsg;
import com.iflytek.rcs.entity.file.FileSendInfo;
import com.iflytek.rcs.entity.file.UploadFileInfo;
import com.iflytek.rcs.entity.suggestion.Suggestion;
import com.iflytek.rcs.entity.suggestion.Suggestions;

import java.io.Serializable;
import java.util.List;

/**
 * 〈A2P消息请求参数〉
 * 〈功能详细描述〉
 *
 * @author [作者] xygao8
 * @version [版本号, 2020-09-21]
 **/
public class A2PMessagePojo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String messageType;//消息类型    text/file/card/cards/upload
    private String textMessage; //文本消息
    private String desPhoneNbr; //接收信息号码  若有多个，用英文逗号连接
    private List<Suggestion> suggestions;  //浮动菜单
    private FileSendInfo fileSendInfo; //发送文件信息   文件类型||文件链接||文件大小||缩略图类型||缩略图链接||缩略图大小
    private UploadFileInfo uploadFileInfo; //上传文件信息  文件类型||文件名||文件链接||缩略图类型||缩略图文件名||缩略图链接
    private List<CardParamInfo> cardInfoList; //卡片信息  文件类型||文件链接||缩略图类型||缩略图链接||卡片描述||卡片标题||卡片按钮json

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDesPhoneNbr() {
        return desPhoneNbr;
    }

    public void setDesPhoneNbr(String desPhoneNbr) {
        this.desPhoneNbr = desPhoneNbr;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public FileSendInfo getFileSendInfo() {
        return fileSendInfo;
    }

    public void setFileSendInfo(FileSendInfo fileSendInfo) {
        this.fileSendInfo = fileSendInfo;
    }

    public List<CardParamInfo> getCardInfoList() {
        return cardInfoList;
    }

    public void setCardInfoList(List<CardParamInfo> cardInfoList) {
        this.cardInfoList = cardInfoList;
    }

    public UploadFileInfo getUploadFileInfo() {
        return uploadFileInfo;
    }

    public void setUploadFileInfo(UploadFileInfo uploadFileInfo) {
        this.uploadFileInfo = uploadFileInfo;
    }

    @Override
    public String toString() {
        return "{" +
                "messageType='" + messageType + '\'' +
                ", textMessage='" + textMessage + '\'' +
                ", desPhoneNbr='" + desPhoneNbr + '\'' +
                ", suggestions='" + suggestions + '\'' +
                ", fileSendInfo='" + fileSendInfo + '\'' +
                ", cardInfoList='" + cardInfoList + '\'' +
                ", uploadFileInfo='" + uploadFileInfo + '\'' +

                '}';
    }
}