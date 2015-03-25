package com.liujilong.carson.game7plus7;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;


public class MainView extends View {
    private MainGame game = new MainGame();;
    private Paint paint = new Paint();
    private Bitmap background;
    private Drawable[] bitmapCell = new Drawable[5];
    private int totalWidth;
    private int totalHeight;
    private int cellSize = 0;
    private int nextCellSize = 0;
    private int gapSize = 0;
    private int startingX = 0;
    private int startingY = 0;
    private int startingNextCellX = 0;
    private int startingNextCellY = 0;
    private int endingNextCellX = 0;
    private int endingNextCellY = 0;
    private int bigTextSize = 0;
    private int smallTextSize = 0;
    private int endX = 0;
    private int endY = 0;
    public MainView(Context context){
        super(context);
        game.newGame();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, paint);
        drawCells(canvas);

    }

    private void drawCells(Canvas canvas) {
        int colorIndex = 0;
        for (int xx = 0; xx < Grid.NumOfSquaresX; xx++){
            for (int yy = 0; yy < Grid.NumOfSquaresY; yy ++){
                int sX = startingX + (cellSize + gapSize) * xx;
                int sY = startingY + (cellSize + gapSize) * yy;
                int eX = sX + cellSize;
                int eY = sY + cellSize;
                colorIndex = game.getGrid().cells[xx][yy].getColorIndex();
                if (colorIndex != -1) {

                    drawDrawable(canvas, bitmapCell[colorIndex], sX, sY, eX, eY);
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh){
        getLayout(width, height);
        createBackgroundBitmap(width, height);
        createBitmapCells();
    }

    private void createBitmapCells() {
        Resources resources = getResources();
        bitmapCell[0] = resources.getDrawable(R.drawable.cell_red);
        bitmapCell[1] = resources.getDrawable(R.drawable.cell_blue);
        bitmapCell[2] = resources.getDrawable(R.drawable.cell_green);
        bitmapCell[3] = resources.getDrawable(R.drawable.cell_orange);
        bitmapCell[4] = resources.getDrawable(R.drawable.cell_purple);

    }

    private void getLayout(int width, int height) {
        totalWidth = width;
        totalHeight = height;
        cellSize = (int) (width / 7.5);
        gapSize = cellSize / 16;
        startingX = gapSize;
        startingY = height - width +gapSize;
        endX = width - gapSize;
        endY = height - gapSize;
        nextCellSize = cellSize * 4 / 5;
        endingNextCellX = endX;
        endingNextCellY = height - width - cellSize / 2;
        startingNextCellX = endingNextCellX - 3*nextCellSize -2*gapSize;
        startingNextCellY = endingNextCellY - 2*nextCellSize - gapSize;
        bigTextSize= cellSize/2;
        smallTextSize = cellSize / 3;
    }

    private void createBackgroundBitmap(int width, int height){
        background = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas backgroundCanvas = new Canvas(background);
        drawBackgroundGrid(backgroundCanvas);
        drawNextGrid(backgroundCanvas);
        drawNextText(backgroundCanvas);
        drawScoreText(backgroundCanvas);
    }

    private void drawNextText(Canvas backgroundCanvas) {
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(smallTextSize);
        backgroundCanvas.drawText(getResources().getString(R.string.next),
                startingNextCellX, startingNextCellY - gapSize, paint);
    }

    private void drawScoreText(Canvas backgroundCanvas) {
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(smallTextSize);
        backgroundCanvas.drawText(getResources().getString(R.string.score),
                startingX, startingNextCellY - gapSize, paint);
        backgroundCanvas.drawText(getResources().getString(R.string.combo),
                startingX, startingNextCellY - gapSize+cellSize, paint);

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
                int sY = startingY + (cellSize + gapSize) * j;
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
