package xyz.gmfatoom.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime


object DateTimeUtil {

    fun nowTime(): LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun toEpochMillis(dateTime: LocalDateTime): Long =
        dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

    fun formatFullDate(dateTime: LocalDateTime): String {
        val year = dateTime.year
        val month = dateTime.month.name.take(3).replaceFirstChar { it.uppercase() }
        val dayOfWeek = dateTime.dayOfWeek
        val day = if (dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val hour = if (dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if (dateTime.hour < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append("-")
            append(day)
            append("-")
            append(year)
            append(" ")
            append(hour)
            append(":")
            append(minute)
        }

    }





    fun getDataList(day: LocalDate):List<LocalDate> {
        val list = mutableListOf<LocalDate>()
        val buffer: Int = 60
        val firstDay = day.minus(buffer/2, DateTimeUnit.DAY)
        repeat(buffer) { i ->
            list.add(firstDay.plus(i, DateTimeUnit.DAY))
        }
        return list
    }
}

fun LocalDate.getLocalDayOfWeak():String{
   return when(this.dayOfWeek) {
        DayOfWeek.MONDAY ->  "Пн"
        DayOfWeek.TUESDAY -> "Вт"
        DayOfWeek.WEDNESDAY -> "Ср"
        DayOfWeek.THURSDAY -> "Чт"
        DayOfWeek.FRIDAY -> "Пт"
        DayOfWeek.SATURDAY -> "Сб"
        DayOfWeek.SUNDAY -> "Вс"
        else -> ""
    }
}


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.checkInputDatePatternBefore(outputPattern: String): String {
    //"dd/mm/yyyy"
    val regex1 = "^(3[01]|[12][0-9]|0[1-9]|[1-9])/(1[0-2]|0[1-9]|[1-9])/[0-9]{4}$".toRegex()

    //"mm/dd/yyyy hh:MM"
    val regex2 =
        "^(1[0-2]|0[1-9]|[1-9])/(3[01]|[12][0-9]|0[1-9]|[1-9])/[0-9]{4} [0-9][0-9]:([0]?[0-5][0-9]|[0-9])$".toRegex()
    //"MM/dd/yyyy hh:mm"
    val regex7 =
        "^((1[0-2]|0[1-9]|[1-9])/3[01]|[12][0-9]|0[1-9]|[1-9])/[0-9]{4}[\\s][0-9][0-9]:([0]?[0-5][0-9]|[0-9])$".toRegex()
    //"dd MMM yyyy"
    val regex3 = "(3[01]|[12][0-9]|0[1-9]|[1-9])[\\s][a-zA-Z]{3}[\\s][0-9]{4}$".toRegex()
    //"yyyy-MM-dd"
    val regex4 =
        "([0-9]{4})[\\-](1[0-2]|0[1-9]|[1-9])[\\-](3[01]|[12][0-9]|0[1-9]|[1-9])$".toRegex()
    //"yyyy-MM-dd'T'HH:mm"
    val regex5 =
        "([0-9]{4})[\\-](1[0-2]|0[1-9]|[1-9])[\\-](3[01]|[12][0-9]|0[1-9])[T][0-9][0-9]:([0]? [0-5][0-9]|[0-9])$".toRegex()

    //"dd-MMM-yyyy"
    val regex6 = "(3[01]|[12][0-9]|0[1-9]|[1-9])[\\-][a-zA-Z]{3}[\\-][0-9]{4}$".toRegex()
    //28-02-2022 12:12
    //"dd-mm-yyyy hh:mm"
    val regex8 =
        "(3[01]|[12][0-9]|0[1-9]|[1-9])[\\-](1[0-2]|0[1-9]|[1-9])[\\-][0-9]{4}[\\s][0-9][0-9]:([0]?[0-5][0-9]|[0-9])$".toRegex()

    //"mm/dd/yyyy hh:mm"
    val regex9 =
        "(1[0-2]|0[1-9]|[1-9])[/](3[01]|[12][0-9]|0[1-9]|[1-9])[/][0-9]{4}[\\s][0-9][0-9]:([0]?[0-5][0-9]|[0-9])$"
            .toRegex()

    //"mm/dd/yyyy"
    val regex10 = "(1[0-2]|0[1-9]|[1-9])[/](3[01]|[12][0-9]|0[1-9]|[1-9])[/][0-9]{4}$".toRegex()

    val bool1: Boolean = regex1.matches(this)
    val bool2: Boolean = regex2.matches(this)
    val bool3: Boolean = regex3.matches(this)
    val bool4: Boolean = regex4.matches(this)
    val bool5: Boolean = regex5.matches(this)
    val bool6: Boolean = regex6.matches(this)
    val bool7: Boolean = regex7.matches(this)
    val bool8: Boolean = regex8.matches(this)
    val bool9: Boolean = regex9.matches(this)
    val bool10: Boolean = regex10.matches(this)


    when {
        bool1 -> {
            return "Pattern dd/mm/yyyy"
        }

        bool2 -> {
            return "Pattern mm/dd/yyyy hh:MM"
        }

        bool3 -> {
            return "Pattern dd MMM yyyy"
        }

        bool4 -> {
            return "Pattern yyyy-MM-dd"
        }

        bool5 -> {
            return "Pattern yyyy-MM-dd'T'HH:mm"
        }

        bool6 -> {
            return "Pattern dd-MMM-yyyy"
        }

        bool7 -> {
            return "Pattern MM/dd/yyyy hh:mm"
        }

        bool8 -> {
            return "Pattern dd-mm-yyyy hh:mm"
        }

        bool9 -> {
            return "Pattern mm/dd/yyyy hh:mm"
        }

        bool10 -> {
            return "Pattern mm/dd/yyyy"
        }


        else -> {

            return "Date pattern not recognized!"
        }
    }
}


