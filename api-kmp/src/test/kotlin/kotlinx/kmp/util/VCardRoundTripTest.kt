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
    fun `dedup removes duplicate emails phones categories`() {
        val withDupes = VCardContact(
            name = PersonName(given = "Ada", family = "Lovelace"),
            emails = listOf("ada@x.org", "ada@x.org", "ada2@x.org"),
            phones = listOf(
                com.google.type.PhoneNumber(e164_number = "+14155551212"),
                com.google.type.PhoneNumber(e164_number = "+14155551212"),  // dupe
                com.google.type.PhoneNumber(e164_number = "+14155551213"),
            ),
            categories = listOf("user", "admin", "user"),  // dupe
            languages = listOf("en", "en", "fr"),
            urls = listOf("https://x.org", "https://x.org"),
        )
        val deduped = withDupes.dedup()
        assertEquals(2, deduped.emails.size)
        assertEquals(2, deduped.phones.size)
        assertEquals(2, deduped.categories.size)
        assertEquals(2, deduped.languages.size)
        assertEquals(1, deduped.urls.size)
    }

    @Test
    fun `set views give O(1) contains lookups`() {
        val contact = sampleContact()

        // Set views
        assertTrue("ada@analytical-engine.org" in contact.emailSet)
        assertTrue("+442079460000" in contact.phoneSet)
        assertTrue("mathematician" in contact.categorySet)
        assertTrue("en" in contact.languageSet)

        // Convenience contains helpers
        assertTrue(contact.hasEmail("ada@analytical-engine.org"))
        assertTrue(contact.hasPhone("+442079460000"))
        assertTrue(contact.hasCategory("mathematician"))
        assertTrue(contact.hasLanguage("en"))

        // Negative lookups
        assertTrue(!contact.hasEmail("nobody@example.com"))
        assertTrue(!contact.hasPhone("+100000000"))
    }

    @Test
    fun `plus operator merges two contacts without duplicates`() {
        val a = VCardContact(
            name = PersonName(given = "Ada"),
            emails = listOf("a@x.org"),
            categories = listOf("mathematician"),
        )
        val b = VCardContact(
            name = PersonName(family = "Lovelace"),
            emails = listOf("a@x.org", "b@x.org"),
            categories = listOf("writer"),
        )
        val merged = a + b
        assertEquals(2, merged.emails.size, "emails deduplicated on merge")
        assertEquals(2, merged.categories.size)
        assertTrue("a@x.org" in merged.emailSet)
        assertTrue("b@x.org" in merged.emailSet)
        assertTrue("mathematician" in merged.categorySet)
        assertTrue("writer" in merged.categorySet)
    }

    @Test
    fun `ingress paths produce deduplicated VCardContact`() {
        // Build a vCard with duplicate EMAIL entries via ezvcard
        val ez = ezvcard.VCard()
        ez.setFormattedName("Test User")
        ez.addEmail("same@x.org")
        ez.addEmail("same@x.org")  // duplicate
        ez.addEmail("other@x.org")

        val wire = Validators.ezvcardToWire(ez)
        // ezvcardToWire calls .dedup() → duplicates removed
        assertEquals(2, wire.emails.size)
        assertTrue("same@x.org" in wire.emailSet)
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
