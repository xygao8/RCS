package com.iflytek.rcs.entity.card;

public class Media {
    /**
     * 媒体资源地址
     */
    private String mediaUrl;
    /**
     * 资源类型如 video/mp4
     */
    private String mediaContentType;
    /**
     * 资源大小
     */
    private String mediaFileSize;
    /**
     * 媒体缩略图资源地址
     */
    private String thumbnailUrl;
    /**
     * image/png
     */
    private String thumbnailContentType;
    /**
     * 高度描述: 如MEDIUM_HEIGHT 同等高度
     * SHORT_HEIGHT = 112 DP
     * MEDIUM_HEIGHT = 168 DP
     * TALL_HEIGHT= 264 DP
     */
    private String height;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
    }

    public String getMediaFileSize() {
        return mediaFileSize;
    }

    public void setMediaFileSize(String mediaFileSize) {
        this.mediaFileSize = mediaFileSize;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailContentType() {
        return thumbnailContentType;
    }

    public void setThumbnailContentType(String thumbnailContentType) {
        this.thumbnailContentType = thumbnailContentType;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}