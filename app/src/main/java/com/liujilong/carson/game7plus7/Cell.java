package com.liujilong.carson.game7plus7;

/**
 * Created by Administrator on 2015/3/25 0025.
 */
public class Cell  {
    public static final int BLUE = 0x139aff;
    public static final int RED = 0xff0a03;
    public static final int PURPLE = 0xdf14ff;
    public static final int GREEN = 0x34ff61;
    public static final int ORANGE = 0xffb00c;
    public static final int GRAY = 0xe2e2e2;

    public static final int[] COLORS = new int[]{RED,BLUE,GREEN,ORANGE,PURPLE};

    private int xx = 0;
    private int yy = 0;
    private int colorIndex = -1;

    public Cell(int xx, int yy){
        this.xx = xx;
        this.yy = yy;
    }

    public Cell(int xx, int yy, int colorIndex){
        this(xx,yy);
        this.colorIndex = colorIndex;
    }

    public int getXx() {
        return xx;
    }

    public Cell setXx(int xx) {
        this.xx = xx;
        return this;
    }

    public int getYy() {
        return yy;
    }

    public Cell setYy(int yy) {
        this.yy = yy;
        return this;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public Cell setColor(int colorIndex) {
        this.colorIndex = colorIndex;
        return this;
    }
}
