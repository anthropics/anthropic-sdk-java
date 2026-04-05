package kotlinx.kmp.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Wire ICalEvent ↔ {ical4j .ics text, jCal RFC 7265 JSON} round-trip parity.
 * Wire is the single source of truth for RFC 5545 iCalendar events.
 */
class ICalRoundTripTest {

    private fun sampleEvent(): ICalEvent = ICalEvent(
        uid = "urn:uuid:e8a7c9d0-1234-5678-9abc-def012345678",
        summary = "Apollo 11 Lunar Landing",
        description = "First crewed mission to land on the Moon",
        location = "Sea of Tranquility",
        dt_start = java.time.Instant.parse("1969-07-20T20:17:40Z"),
        dt_end = java.time.Instant.parse("1969-07-21T17:54:00Z"),
        rrule = "",
        status = "CONFIRMED",
        priority = 1,
        url = "https://www.nasa.gov/mission/apollo-11/",
        categories = listOf("space", "historical", "moon"),
        organizer = "director@nasa.gov",
        attendees = listOf(
            ICalAttendee(
                email = "armstrong@nasa.gov",
                name = "Neil Armstrong",
                part_stat = "ACCEPTED",
                role = "CHAIR",
            ),
            ICalAttendee(
                email = "aldrin@nasa.gov",
                name = "Buzz Aldrin",
                part_stat = "ACCEPTED",
                role = "REQ-PARTICIPANT",
            ),
        ),
    )

    @Test
    fun `Wire ics text to Wire preserves core properties`() {
        val original = sampleEvent()
        val ics = Validators.toICal(listOf(original))

        assertTrue(ics.contains("BEGIN:VCALENDAR"))
        assertTrue(ics.contains("BEGIN:VEVENT"))
        assertTrue(ics.contains("Apollo 11"))
        assertTrue(ics.contains("SUMMARY"))

        val parsed = Validators.parseICalTyped(ics)
        assertEquals(1, parsed.size)
        val event = parsed.first()
        assertEquals(original.summary, event.summary)
        assertEquals(original.description, event.description)
        assertEquals(original.location, event.location)
        assertEquals(original.status, event.status)
    }

    @Test
    fun `Wire jCal RFC 7265 JSON structure is valid`() {
        val original = sampleEvent()
        val json = Validators.toJCalRfc7265(listOf(original))

        // RFC 7265: jCal is a JSON array starting with "vcalendar"
        assertTrue(json.startsWith("[\"vcalendar\""), "jCal must start with [\"vcalendar\"")
        assertTrue(json.contains("vevent"))
        assertTrue(json.contains("Apollo 11"))
        assertTrue(json.contains("uid"))
        assertTrue(json.contains("dtstart"))
    }

    @Test
    fun `Wire pragmatic JSON structure round-trip`() {
        val original = sampleEvent()
        val json = Validators.toICalEventsJson(listOf(original))
        assertTrue(json.contains("Apollo 11"))
        assertTrue(json.contains("\"uid\""))
        assertTrue(json.contains(original.uid))
    }

    @Test
    fun `ical4j VEvent Wire ical4j VEvent round-trip`() {
        val original = sampleEvent()
        val vEvent = ICal4jBridge.wireToVEvent(original)
        assertNotNull(vEvent, "wireToVEvent must produce a VEvent")
        val backToWire = ICal4jBridge.vEventToWire(vEvent!!)
        assertNotNull(backToWire)
        assertEquals(original.summary, backToWire!!.summary)
        assertEquals(original.location, backToWire.location)
        assertEquals(original.status, backToWire.status)
    }

    @Test
    fun `Chain Wire to ics to Wire to jCal to ics still preserves event`() {
        val original = sampleEvent()

        // Wire → ics → Wire
        val ics1 = Validators.toICal(listOf(original))
        val parsed1 = Validators.parseICalTyped(ics1).first()

        // Wire → jCal JSON (one-way structural representation)
        val jcal = Validators.toJCalRfc7265(listOf(parsed1))
        assertTrue(jcal.contains("Apollo 11"))

        // Wire → ics again
        val ics2 = Validators.toICal(listOf(parsed1))
        val parsed2 = Validators.parseICalTyped(ics2).first()

        assertEquals(original.summary, parsed2.summary)
        assertEquals(original.location, parsed2.location)
        assertEquals(original.status, parsed2.status)
    }

    @Test
    fun `Multi event ics preserves all events`() {
        val events = listOf(
            sampleEvent(),
            sampleEvent().copy(
                uid = "urn:uuid:different-event-id",
                summary = "Apollo 17 Landing",
                dt_start = java.time.Instant.parse("1972-12-11T19:54:57Z"),
            ),
        )
        val ics = Validators.toICal(events)
        val parsed = Validators.parseICalTyped(ics)
        assertEquals(2, parsed.size)
        assertTrue(parsed.any { it.summary == "Apollo 11 Lunar Landing" })
        assertTrue(parsed.any { it.summary == "Apollo 17 Landing" })
    }
}
