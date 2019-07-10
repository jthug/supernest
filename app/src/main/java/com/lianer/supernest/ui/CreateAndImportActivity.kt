package com.lianer.supernest.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lianer.supernest.R
import com.lianer.supernest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_create_and_import.*

class CreateAndImportActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_and_import)

        initClick()
    }

    private fun initClick() {
        btn_create.setOnClickListener(this)
        btn_import.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_create->{
                val creIntent = Intent(this, CreateWalletActivity::class.java)
                startActivity(creIntent)
            }
            R.id.btn_import->{
                val impIntent = Intent(this, ImportWalletActivity::class.java)
                startActivity(impIntent)
            }
        }
    }
}
