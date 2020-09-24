package com.iflytek.rcs.entity.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iflytek.rcs.entity.common.CDataAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OutboundIMMessage {

    private String contentType;
    private String conversationID;
    private String contributionID;
    private ServiceCapability serviceCapability =new ServiceCapability();

    @XmlJavaTypeAdapter(value= CDataAdapter.class)
    private String bodyText;
    private String inReplyToContributionID;
    private String contentEncoding;
    private String storeSupported;
    private List<String> reportRequest;
    private String shortMessageSupported;
    private String fallbackSupported;
    private String trafficType;
    private String smsBodyText;
    private String fallbackContentType;
    private String fallbackContentEncoding;
    private String rcsBodyText;
    private String messageId;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }

    public String getContributionID() {
        return contributionID;
    }

    public void setContributionID(String contributionID) {
        this.contributionID = contributionID;
    }

    public ServiceCapability getServiceCapability() {
        return serviceCapability;
    }

    public void setServiceCapability(ServiceCapability serviceCapability) {
        this.serviceCapability = serviceCapability;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getInReplyToContributionID() {
        return inReplyToContributionID;
    }

    public void setInReplyToContributionID(String inReplyToContributionID) {
        this.inReplyToContributionID = inReplyToContributionID;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public String getStoreSupported() {
        return storeSupported;
    }

    public void setStoreSupported(String storeSupported) {
        this.storeSupported = storeSupported;
    }

    public List<String> getReportRequest() {
        return reportRequest;
    }

    public void setReportRequest(List<String> reportRequest) {
        this.reportRequest = reportRequest;
    }

    public String getShortMessageSupported() {
        return shortMessageSupported;
    }

    public void setShortMessageSupported(String shortMessageSupported) {
        this.shortMessageSupported = shortMessageSupported;
    }

    public String getFallbackSupported() {
        return fallbackSupported;
    }

    public void setFallbackSupported(String fallbackSupported) {
        this.fallbackSupported = fallbackSupported;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getSmsBodyText() {
        return smsBodyText;
    }

    public void setSmsBodyText(String smsBodyText) {
        this.smsBodyText = smsBodyText;
    }

    public String getFallbackContentType() {
        return fallbackContentType;
    }

    public void setFallbackContentType(String fallbackContentType) {
        this.fallbackContentType = fallbackContentType;
    }

    public String getFallbackContentEncoding() {
        return fallbackContentEncoding;
    }

    public void setFallbackContentEncoding(String fallbackContentEncoding) {
        this.fallbackContentEncoding = fallbackContentEncoding;
    }

    public String getRcsBodyText() {
        return rcsBodyText;
    }

    public void setRcsBodyText(String rcsBodyText) {
        this.rcsBodyText = rcsBodyText;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}