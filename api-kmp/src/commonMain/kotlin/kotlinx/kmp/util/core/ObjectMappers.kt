package kotlinx.kmp.util.core

/**
 * Common JSON mapping utility.
 *
 * This file hosts the provider-agnostic contract for JSON (de)serialization.
 * The actual backend is chosen per platform:
 *
 *   JVM     -> Jackson (default), via [JsonMapperType] = `com.fasterxml.jackson.databind.json.JsonMapper`
 *   JS      -> kotlinx.serialization `Json`
 *   Native  -> kotlinx.serialization `Json` or Moshi (future)
 *
 * Any commonMain code that needs JSON should go through [jsonMapper] and the
 * open functions on [ApiJsonBackend], never through Jackson or kotlinx APIs
 * directly. Platform actuals override [ApiJsonBackend]'s open fun members to
 * supply concrete encoders/decoders.
 *
 * Usage (commonMain):
 * ```
 * val json: String = apiJsonBackend().encodeToString(myModel)
 * val model: MyModel = apiJsonBackend().decodeFromString(json)
 * ```
 *
 * On JVM, the default [ApiJsonBackend] delegates to the configured Jackson
 * [jsonMapper]. Other targets plug in their own backend.
 */
open class ApiJsonBackend {
    /**
     * Encode [value] to a JSON string.
     *
     * Default implementation throws; platforms override with a real encoder.
     */
    open fun encodeToString(value: Any?): String =
        throw NotImplementedError("ApiJsonBackend: encodeToString not implemented for this platform")

    /**
     * Decode a JSON string into a value of the given runtime type descriptor.
     *
     * The [typeDescriptor] is an opaque handle understood by the platform
     * backend (a `Class<T>` or `TypeReference<T>` on JVM, a `KSerializer<T>`
     * on kotlinx.serialization targets, a Moshi `Type` on Moshi targets).
     *
     * Default implementation throws; platforms override.
     */
    open fun <T> decodeFromString(json: String, typeDescriptor: Any): T =
        throw NotImplementedError("ApiJsonBackend: decodeFromString not implemented for this platform")

    /** Pretty-print a value. Default delegates to [encodeToString]. */
    open fun encodePretty(value: Any?): String = encodeToString(value)
}

/** Default [ApiJsonBackend] for the current platform. JVM actual returns a Jackson-backed backend. */
expect fun apiJsonBackend(): ApiJsonBackend

/**
 * Opaque platform mapper type exposed for legacy code that still passes
 * mappers around as a concrete parameter.
 *
 * On JVM this resolves to `com.fasterxml.jackson.databind.json.JsonMapper`.
 * Other platforms resolve to a kotlinx.serialization `Json` or Moshi `Moshi`.
 *
 * New code should prefer [ApiJsonBackend] over touching [JsonMapperType]
 * directly so the commonMain code path stays provider-neutral.
 */
expect class JsonMapperType

/** Legacy factory — returns the platform [JsonMapperType] configured with SDK defaults. */
expect fun jsonMapper(): JsonMapperType

/**
 * Platform hook for verifying that the runtime JSON library is at a supported
 * version. JVM checks Jackson; other targets are a no-op.
 */
expect fun checkJsonVersionCompatibility()
