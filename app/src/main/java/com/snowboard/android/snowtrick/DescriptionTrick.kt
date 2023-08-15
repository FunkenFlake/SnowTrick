package com.snowboard.android.snowtrick

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

lateinit var getGrabFromDialog: SnowboardTrick

class DescriptionTrick: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(R.string.description_trick)
                .setMessage("nothing")
                .setPositiveButton(R.string.ok) { dialog, id ->
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}