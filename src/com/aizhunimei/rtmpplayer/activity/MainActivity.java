package com.aizhunimei.rtmpplayer.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import com.aizhunimei.rtmpplayer.R;

public class MainActivity extends Activity implements SurfaceHolder.Callback {
	
	
	

	
	private String TAG = "MainActivity";

	private String RTMP_URL = "rtmp://192.168.1.115/live/livestream";

	private SurfaceView mSurfaceView;
	
	
	
	
	

	static {
		System.loadLibrary("avutil-54");
		System.loadLibrary("avcodec-56");
		System.loadLibrary("avformat-56");
		System.loadLibrary("swscale-3");
		System.loadLibrary("rtmpplayer");
		System.loadLibrary("avfilter-5");
		System.loadLibrary("swresample-1");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
		mSurfaceView.getHolder().addCallback(this);
		
//		String mypath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/1.mp4";

		int value = naInit(RTMP_URL);
		Log.e(TAG, "value == " + value);

		try {

			int[] res = naGetVideoRes();
			Log.d(TAG, "res width " + res[0] + ": height " + res[1]);
			int[] screenRes = getScreenRes();
			int width, height;
			float widthScaledRatio = screenRes[0] * 1.0f / res[0];
			float heightScaledRatio = screenRes[1] * 1.0f / res[1];
			if (widthScaledRatio > heightScaledRatio) {
				// use heightScaledRatio
				width = (int) (res[0] * heightScaledRatio);
				height = screenRes[1];
			} else {
				// use widthScaledRatio
				width = screenRes[0];
				height = (int) (res[1] * widthScaledRatio);
			}
			Log.d(TAG, "width " + width + ",height:" + height);
			updateSurfaceView(width, height);
			naSetup(width, height);
			naPlay();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		naStop();
	}

	private void updateSurfaceView(int pWidth, int pHeight) {
		// update surfaceview dimension, this will cause the native window to
		// change
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSurfaceView
				.getLayoutParams();
		params.width = pWidth;
		params.height = pHeight;
		mSurfaceView.setLayoutParams(params);
	}

	@SuppressLint("NewApi")
	private int[] getScreenRes() {
		int[] res = new int[2];
		Display display = getWindowManager().getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= 13) {
			Point size = new Point();
			display.getSize(size);
			res[0] = size.x;
			res[1] = size.y;
		} else {
			res[0] = display.getWidth(); // deprecated
			res[1] = display.getHeight(); // deprecated
		}
		return res;
	}

	private static native int naInit(String pFileName);

	private static native int[] naGetVideoRes();

	private static native void naSetSurface(Surface pSurface);

	private static native int naSetup(int pWidth, int pHeight);

	private static native void naPlay();

	private static native void naStop();

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

		naSetSurface(holder.getSurface());
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		naSetSurface(null);
	}

}
