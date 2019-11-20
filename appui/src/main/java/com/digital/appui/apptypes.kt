package com.digital.appui

import android.view.View
import com.digital.appui.dialog.AppDialog

typealias OnPrepareDialogView = AppDialog.(view: View, clickListener: View.OnClickListener) -> Unit
typealias OnDialogViewClick = (view: View, dialog: AppDialog) -> Unit
typealias OnDialogAdapterItemClick = (view: View, position: Int) -> Unit
typealias OnPrepareDialogAdapterView = AppDialog.(view: View,clickListener: View.OnClickListener,adapterItemClick:OnDialogAdapterItemClick) -> Unit
