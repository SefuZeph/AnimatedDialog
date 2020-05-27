package com.sefu.animateddialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val animatedMaterialDialog: MaterialDialog = MaterialDialog.Builder(this)
            .setTitle("")
            .setMessage("")
            .setCancelable(false)
            .setAnimation("delete_anim.json")
            .build()

        animatedMaterialDialog.show()
    }
}
