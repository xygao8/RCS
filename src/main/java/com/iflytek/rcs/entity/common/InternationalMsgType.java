package com.iflytek.rcs.entity.common;

/**
 * @Description 国标消息体类型
 * @Version 1.0
 **/
public enum InternationalMsgType {

    //纯文本消息类型
    TEXT("text", "text/plain"),
    //携带建议回复混合消息类型
    MULTIPART("multipart", "multipart/mixed; boundary="),
    //卡片消息类型
    CARD("card", "application/vnd.gsma.botmessage.v1.0+json"),
    //文件消息类型
    FILE("file", "application/vnd.gsma.rcs-ft-http+xml"),
    //建议回复消息类型
    SUGGESTION("sugestion", "application/vnd.gsma.botsuggestion.v1.0+json");
    private String type;

    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private InternationalMsgType(String type, String value){
        this.type = type;
        this.value = value;
    }

    public static String getTypeValue(String type){
        try{
            return InternationalMsgType.valueOf(type).getValue();
        }catch (Exception ex){
            return "";
        }
    }

    public static InternationalMsgType getByType(String type){
        try{
            return InternationalMsgType.valueOf(type);
        }catch (Exception ex){
            return null;
        }
    }

    public static InternationalMsgType getByValue(String value){
        try{
            InternationalMsgType[] values = InternationalMsgType.values();
            for (InternationalMsgType internationalMsgType : values) {
                if (internationalMsgType.getValue().equals(value)) {
                    return internationalMsgType;
                }
            }
        }catch (Exception ex){
            return null;
        }
        return null;
    }
    public static Boolean contains(String value){
        try{
            InternationalMsgType[] values = InternationalMsgType.values();
            for (InternationalMsgType internationalMsgType : values) {
                if (value.contains(internationalMsgType.getValue())) {
                    return true;
                }
            }
        }catch (Exception ex){
            return false;
        }
        return false;
    }
}
