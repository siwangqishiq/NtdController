package com.xinlan.component;

import com.xinlan.utils.Common;
import com.xinlan.view.MainView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Controller {
	public int DIR_WIDTH, BTN_WIDTH;
	private int screenHeight, screenWidth;
	private static final int padLeft = 20;
	private static final int padRight = 10;
	private static final int padTop = 80;

	private static int dirBaseZoneRadius, dirRadius, btnRadius;
	private static int dirCenterX, dirCenterY;
	private int dir_x, dir_y, btnA_x, btnA_y, btnB_x, btnB_y;
	private Paint paint;
	private int dirBaseColor = Color.rgb(226, 226, 226);
	private int dirColor = Color.rgb(75, 140, 247);
	private int btnNormalColor = Color.rgb(112, 169, 223);
	private int btnPressedColor = Color.rgb(71, 141, 211);

	private boolean dirIsDown = false;
	private boolean isBtnAPressed = false, isBtnBPressed = false;
	private int dirBoundXMin, dirBoundXMax, dirBoundYMin, dirBoundYMax;

	public Controller() {
		screenHeight = MainView.screenH;
		screenWidth = MainView.screenW;
		BTN_WIDTH = screenHeight / 8;
		DIR_WIDTH = screenHeight / 4;
		dirBaseZoneRadius = DIR_WIDTH;
		dirRadius = dirBaseZoneRadius / 2;
		dirCenterX = padLeft + dirBaseZoneRadius;
		dirCenterY = screenHeight / 2 + padTop;
		btnRadius = BTN_WIDTH;
		btnA_x = screenWidth - padRight - btnRadius;
		btnB_x = btnA_x;
		btnA_y = screenHeight / 2;
		btnB_y = btnA_y + btnRadius + btnRadius + 10;
		dir_x = dirCenterX;
		dir_y = dirCenterY;

		dirBoundXMin = dirCenterX - dirRadius;
		dirBoundXMax = dirCenterX + dirRadius;
		dirBoundYMin = dirCenterY - dirRadius;
		dirBoundYMax = dirCenterY + dirRadius;

		//
		paint = new Paint();
		paint.setAntiAlias(true);
	}

	public void logic() {

	}

	public void draw(Canvas canvas) {
		drawDir(canvas);
		drawBtn(canvas);
	}

	private void drawDir(Canvas canvas) {
		canvas.save();
		paint.setColor(dirBaseColor);
		canvas.drawCircle(dirCenterX, dirCenterY, dirBaseZoneRadius, paint);
		paint.setColor(dirColor);
		canvas.drawCircle(dir_x, dir_y, dirRadius, paint);
		canvas.restore();
	}

	private void drawBtn(Canvas canvas) {
		canvas.save();
		int colorA = isBtnAPressed?btnPressedColor:btnNormalColor;
	    paint.setColor(colorA);
		canvas.drawCircle(btnA_x, btnA_y, btnRadius, paint);
		int colorB = isBtnBPressed?btnPressedColor:btnNormalColor;
	    paint.setColor(colorB);
		canvas.drawCircle(btnB_x, btnB_y, btnRadius, paint);
		canvas.restore();
	}

	public void onTouch(MotionEvent event) {
		int x = (int) event.getX(), y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isInDirZoneDown(x, y);
			isBtnAZoneDown(x,y);
			isBtnBZoneDown(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			isInDirZoneMove(x, y);
			isBtnAZoneMove(x,y);
			isBtnBZoneMove(x,y);
			break;
		case MotionEvent.ACTION_UP:
			isInDirZoneUp(x, y);
			isBtnAZoneUp(x,y);
			isBtnBZoneUp(x, y);
			break;
		}
	}

	private int isBtnAZoneDown(int x, int y) {
		if (Common.isInCircle(x, y, btnA_x, btnA_y, btnRadius)) {// A down
			isBtnAPressed=true;
		}
		return 0;
	}
	
	private int isBtnAZoneMove(int x, int y) {
		if (Common.isInCircle(x, y, btnA_x, btnA_y, btnRadius)) {// A down
			isBtnAPressed=true;
		}
		return 0;
	}

	private int isBtnAZoneUp(int x, int y) {
		if (Common.isInCircle(x, y, btnA_x, btnA_y, btnRadius)) {// A down
			isBtnAPressed=false;
		}
		return 0;
	}
	
	private int isBtnBZoneDown(int x, int y) {
		if (Common.isInCircle(x, y, btnB_x, btnB_y, btnRadius)) {// A down
			isBtnBPressed=true;
		}
		return 0;
	}
	
	private int isBtnBZoneMove(int x, int y) {
		if (Common.isInCircle(x, y, btnB_x, btnB_y, btnRadius)) {// A down
			isBtnBPressed=true;
		}
		return 0;
	}

	private int isBtnBZoneUp(int x, int y) {
		if (Common.isInCircle(x, y, btnB_x, btnB_y, btnRadius)) {// A down
			isBtnBPressed=false;
		}
		return 0;
	}

	private int isInDirZoneDown(int x, int y) {
		if (Common.isInCircle(x, y, dir_x, dir_y, dirRadius)) {
			dirIsDown = true;
			dir_x = dirCenterX;
			dir_y = dirCenterY;
		}
		return 1;
	}

	private int isInDirZoneMove(int x, int y) {
		if (Common.isInCircle(x, y, dir_x, dir_y, dirRadius)) {
			dirIsDown = true;
			dir_x = x;
			dir_y = y;
			if (dir_x < dirBoundXMin) {
				dir_x = dirBoundXMin;
			}
			if (dir_x > dirBoundXMax) {
				dir_x = dirBoundXMax;
			}
			if (dir_y < dirBoundYMin) {
				dir_y = dirBoundYMin;
			}
			if (dir_y > dirBoundYMax) {
				dir_y = dirBoundYMax;
			}
		}else{
			dirIsDown = false;
		}
		return 0;
	}

	private int isInDirZoneUp(int x, int y) {
		if (Common.isInCircle(x, y, dir_x, dir_y, dirRadius)) {
			dirIsDown = false;
		}
		dir_x = dirCenterX;
		dir_y = dirCenterY;
		return 1;
	}

}// end class
