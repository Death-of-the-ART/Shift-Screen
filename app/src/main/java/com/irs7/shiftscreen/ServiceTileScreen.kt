package com.irs7.shiftscreen

import android.app.AlertDialog
import android.provider.Settings
import android.service.quicksettings.TileService

class ServiceTileScreen: TileService() {

    override fun onClick() {
        super.onClick()
        dialog3()
    }

    //region Set Screen Timeout
    private fun dialog3() {
        Settings.System.getInt(
            contentResolver,
            Settings.System.SCREEN_OFF_TIMEOUT
        )

        val fileNames = arrayOf(
            "15 Seconds",
            "30 Seconds",
            "1 Minute",
            "5 Minutes",
            "15 Minutes",
            "30 Minutes",
            "3 Hours"
        )
        val numbers = intArrayOf(15000, 30000, 60000, 300000, 900000, 1800000, 10800000)
//        val builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.MyDialogTheme)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//        when {
//            sct < 70000 -> builder.setTitle((sct/1000).toString()+"Screen Timeout")
//            sct > 80000 -> builder.setTitle((sct/1000/60).toString()+"Screen Timeout")
//            sct > 5000000 -> builder.setTitle((sct/1000/60/60).toString()+"Screen Timeout")
//        }
        builder.setTitle("Set Screen Timeout")
        builder.setIcon(R.drawable.ic_baseline_timelapse_24_black)
        builder.setItems(fileNames) { _, item ->

            Settings.System.putInt(
                contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT, (numbers[item])

            )
        }
        showDialog(builder.create())
    }
}