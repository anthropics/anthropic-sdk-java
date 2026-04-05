package kotlinx.kmp.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Verifies Wire VCardContact ↔ {ezvcard, Smack, Bedework, jCard JSON}
 * round-trip parity. Wire is the single source of truth; every bridge
 * must preserve the core RFC 6350 property set.
 */
class VCardRoundTripTest {

    private fun sampleContact(): VCardContact = VCardContact(
        name = PersonName(
            given = "Ada",
            family = "Lovelace",
            prefix = "Dr.",
            suffix = "FRS",
            middle = "Augusta",
        ),
        nicknames = listOf("Ada"),
        emails = listOf("ada@analytical-engine.org", "ada.lovelace@royal.society.uk"),
        phones = listOf(
            com.google.type.PhoneNumber(e164_number = "+442079460000"),
        ),
        addresses = listOf(
            com.google.type.PostalAddress(
                region_code = "GB",
                language_code = "en",
                postal_code = "SW1A 1AA",
                administrative_area = "Greater London",
                locality = "London",
                address_lines = emptyList(),
            ),
        ),
        organization = "Analytical Engine Ltd",
        title = "First Programmer",
        role = "Mathematician",
        urls = listOf("https://en.wikipedia.org/wiki/Ada_Lovelace"),
        timezone = "Europe/London",
        languages = listOf("en", "fr"),
        note = "Pioneer of computer programming",
        categories = listOf("mathematician", "writer", "historical"),
        uid = "urn:uuid:d3a8d4a2-3e8c-4a5a-9a2a-1f0b0e0d0c0b",
        jabber_id = "ada@xmpp.example.org",
    )

    @Test
    fun `Wire ezvcard Wire preserves all core properties`() {
        val original = sampleContact()
        val ez = Validators.wireToEzvcard(original)
        val roundTripped = Validators.ezvcardToWire(ez)

        assertEquals(original.name?.given, roundTripped.name?.given)
        assertEquals(original.name?.family, roundTripped.name?.family)
        assertEquals(original.emails.toSet(), roundTripped.emails.toSet())
        assertEquals(
            original.phones.first().e164_number,
            roundTripped.phones.firstOrNull()?.e164_number,
        )
        assertEquals(original.organization, roundTripped.organization)
        assertEquals(original.title, roundTripped.title)
        assertEquals(original.role, roundTripped.role)
        assertEquals(original.urls.toSet(), roundTripped.urls.toSet())
        assertEquals(original.timezone, roundTripped.timezone)
        assertEquals(original.categories.toSet(), roundTripped.categories.toSet())
        assertEquals(original.note, roundTripped.note)
        assertEquals(original.uid, roundTripped.uid)
    }

    @Test
    fun `Wire vcf Wire preserves all core properties`() {
        val original = sampleContact()
        val vcf = Validators.toVCard(original)
        assertTrue(vcf.contains("BEGIN:VCARD"))
        assertTrue(vcf.contains("END:VCARD"))
        assertTrue(vcf.contains("Lovelace"))

        val parsed = Validators.parseVCard(vcf)
        assertEquals(original.name?.family, parsed.name?.family)
        assertEquals(original.name?.given, parsed.name?.given)
        assertEquals(original.emails.toSet(), parsed.emails.toSet())
        assertEquals(original.organization, parsed.organization)
    }

    @Test
    fun `Wire jCard Wire preserves all core properties`() {
        val original = sampleContact()
        val json = Validators.toJCard(original)
        assertTrue(json.startsWith("[\"vcard\""), "jCard must start with [\"vcard\"")
        assertTrue(json.contains("Lovelace"))

        val parsed = Validators.parseJCard(json)
        assertEquals(original.name?.family, parsed.name?.family)
        assertEquals(original.name?.given, parsed.name?.given)
        assertEquals(original.emails.toSet(), parsed.emails.toSet())
        assertEquals(original.organization, parsed.organization)
        assertEquals(original.title, parsed.title)
        assertEquals(original.uid, parsed.uid)
    }

    @Test
    fun `Wire Bedework Wire preserves core properties`() {
        val original = sampleContact()
        val bwCard = VCardBridges.wireToBedework(original)
        assertNotNull(bwCard)
        val roundTripped = VCardBridges.bedeworkToWire(bwCard)

        assertEquals(original.name?.family, roundTripped.name?.family)
        assertEquals(original.name?.given, roundTripped.name?.given)
        // Bedework doesn't preserve all email/phone types in round-trip,
        // but the values should round-trip.
        assertTrue(roundTripped.emails.any { it in original.emails })
        assertEquals(original.organization, roundTripped.organization)
        assertEquals(original.title, roundTripped.title)
        assertEquals(original.role, roundTripped.role)
    }

    @Test
    fun `Wire Smack Wire preserves XEP-0054 subset`() {
        val original = sampleContact()
        val smackCard = VCardBridges.ezvcardToSmack(Validators.wireToEzvcard(original))
        assertNotNull(smackCard)
        val roundTripped = Validators.ezvcardToWire(VCardBridges.smackToEzvcard(smackCard))

        // Smack XEP-0054 supports FN, N, EMAIL (home/work), TEL, ORG, TITLE, ROLE, ADR
        assertEquals(original.name?.given, roundTripped.name?.given)
        assertEquals(original.name?.family, roundTripped.name?.family)
        // Emails may be compressed to home/work only
        assertTrue(roundTripped.emails.any { it in original.emails })
        assertEquals(original.organization, roundTripped.organization)
    }

    @Test
    fun `Four way chain Wire jCard ezvcard Bedework Wire`() {
        val original = sampleContact()

        // Step 1: Wire → jCard JSON
        val jcard = Validators.toJCard(original)
        val afterJCard = Validators.parseJCard(jcard)

        // Step 2: Wire → Bedework → Wire
        val bedework = VCardBridges.wireToBedework(afterJCard)
        val afterBedework = VCardBridges.bedeworkToWire(bedework)

        // Step 3: Wire ezvcard Wire
        val ezvcard = Validators.wireToEzvcard(afterBedework)
        val final = Validators.ezvcardToWire(ezvcard)

        // Core identity preserved through all four hops
        assertEquals(original.name?.family, final.name?.family)
        assertEquals(original.name?.given, final.name?.given)
        assertEquals(original.uid, final.uid)
        assertTrue(final.emails.any { it in original.emails })
    }

    @Test
    fun `JSON round trip is idempotent`() {
        val original = sampleContact()
        val json1 = Validators.toJCard(original)
        val parsed1 = Validators.parseJCard(json1)
        val json2 = Validators.toJCard(parsed1)
        val parsed2 = Validators.parseJCard(json2)
        val json3 = Validators.toJCard(parsed2)

        // Second cycle must be identical to first (idempotent)
        assertEquals(json2, json3, "jCard serialization must be idempotent")
    }
}
