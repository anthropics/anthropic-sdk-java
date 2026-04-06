package kotlinx.kmp.util.core.component

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Barcode/QR code type definitions for qrkit KMP integration.
 *
 * Maps OpenAPI string formats to barcode symbologies:
 *   `string-barcode`  → Code 128 (any ASCII string)
 *   `number-barcode`  → EAN-13 / UPC-A (numeric only)
 *   `int-barcode`     → Code 39 (alphanumeric, limited charset)
 *   `uri-qrcode`      → QR Code (optimized for URLs)
 *   `qrcode`          → QR Code (any string)
 *   `ean13-barcode`   → EAN-13 (13-digit product code)
 *   `isbn-barcode`    → ISBN barcode (book identifier)
 *   `upc`             → UPC-A (12-digit retail barcode)
 *   `datamatrix`      → Data Matrix (compact 2D, small items)
 *   `pdf417`          → PDF417 (driver's licenses, boarding passes)
 *
 * Usage with qrkit (Compose Multiplatform):
 * ```kotlin
 * // Generate QR code from URI
 * val spec = BarcodeSpec.qrCode("https://example.com")
 * QRKitView(data = spec.data, size = 200.dp) // qrkit composable
 *
 * // Generate barcode from product code
 * val ean = BarcodeSpec.ean13("9780134685991")
 * BarcodeView(data = ean.data, format = ean.symbology) // qrkit barcode
 * ```
 */
@Serializable
data class BarcodeSpec(
    /** Data to encode */
    val data: String,
    /** Barcode symbology / format */
    val symbology: BarcodeSymbology,
    /** Error correction level (for QR/DataMatrix) */
    val errorCorrection: ErrorCorrection = ErrorCorrection.M,
    /** Display width hint in dp */
    val width: Int = 200,
    /** Display height hint in dp (ignored for square codes like QR) */
    val height: Int = 80,
) {
    companion object {
        /** QR Code from any string */
        fun qrCode(data: String, errorCorrection: ErrorCorrection = ErrorCorrection.M) =
            BarcodeSpec(data, BarcodeSymbology.QR_CODE, errorCorrection, width = 200, height = 200)

        /** QR Code optimized for URI */
        fun uriQrCode(uri: String) = qrCode(uri, ErrorCorrection.L) // URLs are long, use lower EC

        /** Code 128 barcode (any ASCII string) */
        fun code128(data: String) = BarcodeSpec(data, BarcodeSymbology.CODE_128)

        /** Code 39 barcode (alphanumeric) */
        fun code39(data: String) = BarcodeSpec(data, BarcodeSymbology.CODE_39)

        /** EAN-13 barcode (13-digit product code) */
        fun ean13(data: String) = BarcodeSpec(data, BarcodeSymbology.EAN_13, width = 200, height = 100)

        /** UPC-A barcode (12-digit retail code) */
        fun upcA(data: String) = BarcodeSpec(data, BarcodeSymbology.UPC_A, width = 200, height = 100)

        /** ISBN barcode */
        fun isbn(data: String) = ean13(data.replace("-", ""))

        /** Data Matrix 2D barcode */
        fun dataMatrix(data: String) = BarcodeSpec(data, BarcodeSymbology.DATA_MATRIX, width = 150, height = 150)

        /** PDF417 2D barcode */
        fun pdf417(data: String) = BarcodeSpec(data, BarcodeSymbology.PDF_417, width = 300, height = 100)

        /** Auto-detect best symbology from format string */
        fun fromFormat(format: String, data: String): BarcodeSpec = when (format) {
            "string-barcode" -> code128(data)
            "number-barcode" -> if (data.length == 13) ean13(data) else code128(data)
            "int-barcode" -> code39(data)
            "uri-qrcode" -> uriQrCode(data)
            "qrcode" -> qrCode(data)
            "ean13-barcode", "ean13" -> ean13(data)
            "isbn-barcode", "isbn" -> isbn(data)
            "upc" -> upcA(data)
            "datamatrix" -> dataMatrix(data)
            "pdf417" -> pdf417(data)
            else -> qrCode(data) // fallback to QR
        }
    }
}

@Serializable
enum class BarcodeSymbology(val displayName: String) {
    @SerialName("qr_code") QR_CODE("QR Code"),
    @SerialName("code_128") CODE_128("Code 128"),
    @SerialName("code_39") CODE_39("Code 39"),
    @SerialName("ean_13") EAN_13("EAN-13"),
    @SerialName("ean_8") EAN_8("EAN-8"),
    @SerialName("upc_a") UPC_A("UPC-A"),
    @SerialName("upc_e") UPC_E("UPC-E"),
    @SerialName("data_matrix") DATA_MATRIX("Data Matrix"),
    @SerialName("pdf_417") PDF_417("PDF417"),
    @SerialName("aztec") AZTEC("Aztec"),
    @SerialName("codabar") CODABAR("Codabar"),
    @SerialName("itf") ITF("ITF (Interleaved 2 of 5)"),
}

@Serializable
enum class ErrorCorrection {
    /** ~7% recovery */
    L,
    /** ~15% recovery */
    M,
    /** ~25% recovery */
    Q,
    /** ~30% recovery */
    H,
}
