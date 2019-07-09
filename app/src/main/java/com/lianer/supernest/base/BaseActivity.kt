package com.lianer.supernest.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.lianer.supernest.utils.qumi.QMUIStatusBarHelper

open class BaseActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 固定竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // 状态栏设置
        QMUIStatusBarHelper.translucent(this)

    }
}