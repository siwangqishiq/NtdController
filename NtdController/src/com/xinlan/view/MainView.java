package com.xinlan.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Paint paint;
	private Thread th;
	private boolean flag;
	private Canvas canvas;
	private Context context;
	public static int screenW, screenH;
	private Resources res = this.getResources();

	public static int GAME_STATE = 1;

	public MainView(Context context) {
		super(context);
		this.context = context;
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setKeepScreenOn(true);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		init();
		flag = true;
		// 实例线程
		th = new Thread(this);
		// 启动线程
		th.start();
	}

	/**
	 * 初始化
	 */
	public void init() {
		GAME_STATE = 1;
	}

	public void draw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				switch (GAME_STATE) {
				case 1:
					canvas.drawColor(Color.WHITE);
					break;
				}
			}// end if
		} catch (Exception e) {
		} finally {
			if (canvas != null) {
				sfh.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void logic() {
		
	}

	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			draw();
			logic();
			long end = System.currentTimeMillis();
			// System.out.println(end - start);
			try {
				if (end - start < 30) {
					Thread.sleep(30 - (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end while
	}

	/**
	 * 监听触屏事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
}// end class
