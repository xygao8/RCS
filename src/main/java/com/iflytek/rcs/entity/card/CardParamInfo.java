package com.iflytek.rcs.entity.card;

import com.iflytek.rcs.entity.suggestion.Suggestion;
import com.iflytek.rcs.entity.suggestion.Suggestions;

import java.util.List;

public class CardParamInfo {
   // 文件类型||文件链接||缩略图类型||缩略图链接||卡片描述||卡片标题||卡片按钮json
    private String fileType;  //文件类型
    private String fileUrl; //文件链接
    private String thumbnailType;  //缩略图类型
    private String thumbnailUrl; //缩略图链接
    private String cardDesc; //卡片描述
    private String cardTitle; //卡片标题
    private List<Suggestion> suggestions;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getThumbnailType() {
        return thumbnailType;
    }

    public void setThumbnailType(String thumbnailType) {
        this.thumbnailType = thumbnailType;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public String toString() {
        return "{" +
                "fileType='" + fileType + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", thumbnailType='" + thumbnailType + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", cardDesc='" + cardDesc + '\'' +
                ", cardTitle='" + cardTitle + '\'' +
                ", suggestions='" + suggestions + '\'' +

                '}';
    }

}
