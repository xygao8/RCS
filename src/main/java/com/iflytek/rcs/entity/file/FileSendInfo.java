package com.iflytek.rcs.entity.file;


public class FileSendInfo {
    private String fileType;  //文件类型
    private String fileSize;  //文件大小
    private String fileUrl; //文件链接
    private String thumbnailType;  //缩略图类型
    private String thumbnailSize;  //缩略图大小
    private String thumbnailUrl; //缩略图链接


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

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(String thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    @Override
    public String toString() {
        return "{" +
                "fileType='" + fileType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", thumbnailType='" + thumbnailType + '\'' +
                ", thumbnailSize='" + thumbnailSize + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
