package com.sefu.animateddialog.dialog

import com.sefu.animateddialog.AbstractDialog

data class DialogButton(
    var title: String,
    var icon: Int,
    val onClickListener: AbstractDialog.OnClickListener
)