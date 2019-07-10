package com.lianer.supernest

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import com.lianer.supernest.base.BaseActivity
import com.lianer.supernest.etherscan.SafeWebChromeClient
import com.lianer.supernest.etherscan.SafeWebViewClient
import com.lianer.supernest.widget.ProgressDialog
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    var mDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        if (mDialog==null){
            mDialog = ProgressDialog()
        }

        mDialog?.isCancelable = true

        if (this is AppCompatActivity){
            val activity = this
            activity
                .supportFragmentManager
                .beginTransaction()
                .add(mDialog!!, mDialog!!.tag)
                .commitAllowingStateLoss()
        }

        webview.webViewClient = object : SafeWebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
//                dismiss()
            }
        }

        webview.webChromeClient = SafeWebChromeClient()

        val webSettings = webview.settings ?: return
        // 支持 Js 使用
        webSettings.javaScriptEnabled = true
        // 开启DOM缓存
        webSettings.domStorageEnabled = true
        // 开启数据库缓存
        webSettings.databaseEnabled = true
        // 设置 WebView 的缓存模式
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        // 支持启用缓存模式
        webSettings.setAppCacheEnabled(true)
        // 关闭密码保存提醒功能
        webSettings.savePassword = false
        // 支持缩放
        webSettings.setSupportZoom(true)
        // 设置 UserAgent 属性
        webSettings.userAgentString = ""
        // 允许加载本地 html 文件/false
        webSettings.allowFileAccess = true
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webview.loadUrl(intent.getStringExtra("webUrl"))
    }

    private fun dismiss() {
        if (mDialog != null) {
            try {
                mDialog?.dismiss()
                mDialog = null
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
