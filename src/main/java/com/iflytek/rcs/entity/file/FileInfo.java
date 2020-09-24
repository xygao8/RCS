package com.iflytek.rcs.entity.file;


public class FileInfo {

    private Integer fileSize;

    private String contentType;

    private String url;

    private String util;

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toThumbnailXml() {
        return "<file-info type=\"thumbnail\">\n" +
                "<file-size>" + fileSize + "</file-size>\n" +
                "<content-type>" + contentType + "</content-type>\n" +
                "<data\n" +
                "url=\"" + url + "\" until=\"" + util + "\"/>\n" +
                "</file-info>\n";
    }
     public String toFileXml() {
        return "<file-info type=\"file\">\n" +
                "<file-size>" + fileSize + "</file-size>\n" +
                "<content-type>" + contentType + "</content-type>\n" +
                "<data\n" +
                "url=\"" + url + "\" until=\"" + util + "\"/>\n" +
                "</file-info>\n";
    }

}
