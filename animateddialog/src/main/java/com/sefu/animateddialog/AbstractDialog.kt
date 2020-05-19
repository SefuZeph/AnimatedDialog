package com.sefu.animateddialog

import android.app.Activity
import android.app.Dialog
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.button.MaterialButton
import com.sefu.animateddialog.constants.NO_ICON
import com.sefu.animateddialog.dialog.DialogButton
import com.sefu.animateddialog.interfaces.DialogInterface
import com.sefu.animateddialog.interfaces.OnShowListener


abstract class AbstractDialog(
    @NonNull val mActivity: Activity,
    @NonNull val title: String,
    @NonNull val message: String,
    mCancelable: Boolean,
    @NonNull val mPositiveButton: DialogButton,
    @RawRes mAnimationResId: Int,
    @NonNull mAnimationFile: String
) :
    DialogInterface {
    private lateinit var mDialog: Dialog
    private lateinit var mAnimationView: LottieAnimationView
    private lateinit var mOnShowListener: OnShowListener

    protected fun view(
        @NonNull layoutInflater: LayoutInflater,
        @Nullable container: ViewGroup
    ): View {

        val view = layoutInflater.inflate(R.layout.layout_animated_dialog, container, false)
        val mTitleView: TextView = view.findViewById(R.id.textView_title)
        val mMessageView: TextView = view.findViewById(R.id.textView_message)
        val mPositiveButtonView: MaterialButton = view.findViewById(R.id.button_positive)
        mAnimationView = view.findViewById(R.id.animation_view)

        // Set Text visibility and value
        mTitleView.visibility = View.VISIBLE
        mTitleView.text = title

        // Set Message visibility and value
        mMessageView.visibility = View.VISIBLE
        mMessageView.text = message

        //set button include icon as per the sdk version of the phone
        mPositiveButtonView.visibility = View.VISIBLE
        mPositiveButtonView.text = mPositiveButton.title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mPositiveButton.icon != NO_ICON) {
            mPositiveButtonView.icon = mActivity.getDrawable(mPositiveButton.icon);
        }

        // apply set styles
        val styledAttributes = mActivity.theme.obtainStyledAttributes(R.styleable.MaterialDialog)
        try {
            //set background color
            view.setBackgroundColor(
                styledAttributes.getColor(
                    R.styleable.MaterialDialog_material_dialog_background,
                    ContextCompat.getColor(
                        mActivity.applicationContext,
                        R.color.material_dialog_background
                    )
                )
            )

            // Set Title Text Color
            mTitleView.setTextColor(
                styledAttributes.getColor(
                    R.styleable.MaterialDialog_material_dialog_title_text_color,
                    ContextCompat.getColor(
                        mActivity.applicationContext,
                        R.color.material_dialog_title_text_color
                    )
                )
            )

            // Set Message Text Color
            mMessageView.setTextColor(
                styledAttributes.getColor(
                    R.styleable.MaterialDialog_material_dialog_message_text_color,
                    ContextCompat.getColor(
                        mActivity.applicationContext,
                        R.color.material_dialog_message_text_color
                    )
                )
            )

            // Set Positive Button Icon Tint
            var mPositiveButtonTint: ColorStateList? = styledAttributes.getColorStateList(
                R.styleable.MaterialDialog_material_dialog_positive_button_text_color
            )

            if (mPositiveButtonTint == null) {
                mPositiveButtonTint = ContextCompat.getColorStateList(
                    mActivity.applicationContext,
                    R.color.material_dialog_positive_button_text_color
                )
            }
            mPositiveButtonView.setTextColor(mPositiveButtonTint)
            mPositiveButtonView.iconTint = mPositiveButtonTint

            // Set Positive Button Background Tint
            var mBackgroundTint: ColorStateList? = styledAttributes.getColorStateList(
                R.styleable.MaterialDialog_material_dialog_positive_button_color
            )

            if (mBackgroundTint == null) {
                mBackgroundTint = ContextCompat.getColorStateList(
                    mActivity.applicationContext,
                    R.color.material_dialog_positive_button_color
                )
            }
            mPositiveButtonView.backgroundTintList = mBackgroundTint


        } catch (exception: Exception) {
            exception.printStackTrace()
        } finally {
            styledAttributes.recycle()
        }

        return view
    }

    //show dialog
    fun show() {
        mDialog.show()
    }

    /**
     * @param onShowListener interface for callback events when dialog is displayed
     * */
    fun setOnShowListener(@NonNull onShowListener: OnShowListener) {
        this.mOnShowListener = onShowListener

        mDialog.setOnShowListener {
            showCallback()
        }
    }

    /**
     * @return [LottieAnimationView] from the Dialog.
     */
    open fun getAnimationView(): LottieAnimationView? {
        return mAnimationView
    }

    fun showCallback() {
        mOnShowListener.onShow(this)
    }

    interface OnClickListener {
        fun onClick(dialogInterface: DialogInterface, button: Int)
    }
}