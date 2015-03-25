package com.liujilong.carson.game7plus7;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/25 0025.
 */
public class Grid {
    public static final int NumOfSquaresX = 7;
    public static final int NumOfSquaresY = 7;
    public static final int initNum = 3;


    public Cell[][] cells ;
    public ArrayList<Cell> availableCells;

    public Grid(){
        cells = new Cell[NumOfSquaresX][NumOfSquaresY];
        availableCells = new ArrayList<>();
    }

    public void initGrid(){
        for (int xx = 0; xx < NumOfSquaresX; xx++){
            for (int yy = 0; yy < NumOfSquaresY; yy ++){
                cells[xx][yy] = new Cell(xx,xx);
                availableCells.add(cells[xx][yy]);
            }
        }
        for (int num = 0; num < initNum; num ++){
            availableCells.remove(getRandomAvailableCell().setColor(getRandomColorIndex()));
        }
    }

    public Cell getRandomAvailableCell(){
        return availableCells.get((int) (Math.random()*1000%availableCells.size()));
    }

    public int getRandomColorIndex(){
        return (int) (Math.random() * 1000 % 5);
    }


}
