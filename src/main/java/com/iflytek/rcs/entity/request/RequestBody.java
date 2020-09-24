package com.iflytek.rcs.entity.request;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author chenzhuolin
 * @Description bodyText内容
 */
@XmlRootElement(name = "outboundMessageRequest",namespace = "urn:oma:xml:rest:netapi:messaging:1")
public class RequestBody {

    private String address;
    private String senderAddress;
    private List<String> destinationAddress;
    private OutboundIMMessage outboundIMMessage;
    private String clientCorrelator;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public List<String> getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(List<String> destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public OutboundIMMessage getOutboundIMMessage() {
        return outboundIMMessage;
    }

    public void setOutboundIMMessage(OutboundIMMessage outboundIMMessage) {
        this.outboundIMMessage = outboundIMMessage;
    }

    public String getClientCorrelator() {
        return clientCorrelator;
    }

    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }


}
