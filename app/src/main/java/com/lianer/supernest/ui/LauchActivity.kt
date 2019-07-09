package com.lianer.supernest.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.lianer.supernest.R
import com.lianer.supernest.base.BaseActivity
import com.lianer.supernest.constant.Tag
import com.lianer.supernest.manager.HLWalletManager
import com.lianer.supernest.utils.ACache

class LauchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lauch)

        navigateToMain()
    }

    private fun navigateToMain() {
        //延时3秒跳转
        Handler().postDelayed({
            //判断钱包是否完成备份流程标识
            if (TextUtils.isEmpty(ACache.get(this@LauchActivity).getAsString(Tag.IS_BACKUP))) {
                if (HLWalletManager.shared().getCurrentWallet(this@LauchActivity) != null) {
                    HLWalletManager.shared().deleteWallet(this@LauchActivity)
                }
            }

            val intent: Intent
            if (HLWalletManager.shared().getCurrentWallet(this@LauchActivity) == null) {
                //未创建钱包跳转
                intent = Intent(this@LauchActivity, CreateAndImportActivity::class.java)
            } else {
                //已创建/导入钱包跳转
                intent = Intent(this@LauchActivity, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)

//        Flowable.just(1)
//            .delay {  }
    }
}
