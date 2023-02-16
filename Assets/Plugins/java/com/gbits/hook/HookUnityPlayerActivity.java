package com.gbits.hook;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

//import com.leiting.unity.AppActivity;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class HookUnityPlayerActivity extends Activity
{
    // Setup activity layout
    @Override protected void onCreate(Bundle savedInstanceState)
    {
		//Bootstrap.InitNativeLibBeforeUnityPlay(getApplication().getApplicationContext().getFilesDir().getPath(), getApplication().getApplicationContext().getExternalFilesDir(null).getPath());
        super.onCreate(savedInstanceState);
    }
}
