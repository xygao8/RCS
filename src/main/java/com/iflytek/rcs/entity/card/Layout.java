package com.iflytek.rcs.entity.card;

public class Layout {

    /**
     * 卡片方向;如: HORIZONTAL水平的
     * VERTICAL垂直
     */
    private String cardOrientation;

    private String imageAlignment;

    /**
     * SMALL_WIDTH
     * MEDIUM_WIDTH
     * MAX_SMALL_WIDTH_CARD_HEIGHT
     */
    private String cardWidth;

    public String getCardOrientation() {
        return cardOrientation;
    }

    public void setCardOrientation(String cardOrientation) {
        this.cardOrientation = cardOrientation;
    }

    public String getImageAlignment() {
        return imageAlignment;
    }

    public void setImageAlignment(String imageAlignment) {
        this.imageAlignment = imageAlignment;
    }

    public String getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(String cardWidth) {
        this.cardWidth = cardWidth;
    }
}