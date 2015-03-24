package com.liujilong.carson.game7plus7;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Administrator on 2015/3/19 0019.
 */
public class ViewGame extends View {
    Paint paint = new Paint();
    private Bitmap background;
    private int totalWidth;
    private int totalHeight;
    private int cellSize = 0;
    private int nextCellSize = 0;
    private int gapSize = 0;
    private int startingX = 0;
    private int startintY = 0;
    private int startingNextCellX = 0;
    private int startingNextCellY = 0;
    private int endingNextCellX = 0;
    private int endingNextCellY = 0;
    private int endX = 0;
    private int endY = 0;
    public ViewGame(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, paint);
    }
    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh){
        getLayout(width, height);
        createBackgroundBitmap(width, height);
    }

    private void getLayout(int width, int height) {
        totalWidth = width;
        totalHeight = height;
        cellSize = (int) (width / 7.5);
        gapSize = cellSize / 16;
        startingX = gapSize;
        startintY = height - width +gapSize;
        endX = width - gapSize;
        endY = height - gapSize;
        nextCellSize = cellSize * 4 / 5;
        endingNextCellX = endX;
        endingNextCellY = height - width - cellSize / 2;
        startingNextCellX = endingNextCellX - 3*nextCellSize -2*gapSize;
        startingNextCellY = endingNextCellY - 2*nextCellSize - gapSize;
    }

    private void createBackgroundBitmap(int width, int height){
        background = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas backgroundCanvas = new Canvas(background);
        drawBackgroundGrid(backgroundCanvas);
        drawNextGrid(backgroundCanvas);
    }

    private void drawNextGrid(Canvas backgroundCanvas) {
        Resources resources = getResources();
        Drawable nextCell = resources.getDrawable(R.drawable.cell_next);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                int sX = startingNextCellX + (nextCellSize + gapSize) * i;
                int sY = startingNextCellY + (nextCellSize + gapSize) * j;
                int eX = sX + nextCellSize;
                int eY = sY + nextCellSize;
                drawDrawable(backgroundCanvas, nextCell, sX, sY, eX, eY);
            }
        }
    }

    private void drawBackgroundGrid(Canvas backgroundCanvas) {
        Resources resources = getResources();
        Drawable backgroundCell = resources.getDrawable(R.drawable.cell_background);
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                int sX = startingX + (cellSize + gapSize) * i;
                int sY = startintY + (cellSize + gapSize) * j;
                int eX = sX + cellSize;
                int eY = sY + cellSize;
                drawDrawable(backgroundCanvas, backgroundCell, sX, sY, eX, eY);
            }
        }
    }

    private void drawDrawable(Canvas canvas, Drawable draw, int sX, int sY, int eX, int eY) {
        draw.setBounds(sX, sY, eX, eY);
        draw.draw(canvas);
    }
}
