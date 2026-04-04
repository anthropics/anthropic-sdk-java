// JS compile-only stubs for java.time.temporal
package java.time.temporal

interface TemporalAccessor {
    fun isSupported(field: ChronoField): Boolean
    fun getLong(field: ChronoField): Long
}

interface Temporal : TemporalAccessor

enum class ChronoField {
    NANO_OF_SECOND,
    MICRO_OF_SECOND,
    MILLI_OF_SECOND,
    SECOND_OF_MINUTE,
    MINUTE_OF_HOUR,
    HOUR_OF_DAY,
    DAY_OF_MONTH,
    MONTH_OF_YEAR,
    YEAR,
    OFFSET_SECONDS,
    EPOCH_DAY;
}

enum class ChronoUnit {
    NANOS,
    MICROS,
    MILLIS,
    SECONDS,
    MINUTES,
    HOURS,
    DAYS,
    WEEKS,
    MONTHS,
    YEARS;
}
