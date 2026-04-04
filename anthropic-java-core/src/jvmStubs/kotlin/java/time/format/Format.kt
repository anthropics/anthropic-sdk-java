package java.time.format

import java.time.temporal.TemporalAccessor
import java.time.temporal.ChronoField

class DateTimeFormatter private constructor(private val name: String) {
    fun parse(text: CharSequence): TemporalAccessor = object : TemporalAccessor {
        override fun isSupported(field: ChronoField): Boolean = false
        override fun getLong(field: ChronoField): Long = 0L
    }
    fun format(temporal: TemporalAccessor): String = temporal.toString()
    companion object {
        val ISO_LOCAL_DATE_TIME: DateTimeFormatter = DateTimeFormatter("ISO_LOCAL_DATE_TIME")
        val ISO_LOCAL_DATE: DateTimeFormatter = DateTimeFormatter("ISO_LOCAL_DATE")
        val ISO_ZONED_DATE_TIME: DateTimeFormatter = DateTimeFormatter("ISO_ZONED_DATE_TIME")
        val ISO_OFFSET_DATE_TIME: DateTimeFormatter = DateTimeFormatter("ISO_OFFSET_DATE_TIME")
        val ISO_INSTANT: DateTimeFormatter = DateTimeFormatter("ISO_INSTANT")
        val RFC_1123_DATE_TIME: DateTimeFormatter = DateTimeFormatter("RFC_1123_DATE_TIME")
        fun ofPattern(pattern: String): DateTimeFormatter = DateTimeFormatter(pattern)
    }
}

class DateTimeParseException(message: String, parsedData: CharSequence? = null, errorIndex: Int = 0) : java.time.DateTimeException(message)
