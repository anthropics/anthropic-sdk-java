package kotlinx.kmp.util.core.component

import kotlinx.kmp.util.core.ContentFormat
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BarcodeTypesTest {

    // === Factory methods ===

    @Test fun qrCode_from_string() {
        val spec = BarcodeSpec.qrCode("Hello World")
        assertEquals(BarcodeSymbology.QR_CODE, spec.symbology)
        assertEquals("Hello World", spec.data)
        assertEquals(200, spec.width)
        assertEquals(200, spec.height)
    }

    @Test fun uriQrCode_uses_low_error_correction() {
        val spec = BarcodeSpec.uriQrCode("https://example.com/very/long/path?query=value")
        assertEquals(BarcodeSymbology.QR_CODE, spec.symbology)
        assertEquals(ErrorCorrection.L, spec.errorCorrection)
    }

    @Test fun code128_from_string() {
        val spec = BarcodeSpec.code128("ABC-123-xyz")
        assertEquals(BarcodeSymbology.CODE_128, spec.symbology)
    }

    @Test fun code39_from_int() {
        val spec = BarcodeSpec.code39("12345")
        assertEquals(BarcodeSymbology.CODE_39, spec.symbology)
    }

    @Test fun ean13_product_code() {
        val spec = BarcodeSpec.ean13("9780134685991")
        assertEquals(BarcodeSymbology.EAN_13, spec.symbology)
        assertEquals(100, spec.height) // taller for scanning
    }

    @Test fun upcA_retail_code() {
        val spec = BarcodeSpec.upcA("012345678905")
        assertEquals(BarcodeSymbology.UPC_A, spec.symbology)
    }

    @Test fun isbn_strips_dashes() {
        val spec = BarcodeSpec.isbn("978-0-13-468599-1")
        assertEquals("9780134685991", spec.data)
        assertEquals(BarcodeSymbology.EAN_13, spec.symbology)
    }

    @Test fun dataMatrix_compact() {
        val spec = BarcodeSpec.dataMatrix("SERIAL:12345")
        assertEquals(BarcodeSymbology.DATA_MATRIX, spec.symbology)
        assertEquals(150, spec.width)
    }

    @Test fun pdf417_document() {
        val spec = BarcodeSpec.pdf417("DL:CA:D1234567:DOE:JOHN:1990-01-01")
        assertEquals(BarcodeSymbology.PDF_417, spec.symbology)
        assertEquals(300, spec.width)
    }

    // === fromFormat auto-detection ===

    @Test fun fromFormat_string_barcode() {
        assertEquals(BarcodeSymbology.CODE_128, BarcodeSpec.fromFormat("string-barcode", "test").symbology)
    }

    @Test fun fromFormat_number_barcode_13_digits() {
        assertEquals(BarcodeSymbology.EAN_13, BarcodeSpec.fromFormat("number-barcode", "9780134685991").symbology)
    }

    @Test fun fromFormat_number_barcode_short() {
        assertEquals(BarcodeSymbology.CODE_128, BarcodeSpec.fromFormat("number-barcode", "12345").symbology)
    }

    @Test fun fromFormat_int_barcode() {
        assertEquals(BarcodeSymbology.CODE_39, BarcodeSpec.fromFormat("int-barcode", "42").symbology)
    }

    @Test fun fromFormat_uri_qrcode() {
        assertEquals(BarcodeSymbology.QR_CODE, BarcodeSpec.fromFormat("uri-qrcode", "https://x.com").symbology)
    }

    @Test fun fromFormat_qrcode() {
        assertEquals(BarcodeSymbology.QR_CODE, BarcodeSpec.fromFormat("qrcode", "any data").symbology)
    }

    @Test fun fromFormat_ean13() {
        assertEquals(BarcodeSymbology.EAN_13, BarcodeSpec.fromFormat("ean13-barcode", "1234567890123").symbology)
    }

    @Test fun fromFormat_upc() {
        assertEquals(BarcodeSymbology.UPC_A, BarcodeSpec.fromFormat("upc", "012345678905").symbology)
    }

    @Test fun fromFormat_datamatrix() {
        assertEquals(BarcodeSymbology.DATA_MATRIX, BarcodeSpec.fromFormat("datamatrix", "test").symbology)
    }

    @Test fun fromFormat_pdf417() {
        assertEquals(BarcodeSymbology.PDF_417, BarcodeSpec.fromFormat("pdf417", "test").symbology)
    }

    @Test fun fromFormat_unknown_defaults_to_qr() {
        assertEquals(BarcodeSymbology.QR_CODE, BarcodeSpec.fromFormat("unknown", "test").symbology)
    }

    // === UiSchemaRegistry integration ===

    @Test fun registry_has_barcode_formats() {
        assertEquals("barcode", UiSchemaRegistry.forTypeFormat("string", "string-barcode").widget)
        assertEquals("barcode-ean", UiSchemaRegistry.forTypeFormat("string", "number-barcode").widget)
        assertEquals("qrcode", UiSchemaRegistry.forTypeFormat("string", "uri-qrcode").widget)
        assertEquals("qrcode", UiSchemaRegistry.forTypeFormat("string", "qrcode").widget)
        assertEquals("barcode-code39", UiSchemaRegistry.forTypeFormat("string", "int-barcode").widget)
    }

    @Test fun registry_barcode_category() {
        assertEquals("Barcode", UiSchemaRegistry.forTypeFormat("string", "string-barcode").category)
        assertEquals("Barcode", UiSchemaRegistry.forTypeFormat("string", "uri-qrcode").category)
    }

    // === Serialization ===

    @Test fun barcodeSpec_json_roundTrip() {
        val spec = BarcodeSpec.qrCode("test data")
        val json = Json.encodeToString(BarcodeSpec.serializer(), spec)
        assertTrue(json.contains("qr_code"))
        val decoded = Json.decodeFromString(BarcodeSpec.serializer(), json)
        assertEquals(spec, decoded)
    }

    @Test fun barcodeSpec_cbor_roundTrip() {
        val spec = BarcodeSpec.ean13("1234567890123")
        val bytes = ContentFormat.CBOR.encodeToByteArray(BarcodeSpec.serializer(), spec)
        val decoded = ContentFormat.CBOR.decodeFromByteArray(BarcodeSpec.serializer(), bytes)
        assertEquals(spec.data, decoded.data)
        assertEquals(BarcodeSymbology.EAN_13, decoded.symbology)
    }

    @Test fun symbology_serialNames() {
        val json = Json.encodeToString(BarcodeSymbology.serializer(), BarcodeSymbology.QR_CODE)
        assertEquals("\"qr_code\"", json)
        val json2 = Json.encodeToString(BarcodeSymbology.serializer(), BarcodeSymbology.CODE_128)
        assertEquals("\"code_128\"", json2)
    }

    @Test fun all_symbologies_registered() {
        assertEquals(12, BarcodeSymbology.entries.size)
    }
}
