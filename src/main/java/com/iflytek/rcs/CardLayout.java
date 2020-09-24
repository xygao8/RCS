package com.iflytek.rcs;

/**
 * @author  
 * @Description
 */
public class CardLayout {

    // 单卡片布局：HORIZONTAL水平布局，VERTICAL垂直布局
    public static final String CARD_ORIENTATION_VERTICAL = "VERTICAL";
    public static final String CARD_ORIENTATION_HORIZONTAL = "HORIZONTAL";

    //单卡片水平布局图片对齐方式：LEFT左对齐，RIGHT右对齐
    public static final String IMAGE_ALIGNMENT_LEFT = "LEFT";
    public static final String IMAGE_ALIGNMENT_RIGHT = "RIGHT";

    //卡片宽参数 ：SMALL_WIDTH: 120 DP ，MEDIUM_WIDTH: 232 DP
    public static final String CARD_WIDTH_MEDIUM = "MEDIUM_WIDTH";
    public static final String CARD_WIDTH_SMALL = "SMALL_WIDTH";

    /**
     * 卡片素材参数，ASPECT RATIO是纵横比
     * SHORT_HEIGHT (112 DP 、 ASPECT RATIO = 3:1）
     * MEDIUM_HEIGHT （ 168 DP 、 ASPECT RATIO = 1.56:1）
     * TALL_HEIGHT（264 DP 、 ASPECT RATIO = 9:10）
     */
    public static final String MEDIA_HEIGHT_MEDIUM = "MEDIUM_HEIGHT";
    public static final String MEDIA_HEIGHT_SHORT = "SHORT_HEIGHT";
    public static final String MEDIA_HEIGHT_TALL = "TALL_HEIGHT";

}
