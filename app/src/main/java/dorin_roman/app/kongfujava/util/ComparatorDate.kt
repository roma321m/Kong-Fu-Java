package dorin_roman.app.kongfujava.util

import android.annotation.SuppressLint
import android.util.Log
import dorin_roman.app.kongfujava.screens.supervisor.progress_report.SupervisorProgressReportViewModel
import java.text.SimpleDateFormat
import java.util.Date

class ComparatorDate : Comparator<String> {
    @SuppressLint("SimpleDateFormat")
    override fun compare(o1: String?, o2: String?): Int {
        if (o1 == null || o2 == null) {
            return 0
        }
        val simpleDateFormat = SimpleDateFormat("dd/MM")
        try {
            val o1Date: Date = simpleDateFormat.parse(o1) as Date
            val o2Date: Date = simpleDateFormat.parse(o2) as Date
            val o1milli: Long = o1Date.time
            val o2milli: Long = o2Date.time
            return o1milli.compareTo(o2milli)
        } catch (e: Exception) {
            Log.e(SupervisorProgressReportViewModel.TAG, "${e.message}")
        }
        return o1.compareTo(o2)
    }
}