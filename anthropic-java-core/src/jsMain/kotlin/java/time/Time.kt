// JS compile-only stubs for java.time
package java.time

import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalAccessor

class OffsetDateTime private constructor(
    private val dateTime: LocalDateTime,
    private val offsetSeconds: Int,
) : Temporal {
    override fun isSupported(field: ChronoField): Boolean = true
    override fun getLong(field: ChronoField): Long = 0L

    fun toLocalDate(): LocalDate = dateTime.toLocalDate()

    override fun toString(): String = dateTime.toString()

    companion object {
        fun now(): OffsetDateTime = OffsetDateTime(LocalDateTime.now(), 0)
        fun now(clock: Clock): OffsetDateTime = now()
        fun parse(text: CharSequence): OffsetDateTime = now()
        fun parse(text: CharSequence, formatter: DateTimeFormatter): OffsetDateTime = now()
        fun from(temporal: TemporalAccessor): OffsetDateTime = now()
    }
}

class LocalDate private constructor() : Temporal {
    override fun isSupported(field: ChronoField): Boolean = true
    override fun getLong(field: ChronoField): Long = 0L

    fun atStartOfDay(): LocalDateTime = LocalDateTime.now()

    companion object {
        fun now(): LocalDate = LocalDate()
        fun from(temporal: TemporalAccessor): LocalDate = LocalDate()
        fun parse(text: CharSequence): LocalDate = now()
    }
}

class LocalDateTime private constructor() : Temporal {
    override fun isSupported(field: ChronoField): Boolean = true
    override fun getLong(field: ChronoField): Long = 0L

    fun atZone(zone: ZoneId): ZonedDateTime = ZonedDateTime()
    fun toLocalDate(): LocalDate = LocalDate.now()

    companion object {
        fun now(): LocalDateTime = LocalDateTime()
        fun from(temporal: TemporalAccessor): LocalDateTime = LocalDateTime()
        fun parse(text: CharSequence): LocalDateTime = now()
    }
}

class ZonedDateTime internal constructor() {
    fun toOffsetDateTime(): OffsetDateTime = OffsetDateTime.now()
}

class ZoneId private constructor(private val id: String) {
    override fun toString(): String = id

    companion object {
        fun of(zoneId: String): ZoneId = ZoneId(zoneId)
        fun systemDefault(): ZoneId = ZoneId("UTC")
    }
}

class Duration private constructor(private val millis: Long) {
    fun toMillis(): Long = millis
    fun toNanos(): Long = millis * 1_000_000

    companion object {
        fun ofMillis(millis: Long): Duration = Duration(millis)
        fun ofSeconds(seconds: Long): Duration = Duration(seconds * 1000)
        fun ofNanos(nanos: Long): Duration = Duration(nanos / 1_000_000)
        fun between(start: Temporal, end: Temporal): Duration = Duration(0)
    }
}

open class DateTimeException(message: String) : RuntimeException(message)

abstract class Clock {
    abstract fun instant(): Any
    abstract fun getZone(): ZoneId

    companion object {
        fun systemUTC(): Clock = object : Clock() {
            override fun instant(): Any = Unit
            override fun getZone(): ZoneId = ZoneId.of("UTC")
        }
    }
}
