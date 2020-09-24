package com.iflytek.rcs.entity.card;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    /**
     * 多卡片
     */
    private GeneralPurposeCardCarousel generalPurposeCardCarousel;
    /**
     * 单卡片
     */
    private GeneralPurposeCard generalPurposeCard;

    public GeneralPurposeCardCarousel getGeneralPurposeCardCarousel() {
        return generalPurposeCardCarousel;
    }

    public void setGeneralPurposeCardCarousel(GeneralPurposeCardCarousel generalPurposeCardCarousel) {
        this.generalPurposeCardCarousel = generalPurposeCardCarousel;
    }

    public GeneralPurposeCard getGeneralPurposeCard() {
        return generalPurposeCard;
    }

    public void setGeneralPurposeCard(GeneralPurposeCard generalPurposeCard) {
        this.generalPurposeCard = generalPurposeCard;
    }
}