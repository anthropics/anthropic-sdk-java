package kotlinx.kmp.util.core.component

import kotlinx.kmp.util.core.ContentFormat
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Tests comprehensive UI Schema type mapping + all wire format negotiation.
 */
class UiSchemaRegistryTest {

    // === Standard OpenAPI string formats ===

    @Test fun email_maps_to_email_input() { assertInput("email", "email", "Contact") }
    @Test fun phone_maps_to_tel_input() { assertInput("phone", "tel", "Contact") }
    @Test fun uri_maps_to_url_input() { assertInput("uri", "url", "Link") }
    @Test fun date_maps_to_date_input() { assertInput("date", "date", "DateTime") }
    @Test fun datetime_maps_to_datetime_local() { assertInput("date-time", "datetime-local", "DateTime") }
    @Test fun time_maps_to_time_input() { assertInput("time", "time", "DateTime") }
    @Test fun password_maps_to_password_input() { assertInput("password", "password", "Security") }
    @Test fun uuid_maps_to_text_identity() { assertInput("uuid", "text", "Identity") }

    // === Numeric formats ===

    @Test fun int32_has_step_1() { assertEquals(1.0, lookup("int32").step) }
    @Test fun double_has_small_step() { assertEquals(0.000001, lookup("double").step) }

    // === Financial / Identity ===

    @Test fun credit_card_financial() { assertInput("credit-card", "text", "Financial") }
    @Test fun isbn_identity() { assertInput("isbn", "text", "Identity") }

    // === Currency / Country / Language / Locale ===

    @Test fun currency_is_select() { assertEquals("select", lookup("currency").widget) }
    @Test fun currency_has_options() { assertTrue(lookup("currency").options!!.contains("USD")) }
    @Test fun country_is_select() { assertEquals("select", lookup("country").widget) }
    @Test fun language_is_select() { assertEquals("select", lookup("language").widget) }
    @Test fun locale_has_pattern() { assertNotNull(lookup("locale").pattern) }
    @Test fun timezone_locale_category() { assertEquals("Locale", lookup("timezone").category) }

    // === Measure / Unit types ===

    @Test fun measure_length_has_unit_m() { assertEquals("m", lookup("measure-length").unit) }
    @Test fun measure_mass_has_unit_kg() { assertEquals("kg", lookup("measure-mass").unit) }
    @Test fun measure_temperature_celsius() { assertEquals("°C", lookup("measure-temperature").unit) }
    @Test fun measure_currency_is_financial() { assertEquals("Financial", lookup("measure-currency").category) }
    @Test fun measure_uses_measure_widget() { assertEquals("measure", lookup("measure-length").widget) }

    // === vCard property types (RFC 6350) ===

    @Test fun vcard_fn_text() { assertInput("vcard-fn", "text", "vCard") }
    @Test fun vcard_tel_is_tel() { assertInput("vcard-tel", "tel", "vCard") }
    @Test fun vcard_email_is_email() { assertInput("vcard-email", "email", "vCard") }
    @Test fun vcard_adr_address_editor() { assertEquals("address-editor", lookup("vcard-adr").widget) }
    @Test fun vcard_photo_is_file() { assertInput("vcard-photo", "file", "vCard") }
    @Test fun vcard_bday_is_date() { assertInput("vcard-bday", "date", "vCard") }
    @Test fun vcard_note_is_textarea() { assertEquals("textarea", lookup("vcard-note").widget) }
    @Test fun vcard_geo_picker() { assertEquals("geo-picker", lookup("vcard-geo").widget) }

    // === iCalendar property types (RFC 5545) ===

    @Test fun ical_dtstart_datetime() { assertInput("ical-dtstart", "datetime-local", "iCalendar") }
    @Test fun ical_status_select() { assertTrue(lookup("ical-status").options!!.contains("CONFIRMED")) }
    @Test fun ical_partstat_select() { assertTrue(lookup("ical-partstat").options!!.contains("ACCEPTED")) }
    @Test fun ical_role_select() { assertTrue(lookup("ical-role").options!!.contains("CHAIR")) }
    @Test fun ical_action_select() { assertTrue(lookup("ical-action").options!!.contains("DISPLAY")) }
    @Test fun ical_rrule_editor() { assertEquals("rrule-editor", lookup("ical-rrule").widget) }
    @Test fun ical_priority_slider() { assertEquals("slider", lookup("ical-priority").widget) }

