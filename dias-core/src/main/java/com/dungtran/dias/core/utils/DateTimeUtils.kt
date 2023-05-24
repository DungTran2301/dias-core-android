package com.dungtran.dias.core.utils


import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeUtils {

    // for format explanation: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
    enum class DateFormat(val value: String) {
        BasicFormatDate("dd/MM/yyyy"),
        OmFormatDate("dd/MM/yyyy - HH:mm"),
        OmDetailFormatDate("HH:mm, EEEE dd/MM/yyyy"),
        OmOrderListFormatDate("yyyy-MM-dd'T'HH:mm:ss"),
        BffCovFormatDate("yyyy-MM-dd'T'HH:mm:ss'Z'"),
        PlFormatDate("yyyy-MM-dd'T'HH:mm:ss"),
        FullDateTimeWithTimezone("yyyy-MM-dd'T'HH:mm:ssZZZZZ"),
        DisplayFormatDate("dd/MM/yyyy - hh:mm a"),
        MonthOnlyFormatDate("MMMM"),
        PvisFormatDate("yyyy-MM-dd"),
        PlFormatTimeRange("HH:mm:ss"),
        PlFormatTimeRangeWithoutSecond("HH:mm"),
        EndShiftFormatDate("HH:mm - dd/MM/yyyy")
    }

    enum class TimeFormat(val value: String) {
        PlFormatTimeRange("HH:mm:ss"),
    }

    @JvmStatic
    fun formatDate(source: Date, dateFormat: DateFormat): String {
        return SimpleDateFormat(dateFormat.value, Locale.getDefault()).format(source)
    }


    @JvmStatic
    fun changeDateFormat(
        source: String,
        inputDateFormat: DateFormat,
        outputDateFormat: DateFormat,
        isNeedUTCTimeZone: Boolean = true
    ): String {
        return convertStringToDate(source, inputDateFormat, isNeedUTCTimeZone)?.let { date ->
            formatDate(date, outputDateFormat)
        } ?: ""
    }

    /**
     * Tính số ngày còn lại giữa 2 thời điểm, làm tròn theo ngày
     *
     * vd:
     * 0:0:0 1/1/2019 -> 0:0:0 2/1/2019 là 2 ngày
     *
     * 0:0:0 1/1/2019 -> 23:59:59 1/1/2019 là 1 ngày
     *
     */
    @JvmStatic
    fun getRemainingDays(
        currentTime: Long,
        endTime: Long
    ): Int {
        val remain = endTime - currentTime
        val days = TimeUnit.MILLISECONDS.toDays(remain)

        return if (endTime < currentTime) 0 else days.toInt() + 1
    }

    @JvmStatic
    fun getRemainingHours(
        currentTime: Long,
        endTime: Long
    ): Int {
        val remain = endTime - currentTime
        val hours = TimeUnit.MILLISECONDS.toHours(remain)

        return if (endTime < currentTime) 0 else hours.toInt() + 1
    }

    @JvmStatic
    fun convertStringToDate(
        input: String,
        dateFormat: DateFormat,
        isNeedUTCTimeZone: Boolean = false
    ): Date? {
        return try {
            val simpleDateFormat = SimpleDateFormat(dateFormat.value, Locale.getDefault())
            if (isNeedUTCTimeZone) simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            simpleDateFormat.parse(input)
        } catch (e: ParseException) {
            null
        }
    }

    @JvmStatic
    fun convertTimeStringToDate(input: String, timeFormat: TimeFormat): Date? {
        return try {
            val simpleDateFormat = SimpleDateFormat(timeFormat.value, Locale.getDefault())
            val parsedTime = simpleDateFormat.parse(input)
            return if (parsedTime != null) {
                val result = Calendar.getInstance()
                val timeWithoutDate =
                    Calendar.getInstance().apply { timeInMillis = parsedTime.time }

                result.set(Calendar.HOUR_OF_DAY, timeWithoutDate.get(Calendar.HOUR_OF_DAY))
                result.set(Calendar.MINUTE, timeWithoutDate.get(Calendar.MINUTE))
                result.set(Calendar.SECOND, timeWithoutDate.get(Calendar.SECOND))

                Date(result.timeInMillis)
            } else {
                null
            }
        } catch (e: ParseException) {
            null
        }
    }

    @JvmStatic
    fun formatCalendar(
        calendar: Calendar,
        dateFormat: DateFormat,
        showInUTC: Boolean = false
    ): String {
        val sdf = SimpleDateFormat(dateFormat.value, Locale.getDefault())
        if (showInUTC) sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(calendar.time)
    }

    @JvmStatic
    fun getCurrentMonth(): String {
        val sdf = SimpleDateFormat(DateFormat.MonthOnlyFormatDate.value, Locale.getDefault())

        return sdf.format(Date())
    }

}