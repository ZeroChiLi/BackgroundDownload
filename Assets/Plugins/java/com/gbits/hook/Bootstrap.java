package com.gbits.hook;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.io.File;
import com.unity3d.player.UnityPlayer;

public class Bootstrap
{
    public static native void init(String internalPath, String externalPath);
    
    public static void InitNativeLibBeforeUnityPlay(String internalPath, String externalPath)
    {
        System.loadLibrary("main");
        System.loadLibrary("unity");
        System.loadLibrary("bootstrap");
        init(internalPath, externalPath);
    }

    //app 重启
    public static void RestartApp(int delay) {
        // 重启
        Log.d("Unity", "========RestartApp "+delay);
        
        Activity activity = UnityPlayer.currentActivity;
 
        Intent restartIntent = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName() );
        PendingIntent intent = PendingIntent.getActivity(activity, 0,restartIntent,0);
        AlarmManager manager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC, System.currentTimeMillis()+delay, intent);
        activity.finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}