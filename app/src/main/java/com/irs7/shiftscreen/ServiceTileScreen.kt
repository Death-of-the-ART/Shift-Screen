package com.irs7.shiftscreen

import android.app.AlertDialog
import android.provider.Settings
import android.service.quicksettings.TileService


class ServiceTileScreen : TileService() {

    override fun onClick() {
        super.onClick()
        screenTimeoutDialog()
    }

    private fun tileUpdateLabel() {
        val tile = this.qsTile
        val sct = Settings.System.getInt(
                contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT
        )
        when {
            sct < 70000 -> tile.label = (sct / 1000).toString() + " Seconds"
            sct > 5000000 -> tile.label = (sct / 1000 / 60 / 60).toString() + " Hours"
            sct > 80000 -> tile.label = (sct / 1000 / 60).toString() + " Minutes"
        }
        tile.updateTile()
    }

    private fun screenTimeoutDialog() {
        val timeNames = arrayOf(
                "15 Seconds",
                "30 Seconds",
                "1 Minute",
                "5 Minutes",
                "15 Minutes",
                "30 Minutes",
                "3 Hours"
        )

        val timeValues = intArrayOf(15000, 30000, 60000, 300000, 900000, 1800000, 10800000)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.Theme_ShiftScreen_Light)
        builder.setTitle("Set Screen Timeout")
        builder.setIcon(R.drawable.ic_baseline_timelapse_24_black)
        builder.setItems(timeNames) { _, item ->
            Settings.System.putInt(
                    contentResolver,
                    Settings.System.SCREEN_OFF_TIMEOUT, (timeValues[item])
            )
            tileUpdateLabel()
        }
        showDialog(builder.create())
    }
}
