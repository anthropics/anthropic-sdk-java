package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The request combines `thread` with a request field or an organization setting that Threads do not
 * support; `unsupported`, when present, names it. Send the full conversation without `thread`, or
 * change that field.
 */
class ThreadUnsupportedRequestErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val unsupported: JsonField<List<ThreadUnsupportedFeatureName>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("unsupported")
        @ExcludeMissing
        unsupported: JsonField<List<ThreadUnsupportedFeatureName>> = JsonMissing.of(),
    ) : this(errorCode, unsupported, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("thread_unsupported_request")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * Each entry names a request field or an organization setting that Threads do not support. On a
     * value you do not recognize, send the full conversation without `thread`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun unsupported(): Optional<List<ThreadUnsupportedFeatureName>> =
        unsupported.getOptional("unsupported")

    /**
     * Returns the raw JSON value of [unsupported].
     *
     * Unlike [unsupported], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("unsupported")
    @ExcludeMissing
    fun _unsupported(): JsonField<List<ThreadUnsupportedFeatureName>> = unsupported

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [ThreadUnsupportedRequestErrorDetails].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ThreadUnsupportedRequestErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("thread_unsupported_request")
        private var unsupported: JsonField<MutableList<ThreadUnsupportedFeatureName>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            threadUnsupportedRequestErrorDetails: ThreadUnsupportedRequestErrorDetails
        ) = apply {
            errorCode = threadUnsupportedRequestErrorDetails.errorCode
            unsupported =
                threadUnsupportedRequestErrorDetails.unsupported
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties =
                threadUnsupportedRequestErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("thread_unsupported_request")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        /**
         * Each entry names a request field or an organization setting that Threads do not support.
         * On a value you do not recognize, send the full conversation without `thread`.
         */
        fun unsupported(unsupported: List<ThreadUnsupportedFeatureName>?) =
            unsupported(JsonField.ofNullable(unsupported))

        /** Alias for calling [Builder.unsupported] with `unsupported.orElse(null)`. */
        fun unsupported(unsupported: Optional<List<ThreadUnsupportedFeatureName>>) =
            unsupported(unsupported.getOrNull())

        /**
         * Sets [Builder.unsupported] to an arbitrary JSON value.
         *
         * You should usually call [Builder.unsupported] with a well-typed
         * `List<ThreadUnsupportedFeatureName>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun unsupported(unsupported: JsonField<List<ThreadUnsupportedFeatureName>>) = apply {
            this.unsupported = unsupported.map { it.toMutableList() }
        }

        /**
         * Adds a single [ThreadUnsupportedFeatureName] to [Builder.unsupported].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUnsupported(unsupported: ThreadUnsupportedFeatureName) = apply {
            this.unsupported =
                (this.unsupported ?: JsonField.of(mutableListOf())).also {
                    checkKnown("unsupported", it).add(unsupported)
                }
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [ThreadUnsupportedRequestErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ThreadUnsupportedRequestErrorDetails =
            ThreadUnsupportedRequestErrorDetails(
                errorCode,
                (unsupported ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): ThreadUnsupportedRequestErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("thread_unsupported_request")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        unsupported().ifPresent { it.forEach { it.validate() } }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        errorCode.let { if (it == JsonValue.from("thread_unsupported_request")) 1 else 0 } +
            (unsupported.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ThreadUnsupportedRequestErrorDetails &&
            errorCode == other.errorCode &&
            unsupported == other.unsupported &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, unsupported, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ThreadUnsupportedRequestErrorDetails{errorCode=$errorCode, unsupported=$unsupported, additionalProperties=$additionalProperties}"
}
