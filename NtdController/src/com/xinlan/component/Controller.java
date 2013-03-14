package com.xinlan.component;

import com.xinlan.view.MainView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Controller {
	public int DIR_WIDTH, BTN_WIDTH;
	private int screenHeight, screenWidth;
	private static final int padLeft = 10;
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
		btnB_y = btnA_y + btnRadius +btnRadius + 10;
		dir_x = dirCenterX;
		dir_y = dirCenterY;
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
		paint.setColor(btnNormalColor);
		canvas.drawCircle(btnA_x, btnA_y, btnRadius, paint);
		canvas.drawCircle(btnB_x, btnB_y, btnRadius, paint);
		canvas.restore();
	}
}// end class
