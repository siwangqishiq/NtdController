package com.xinlan.component;

import com.xinlan.view.MainView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Controller {
	public static int DIR_WIDTH;
	private static int padLeft = 10;
	private static int dirBaseZoneRadius, dirRadius, btnRadius;
	private static int dirCenterX, dirCenterY;
	private int dir_x, dir_y,btnA_x,btnA_y,btnB_x,btnB_y;
	private Paint paint;
	private int dirBaseColor = Color.rgb(226, 226, 226);
	private int dirColor = Color.rgb(75, 140, 247);
	private int btnNormalColor = Color.rgb(112, 169, 223);
	private int btnPressedColor = Color.rgb(71, 141, 211);

	public Controller() {
		DIR_WIDTH = MainView.screenH >> 2;// ÆÁÄ»¸ß¶È
		dirBaseZoneRadius = DIR_WIDTH;
		dirRadius = dirBaseZoneRadius >> 1;
		dirCenterX = padLeft + dirBaseZoneRadius;
		dirCenterY = MainView.screenH >> 1;
		btnRadius = dirRadius;
		dir_x = dirCenterX;
		dir_y = dirCenterY;
		paint = new Paint();
		paint.setAntiAlias(true);
	}

	public void logic() {

	}

	public void draw(Canvas canvas) {
		drawDir(canvas);
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
		
		canvas.restore();
	}
}// end class
