package com.bulock.common.Dialog

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.bulock.common.R
import com.labo.kaji.swipeawaydialog.support.v4.SwipeAwayDialogFragment

/**
 * @author cdd
 * describe 版本升级提示窗
 * @create 2018/12/19 17:04
 */

class VersionUpdateDialog : SwipeAwayDialogFragment(), View.OnClickListener {

    private var rootView: View? = null
    private var btnUpdateNow: TextView? = null//立即更新
    private var btnRemindMeLater: TextView? = null//稍后提醒
    private var tvUpdateDescription: TextView? = null//更新日志
    private var isForceUpdate: Boolean = false//强制更新

    private fun initData() {
        tvUpdateDescription!!.text = "2.0.2升级介绍\n1、杀了个程序员祭天\n2、杀了个测试祭天\n3、产品经理自杀"
    }

    private fun initView(rootView: View) {
        tvUpdateDescription = rootView.findViewById(R.id.tv_update_description)
        btnRemindMeLater = rootView.findViewById(R.id.btn_remind_me_later)
        btnUpdateNow = rootView.findViewById(R.id.btn_update_now)

        btnRemindMeLater!!.setOnClickListener(this)
        btnUpdateNow!!.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.style_common_dialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //点击外面是不会消失
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener { dialog, keyCode, event -> keyCode == KeyEvent.KEYCODE_BACK }
        return rootView
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_remind_me_later -> {
                ToastUtils.showShort("稍后提醒我")
                dismiss()
            }
            R.id.btn_update_now -> {
                ToastUtils.showShort("立即更新")
                dismiss()
            }
        }
    }

    override fun show(manager: FragmentManager, tag: String) {
        //这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    /**
     * 设置强制更新
     *
     * @param forceUpdate forceUpdate
     */
    fun setForceUpdate(forceUpdate: Boolean) {
        this.isForceUpdate = forceUpdate
        if (isForceUpdate) btnRemindMeLater!!.visibility = View.GONE
        isSwipeable = !isForceUpdate//根据是否强制可滑动隐藏
    }

    companion object {

        fun newInstance(context: Context): VersionUpdateDialog {
            val vud = VersionUpdateDialog()
            vud.rootView = View.inflate(context, R.layout.dialog_version_update, null)
            vud.initView(vud.rootView!!)
            vud.initData()
            return vud
        }
    }
}
