package com.rahmanarifofficial.mypik_pusatinformasikampus.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeNow {
    fun TIMENOW(): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(cal.time)
    }

    fun DATENOW(): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return sdf.format(cal.time)
    }
}
