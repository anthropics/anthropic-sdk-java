// JS compile-only stubs for java.time.format
package java.time.format

import java.time.temporal.TemporalAccessor

class DateTimeFormatter private constructor(private val pattern: String) {
    fun parse(text: CharSequence): TemporalAccessor = object : TemporalAccessor {
        override fun isSupported(field: java.time.temporal.ChronoField): Boolean = false
        override fun getLong(field: java.time.temporal.ChronoField): Long = 0L
    }

    fun format(temporal: TemporalAccessor): String = temporal.toString()

    companion object {
        val ISO_LOCAL_DATE_TIME: DateTimeFormatter = DateTimeFormatter("ISO_LOCAL_DATE_TIME")
        val ISO_LOCAL_DATE: DateTimeFormatter = DateTimeFormatter("ISO_LOCAL_DATE")
        val ISO_ZONED_DATE_TIME: DateTimeFormatter = DateTimeFormatter("ISO_ZONED_DATE_TIME")
        val ISO_OFFSET_DATE_TIME: DateTimeFormatter = DateTimeFormatter("ISO_OFFSET_DATE_TIME")
        val ISO_INSTANT: DateTimeFormatter = DateTimeFormatter("ISO_INSTANT")
        fun ofPattern(pattern: String): DateTimeFormatter = DateTimeFormatter(pattern)
    }
}

class DateTimeParseException(message: String, parsedData: CharSequence? = null, errorIndex: Int = 0) :
    java.time.DateTimeException(message)
