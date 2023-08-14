package com.example.moec.JavaClass;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class MobileDataUtils {
    public static void openMobileDataSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }
}
