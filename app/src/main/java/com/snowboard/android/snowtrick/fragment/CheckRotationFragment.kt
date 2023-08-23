package com.snowboard.android.snowtrick.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.snowboard.android.snowtrick.R

class CheckRotationFragment : DialogFragment() {

    private var _listenerItems: OnDialogSelectedItems? = null
    private val listenerItems
        get() = _listenerItems ?: throw IllegalStateException ("Property listenerItems for " +
                "CheckRotationFragment must not be null")

//    определяем интерфейс для возврата значения из диалогового окна
    interface OnDialogSelectedItems {
        fun onSelectedItemsRotation(selectedItems: ArrayList<Int>)
    }

//    переопределяем адаптер для передачи действий из MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
//            слушатель для кнопок (сохраняет в себе выбранные элементы)
            _listenerItems = context as OnDialogSelectedItems
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() + "must implement MyDialogListener"))
        }
    }

//    создаем диалог
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>()
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.choose_rotation)
                .setMultiChoiceItems(
                    R.array.rotation, null
                ) { _, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(which)
                    }
                }
                .setPositiveButton(R.string.ok) { dialog, id ->
                    listenerItems.onSelectedItemsRotation(selectedItems)
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity for CheckRotationFragment cannot be null")
    }
}