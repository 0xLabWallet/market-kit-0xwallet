package com.wallet0x.marketkit.chart

import com.wallet0x.marketkit.models.HsPeriodType
import com.wallet0x.marketkit.models.HsPointTimePeriod
import com.wallet0x.marketkit.models.HsTimePeriod
import java.util.*

object HsChartRequestHelper {

    fun pointInterval(periodType: HsPeriodType) = when (periodType) {
        is HsPeriodType.ByPeriod -> {
            byPeriod(periodType.timePeriod)
        }
        is HsPeriodType.ByCustomPoints -> {
            byPeriod(periodType.timePeriod)
        }
        is HsPeriodType.ByStartTime -> {
            val currentTime = Date().time / 1000
            val seconds = currentTime - periodType.startTime

            when {
                seconds <= HsTimePeriod.Day1.range -> HsPointTimePeriod.Minute30
                seconds <= HsTimePeriod.Week1.range -> HsPointTimePeriod.Hour4
                seconds <= HsTimePeriod.Week2.range -> HsPointTimePeriod.Hour8
                seconds <= HsTimePeriod.Year2.range -> HsPointTimePeriod.Day1
                else -> HsPointTimePeriod.Week1
            }
        }
    }

    private fun byPeriod(timePeriod: HsTimePeriod) = when (timePeriod) {
        HsTimePeriod.Day1 -> HsPointTimePeriod.Minute30
        HsTimePeriod.Week1 -> HsPointTimePeriod.Hour4
        HsTimePeriod.Week2 -> HsPointTimePeriod.Hour8
        else -> HsPointTimePeriod.Day1
    }

    fun fromTimestamp(timestamp: Long, periodType: HsPeriodType) = when (periodType) {
        is HsPeriodType.ByPeriod -> {
            timestamp - periodType.timePeriod.range
        }
        is HsPeriodType.ByCustomPoints -> {
            timestamp - periodType.timePeriod.range
        }
        is HsPeriodType.ByStartTime -> {
            periodType.startTime
        }

    }
}