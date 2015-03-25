package com.liujilong.carson.game7plus7;

/**
 * Created by Administrator on 2015/3/25 0025.
 */
public class MainGame {
    private Grid grid;

    public MainGame(){
        grid = new Grid();
    }
    public void newGame(){
        grid.initGrid();
    }

    public Grid getGrid() {
        return grid;
    }
}