    // === Geo types ===

    @Test fun geo_picker() { assertEquals("geo-picker", lookup("geo").widget) }
    @Test fun geo_lat_range() { assertEquals(-90.0, lookup("geo-lat").min) }
    @Test fun geojson_editor() { assertEquals("geojson-editor", lookup("geojson").widget) }

    // === Custom / Extension ===

    @Test fun color_input() { assertInput("color", "color", "Custom") }
    @Test fun markdown_editor() { assertEquals("markdown-editor", lookup("markdown").widget) }
    @Test fun cron_editor() { assertEquals("cron-editor", lookup("cron").widget) }
    @Test fun json_editor() { assertEquals("json-editor", lookup("json").widget) }

    // === Type defaults (no format) ===

    @Test fun string_default_text() { assertEquals("text", UiSchemaRegistry.forTypeFormat("string").inputType) }
    @Test fun integer_default_number() { assertEquals("number", UiSchemaRegistry.forTypeFormat("integer").inputType) }
    @Test fun boolean_default_checkbox() { assertEquals("checkbox", UiSchemaRegistry.forTypeFormat("boolean").inputType) }
    @Test fun array_default_array_editor() { assertEquals("array-editor", UiSchemaRegistry.forTypeFormat("array").widget) }

    // === Registry completeness ===

    @Test fun registry_has_all_categories() {
        val cats = UiSchemaRegistry.allCategories
        assertTrue(cats.containsAll(listOf("Contact", "DateTime", "Locale", "Measure", "vCard", "iCalendar", "Financial", "Geography")))
    }

    @Test fun registry_has_many_formats() {
        assertTrue(UiSchemaRegistry.allFormats.size >= 60)
    }

    // === Wire format content negotiation for UiFieldMapping ===

    @Test fun uiFieldMapping_json_roundTrip() {
        val m = lookup("email")
        val json = Json.encodeToString(UiFieldMapping.serializer(), m)
        val decoded = Json.decodeFromString(UiFieldMapping.serializer(), json)
        assertEquals(m.inputType, decoded.inputType)
        assertEquals(m.category, decoded.category)
    }

    @Test fun uiFieldMapping_cbor_roundTrip() {
        val m = UiFieldMapping("number", widget = "slider", category = "Test", min = 0.0, max = 100.0)
        val bytes = ContentFormat.CBOR.encodeToByteArray(UiFieldMapping.serializer(), m)
        val decoded = ContentFormat.CBOR.decodeFromByteArray(UiFieldMapping.serializer(), bytes)
        assertEquals("slider", decoded.widget)
        assertEquals(0.0, decoded.min)
    }

    @Test fun uiFieldMapping_protobuf_roundTrip() {
        val m = UiFieldMapping("text", category = "Test")
        val bytes = ContentFormat.PROTOBUF.encodeToByteArray(UiFieldMapping.serializer(), m)
        val decoded = ContentFormat.PROTOBUF.decodeFromByteArray(UiFieldMapping.serializer(), bytes)
        assertEquals("text", decoded.inputType)
    }

    @Test fun uiFieldMapping_msgpack_roundTrip() {
        val m = UiFieldMapping("tel", category = "Contact", placeholder = "+1")
        val bytes = ContentFormat.MSGPACK.encodeToByteArray(UiFieldMapping.serializer(), m)
        val decoded = ContentFormat.MSGPACK.decodeFromByteArray(UiFieldMapping.serializer(), bytes)
        assertEquals("tel", decoded.inputType)
        assertEquals("+1", decoded.placeholder)
    }

    // === Helpers ===

    private fun lookup(format: String) = UiSchemaRegistry.forTypeFormat("string", format)

    private fun assertInput(format: String, expectedInputType: String, expectedCategory: String) {
        val m = lookup(format)
        assertEquals(expectedInputType, m.inputType, "inputType for $format")
        assertEquals(expectedCategory, m.category, "category for $format")
    }
}
