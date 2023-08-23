package com.snowboard.android.snowtrick.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.snowboard.android.snowtrick.R
import com.snowboard.android.snowtrick.SnowboardTrick
import com.snowboard.android.snowtrick.ext.getFormatTextAssets
import com.snowboard.android.snowtrick.ext.getDescriptionTricks
import com.snowboard.android.snowtrick.ext.readTextFromAsset
import com.snowboard.android.snowtrick.ext.errorUnknownGrab

lateinit var getGrabFromDialog: SnowboardTrick
const val grabsDataPath = "description-grabs.txt"

class DescriptionTrick: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(R.string.description_trick)
                .setMessage(context
                    ?.readTextFromAsset(grabsDataPath)
                    ?.getFormatTextAssets()
                    ?.getDescriptionTricks(getGrabFromDialog)
                    ?:errorUnknownGrab
                )
                .setPositiveButton(R.string.ok) { dialog, id ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}