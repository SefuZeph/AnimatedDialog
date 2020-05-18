package com.sefu.animateddialog

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.annotation.RawRes
import com.airbnb.lottie.LottieAnimationView
import com.sefu.animateddialog.dialog.DialogButton
import com.sefu.animateddialog.interfaces.DialogInterface
import com.sefu.animateddialog.interfaces.OnCancelListener
import com.sefu.animateddialog.interfaces.OnShowListener
import org.jetbrains.annotations.NotNull


abstract class AbstractDialog(
    @NotNull mActivity: Activity,
    @NotNull title: String,
    @NotNull message: String,
    mCancelable: Boolean,
    @NotNull mPositiveButton: DialogButton,
    @RawRes mAnimationResId: Int,
    @NotNull mAnimationFile: String
) :
    DialogInterface {
    protected lateinit var mDialog: Dialog
    protected lateinit var mAnimationView: LottieAnimationView
    protected lateinit var mOnCancelListener: OnCancelListener
    protected lateinit var mOnShowListener: OnShowListener

    protected fun view(
        @NotNull layoutInflater: LayoutInflater,
        @Nullable container: ViewGroup
    ): View {

        val view = layoutInflater.inflate(R.layout.layout_animated_dialog, container, false)

        return view
    }

    interface OnClickListener {
        fun onClick(dialogInterface: DialogInterface, button: Int)
    }
}