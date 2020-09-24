package com.iflytek.rcs.entity.suggestion;

public class ShowLocation {
    /**
     * 本地位置坐标
     */
    private Location location;
    /**
     * 打开的网址
     */
    private String fallbackUrl;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFallbackUrl() {
        return fallbackUrl;
    }

    public void setFallbackUrl(String fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
    }
}