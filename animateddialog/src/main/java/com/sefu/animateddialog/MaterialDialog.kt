package com.sefu.animateddialog

import android.app.Activity
import android.app.AlertDialog
import androidx.annotation.NonNull
import com.sefu.animateddialog.constants.NO_ANIMATION
import com.sefu.animateddialog.dialog.DialogButton

/**
 * Create a material dialog
 * */
class MaterialDialog(
    mActivity: Activity,
    title: String,
    message: String,
    mCancelable: Boolean,
    mPositiveButton: DialogButton,
    mAnimationResId: Int,
    mAnimationFile: String
) : AbstractDialog(
    mActivity,
    title,
    message,
    mCancelable,
    mPositiveButton,
    mAnimationResId,
    mAnimationFile
) {

    fun MaterialDialog(
        mActivity: Activity,
        title: String,
        message: String,
        mCancelable: Boolean,
        mPositiveButton: DialogButton,
        mAnimationResId: Int,
        mAnimationFile: String
    ) {

        val builder = AlertDialog.Builder(mActivity)
        val inflater = mActivity.layoutInflater
        val dialogView = view(inflater, null)
        builder.setView(dialogView)
        // Set Cancelable property
        builder.setCancelable(mCancelable);
        // Create and show dialog
        mDialog = builder.create()
    }

    /**
     * Builder for the material dialog
     * */
    class Builder {
        private lateinit var activity: Activity
        private lateinit var title: String
        private lateinit var message: String
        private lateinit var positiveButton: DialogButton
        private var isCancelable = false
        private val animationResId: Int = NO_ANIMATION
        private lateinit var animationFile: String

        /**
         * @param activity where the material dialog will be built from
         * */
        fun Builder(@NonNull activity: Activity) {
            this.activity = activity
        }

        /**
         * @param title Sets the Title of Material Dialog.
         * @return this, for chaining.
         */

        @NonNull
        fun setTitle(@NonNull title: String): Builder {
            this.title = title
            return this
        }

        /**
         * @param message Sets the Message of Material Dialog.
         * @return this, for chaining.
         */

        @NonNull
        fun setMessage(@NonNull message: String): Builder {
            this.message = message
            return this
        }

        /**
         * @param isCancelable Sets cancelable property of Material Dialog.
         * @return this, for chaining.
         */
        @NonNull
        fun setCancelable(isCancelable: Boolean): Builder {
            this.isCancelable = isCancelable
            return this
        }

        /** It sets the json file to the {@link com.airbnb.lottie.LottieAnimationView} from assets.
         * @param fileName sets the file from assets to {@link com.airbnb.lottie.LottieAnimationView}.
         * @return this, for chaining.
         */
        @NonNull
        fun setAnimation(@NonNull fileName: String): Builder {
            this.animationFile = fileName
            return this
        }

        /**
         * Build the {@link MaterialDialog}.
         */
        @NonNull
        fun build(): MaterialDialog {
            return MaterialDialog(
                activity,
                title,
                message,
                isCancelable,
                positiveButton,
                animationResId,
                animationFile
            )
        }

    }
}