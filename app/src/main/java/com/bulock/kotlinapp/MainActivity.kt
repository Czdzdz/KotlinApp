package com.bulock.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import com.blankj.utilcode.util.ToastUtils
import com.bulock.common.Dialog.VersionUpdateDialog
import kotlinx.android.synthetic.main.activity_main.*
import com.bulock.common.Widget.SpinningView
import android.widget.TextView
import android.widget.Toast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bulock.common.Widget.SpinningAdapter


class MainActivity : AppCompatActivity() {

    var init = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        tv_update_version.setOnClickListener {
            ToastUtils.showLong("版本更新")
            var updateDialog = VersionUpdateDialog.newInstance(this)
            updateDialog.setForceUpdate(false)
            updateDialog.show(supportFragmentManager, "版本更新")
        }

        tv_force_update_version.setOnClickListener {
            ToastUtils.showLong("强制更新")
            var forceUpdateDialog = VersionUpdateDialog.newInstance(this)
            forceUpdateDialog.setForceUpdate(true)
            forceUpdateDialog.show(supportFragmentManager, "强制更新")
        }

        tv_switch_mode.setOnClickListener {
            init++
            if (init % 2 == 0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                tv_switch_mode.text = "夜间模式"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                tv_switch_mode.text = "日间模式"
            }

        }
    }
}
