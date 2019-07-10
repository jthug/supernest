package com.lianer.supernest.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import com.gs.keyboard.KeyboardType
import com.gs.keyboard.SecurityConfigure
import com.gs.keyboard.SecurityKeyboard
import com.lianer.supernest.R
import com.lianer.supernest.WebViewActivity
import com.lianer.supernest.base.BaseActivity
import com.lianer.supernest.constant.Constants
import com.lianer.supernest.utils.KLog
import kotlinx.android.synthetic.main.activity_create_wallet.*

class CreateWalletActivity : BaseActivity() {

    private var isPsd: Boolean = false
    private var isRepsd: Boolean = false
    private var isAgree = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)

        initToolbar()

        val configure = SecurityConfigure()
            .setDefaultKeyboardType(KeyboardType.NUMBER)
        val securityKeyboard = SecurityKeyboard(ll_root, configure)

        btn_create_wallet.setOnClickListener { v -> createWallet() }
        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isPsd = s?.length ?: 0 >= 8
                setCreateWalletEnable(isPsd && isRepsd && isAgree)
            }

        })

        et_repassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isRepsd = s?.length ?: 0 >=8
                setCreateWalletEnable(isPsd && isRepsd && isAgree)
            }

        })

        tv_agree_create_wallet.setOnClickListener { v->
            isAgree = !isAgree
            tv_agree_create_wallet.setCompoundDrawablesWithIntrinsicBounds(if (isAgree){
                resources.getDrawable(R.drawable.ic_agreement_selected)
            }else{
                resources.getDrawable(R.drawable.ic_agreement_unselected)
            },null,null,null)
            setCreateWalletEnable(isPsd && isRepsd && isAgree)
        }

        tv_user_agreement.setOnClickListener { v->
            val intent = Intent(this@CreateWalletActivity, WebViewActivity::class.java)
            intent.putExtra("webUrl", Constants.USER_AGREEMENT)
            startActivity(intent)
        }

    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { v->finish() }
    }

    private fun setCreateWalletEnable(isWalletCreate: Boolean) {
        btn_create_wallet.setEnabled(isWalletCreate)
    }

    private fun createWallet() {


    }
}
