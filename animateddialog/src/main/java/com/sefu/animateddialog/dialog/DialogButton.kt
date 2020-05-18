package com.sefu.animateddialog.dialog

import com.sefu.animateddialog.AbstractDialog

data class DialogButton(
    private var title: String,
    private var icon: Int,
    private val onClickListener: AbstractDialog.OnClickListener
)