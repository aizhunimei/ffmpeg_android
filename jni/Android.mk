LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)  
LOCAL_MODULE :=  swscale
LOCAL_SRC_FILES :=lib/libswscale-3.so  
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)  
LOCAL_MODULE :=  avcodec
LOCAL_SRC_FILES :=lib/libavcodec-56.so  
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)  
LOCAL_MODULE :=  avfilter
LOCAL_SRC_FILES :=lib/libavfilter-5.so  
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)  
LOCAL_MODULE :=  avformat
LOCAL_SRC_FILES :=lib/libavformat-56.so  
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)  
LOCAL_MODULE :=  avutil
LOCAL_SRC_FILES :=lib/libavutil-54.so  
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE :=  swresample
LOCAL_SRC_FILES :=lib/libswresample-1.so
include $(PREBUILT_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE    := rtmpplayer
LOCAL_SRC_FILES := rtmpplayer.c
LOCAL_LDLIBS := -llog -ljnigraphics -lz -landroid
LOCAL_SHARED_LIBRARIES := libavformat libavcodec libswscale libavutil
include $(BUILD_SHARED_LIBRARY)


