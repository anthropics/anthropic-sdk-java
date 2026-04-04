package java.time

import java.time.temporal.ChronoField
import java.time.temporal.Temporal
import java.time.temporal.TemporalAccessor

open class DateTimeException(message: String) : RuntimeException(message)

class OffsetDateTime private constructor(private val epochSeconds: Long = 0) : Temporal, Comparable<OffsetDateTime> {
    override fun isSupported(field: ChronoField): Boolean = true
    override fun getLong(field: ChronoField): Long = epochSeconds
    override fun compareTo(other: OffsetDateTime): Int = epochSeconds.compareTo(other.epochSeconds)
    override fun toString(): String = "1970-01-01T00:00:00Z"
    fun toEpochSecond(): Long = epochSeconds
    companion object {
        fun now(): OffsetDateTime = OffsetDateTime(0)
        fun now(clock: Clock): OffsetDateTime = now()
        fun parse(text: CharSequence): OffsetDateTime = OffsetDateTime()
        fun parse(text: CharSequence, formatter: java.time.format.DateTimeFormatter): OffsetDateTime = OffsetDateTime()
        fun from(temporal: TemporalAccessor): OffsetDateTime = OffsetDateTime()
    }
}
class LocalDate private constructor() : Temporal {
    override fun isSupported(field: ChronoField): Boolean = true
    override fun getLong(field: ChronoField): Long = 0L
    fun atStartOfDay(): LocalDateTime = LocalDateTime.now()
    companion object { fun parse(text: CharSequence): LocalDate = LocalDate(); fun from(temporal: TemporalAccessor): LocalDate = LocalDate() }
}
class LocalDateTime private constructor() : Temporal {
    override fun isSupported(field: ChronoField): Boolean = true
    override fun getLong(field: ChronoField): Long = 0L
    fun atZone(zone: ZoneId): ZonedDateTime = ZonedDateTime()
    fun toLocalDate(): LocalDate = LocalDate.from(this)
    companion object { fun now(): LocalDateTime = LocalDateTime(); fun parse(text: CharSequence): LocalDateTime = LocalDateTime(); fun from(temporal: TemporalAccessor): LocalDateTime = LocalDateTime() }
}
class ZonedDateTime internal constructor() { fun toOffsetDateTime(): OffsetDateTime = OffsetDateTime.now() }
class ZoneId private constructor(private val id: String) { override fun toString(): String = id; companion object { fun of(zoneId: String): ZoneId = ZoneId(zoneId); fun systemDefault(): ZoneId = ZoneId("UTC") } }
abstract class Clock { abstract fun millis(): Long; companion object { fun systemUTC(): Clock = object : Clock() { override fun millis() = 0L } } }
class Duration private constructor(private val nanos: Long = 0) {
    fun toNanos(): Long = nanos; fun toMillis(): Long = nanos / 1_000_000
    companion object { val ZERO: Duration = Duration(0); fun ofMinutes(minutes: Long): Duration = Duration(minutes * 60_000_000_000); fun ofSeconds(seconds: Long): Duration = Duration(seconds * 1_000_000_000); fun ofMillis(millis: Long): Duration = Duration(millis * 1_000_000); fun ofNanos(nanos: Long): Duration = Duration(nanos) }
}
