package com.aizhunimei.rtmpplayer.nativeutil;

import android.view.Surface;

public class NativeUtil {

	public static native int naInit(String pFileName);

	public static native int[] naGetVideoRes();

	public static native void naSetSurface(Surface pSurface);

	public static native int naSetup(int pWidth, int pHeight);

	public static native void naPlay();

	public static native void naStop();

}
