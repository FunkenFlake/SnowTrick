package com.snowboard.android.snowtrick

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {

    lateinit var listener: MyDialogListener
    lateinit var listenerItems: OnDialogSelectedItems

//    определяем интерфейс для действия кнопок ok и cancel
    interface MyDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
//    определяем интерфейс для возврата значения из диалогового окна
    interface OnDialogSelectedItems {
        fun onSelectedItems(selectedItems: ArrayList<Int>)
    }



//    переопределяем адаптер (наверно) для передачи действий из MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
//            слушатель для кнопок
            listener = context as MyDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() + "must implement MyDialogListener"))
        }
//    слушатель, который сохраняет в себе выбранные элементы
        listenerItems = context as OnDialogSelectedItems
    }

//    создаем диалог
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>()
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.choose_rotation)
                .setMultiChoiceItems(R.array.rotation, null
                ) { _, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(which)
                    }
                }
                .setPositiveButton(R.string.ok) { dialog, id ->
                    listener.onDialogPositiveClick(this)
                    listenerItems.onSelectedItems(selectedItems)
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    listener.onDialogNegativeClick(this)
                    dialog.dismiss()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
