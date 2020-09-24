package com.iflytek.rcs.entity.suggestion;

public class Action extends Reply{
    /**
     * 打开URL操作
     */
    private UrlAction urlAction;
    /**
     * 拨号操作
     */
    private DialerAction dialerAction;
    /**
     * 地图操作
     */
    private MapAction mapAction;

    public UrlAction getUrlAction() {
        return urlAction;
    }

    public void setUrlAction(UrlAction urlAction) {
        this.urlAction = urlAction;
    }

    public DialerAction getDialerAction() {
        return dialerAction;
    }

    public void setDialerAction(DialerAction dialerAction) {
        this.dialerAction = dialerAction;
    }

    public MapAction getMapAction() {
        return mapAction;
    }

    public void setMapAction(MapAction mapAction) {
        this.mapAction = mapAction;
    }
}