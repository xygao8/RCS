package com.iflytek.rcs.entity.file;

/**
 * @author chenzhuolin
 * @Description 文件消息体
 */
public class FileMsg {

    private FileInfo thumbInfo;
    private FileInfo fileInfo;

    public FileInfo getThumbInfo() {
        return thumbInfo;
    }

    public void setThumbInfo(FileInfo thumbInfo) {
        this.thumbInfo = thumbInfo;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String toXMLString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<file  xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">"
                + (thumbInfo != null ? thumbInfo.toThumbnailXml() : "" )+ fileInfo.toFileXml() +
                "</file>";
    }
}
