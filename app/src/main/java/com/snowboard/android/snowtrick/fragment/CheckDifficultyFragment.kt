package com.snowboard.android.snowtrick.fragment

import android.app.Dialog
import androidx.fragment.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.app.AlertDialog
import com.snowboard.android.snowtrick.R

class CheckDifficultyFragment: DialogFragment() {

    lateinit var listenerItems: OnDialogSelectedItems

    //    определяем интерфейс для возврата значения из диалогового окна
    interface OnDialogSelectedItems {
        fun onSelectedItemsDifficulty(selectedItems: ArrayList<Int>)
    }

//    переопределяем адаптер для передачи действий из MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
//            слушатель кнопок (сохраняет в себе выбранные элементы)
            listenerItems = context as OnDialogSelectedItems
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() + "must implement CheckDifficultyFragment"))
        }
    }

//    создаем диалог
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>()
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.choose_difficulty)
                .setMultiChoiceItems(
                    R.array.difficulty, null
                ) { _, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(which)
                    }
                }
                .setPositiveButton(R.string.ok) { dialog, id ->
                    listenerItems.onSelectedItemsDifficulty(selectedItems)
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}