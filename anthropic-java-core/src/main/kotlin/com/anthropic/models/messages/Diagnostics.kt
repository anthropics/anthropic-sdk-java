package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
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
 * Request-level diagnostics: why the prompt cache could not fully reuse the prefix of the request
 * named by `diagnostics.previous_message_id`.
 */
class Diagnostics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheMissReason: JsonField<CacheMissReason>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_miss_reason")
        @ExcludeMissing
        cacheMissReason: JsonField<CacheMissReason> = JsonMissing.of()
    ) : this(cacheMissReason, mutableMapOf())

    /**
     * Explains why the prompt cache could not fully reuse the prefix from the request identified by
     * `diagnostics.previous_message_id`. `null` means diagnosis is still pending — the response was
     * serialized before the background comparison completed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheMissReason(): Optional<CacheMissReason> =
        cacheMissReason.getOptional("cache_miss_reason")

    /**
     * Returns the raw JSON value of [cacheMissReason].
     *
     * Unlike [cacheMissReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_miss_reason")
    @ExcludeMissing
    fun _cacheMissReason(): JsonField<CacheMissReason> = cacheMissReason

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
         * Returns a mutable builder for constructing an instance of [Diagnostics].
         *
         * The following fields are required:
         * ```java
         * .cacheMissReason()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [Diagnostics] with the required [cacheMissReason] set to
         * the given value.
         */
        @JvmStatic
        fun of(cacheMissReason: CacheMissReason?) =
            builder().cacheMissReason(cacheMissReason).build()

        /** Alias for calling [of] with `cacheMissReason.orElse(null)`. */
        @JvmStatic
        fun of(cacheMissReason: Optional<CacheMissReason>) = of(cacheMissReason.getOrNull())

        /** Alias for calling [of] with `CacheMissReason.ofModelChanged(modelChanged)`. */
        @JvmStatic
        fun of(modelChanged: CacheMissModelChanged) =
            of(CacheMissReason.ofModelChanged(modelChanged))

        /** Alias for calling [of] with `CacheMissReason.ofSystemChanged(systemChanged)`. */
        @JvmStatic
        fun of(systemChanged: CacheMissSystemChanged) =
            of(CacheMissReason.ofSystemChanged(systemChanged))

        /** Alias for calling [of] with `CacheMissReason.ofToolsChanged(toolsChanged)`. */
        @JvmStatic
        fun of(toolsChanged: CacheMissToolsChanged) =
            of(CacheMissReason.ofToolsChanged(toolsChanged))

        /** Alias for calling [of] with `CacheMissReason.ofMessagesChanged(messagesChanged)`. */
        @JvmStatic
        fun of(messagesChanged: CacheMissMessagesChanged) =
            of(CacheMissReason.ofMessagesChanged(messagesChanged))

        /**
         * Alias for calling [of] with
         * `CacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound)`.
         */
        @JvmStatic
        fun of(previousMessageNotFound: CacheMissPreviousMessageNotFound) =
            of(CacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound))

        /** Alias for calling [of] with `CacheMissReason.ofUnavailable(unavailable)`. */
        @JvmStatic
        fun of(unavailable: CacheMissUnavailable) = of(CacheMissReason.ofUnavailable(unavailable))
    }

    /** A builder for [Diagnostics]. */
    class Builder internal constructor() {

        private var cacheMissReason: JsonField<CacheMissReason>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(diagnostics: Diagnostics) = apply {
            cacheMissReason = diagnostics.cacheMissReason
            additionalProperties = diagnostics.additionalProperties.toMutableMap()
        }

        /**
         * Explains why the prompt cache could not fully reuse the prefix from the request
         * identified by `diagnostics.previous_message_id`. `null` means diagnosis is still pending
         * — the response was serialized before the background comparison completed.
         */
        fun cacheMissReason(cacheMissReason: CacheMissReason?) =
            cacheMissReason(JsonField.ofNullable(cacheMissReason))

        /** Alias for calling [Builder.cacheMissReason] with `cacheMissReason.orElse(null)`. */
        fun cacheMissReason(cacheMissReason: Optional<CacheMissReason>) =
            cacheMissReason(cacheMissReason.getOrNull())

        /**
         * Sets [Builder.cacheMissReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheMissReason] with a well-typed [CacheMissReason]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheMissReason(cacheMissReason: JsonField<CacheMissReason>) = apply {
            this.cacheMissReason = cacheMissReason
        }

        /**
         * Alias for calling [cacheMissReason] with `CacheMissReason.ofModelChanged(modelChanged)`.
         */
        fun cacheMissReason(modelChanged: CacheMissModelChanged) =
            cacheMissReason(CacheMissReason.ofModelChanged(modelChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * CacheMissModelChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun modelChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                CacheMissModelChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `CacheMissReason.ofSystemChanged(systemChanged)`.
         */
        fun cacheMissReason(systemChanged: CacheMissSystemChanged) =
            cacheMissReason(CacheMissReason.ofSystemChanged(systemChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * CacheMissSystemChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun systemChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                CacheMissSystemChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with `CacheMissReason.ofToolsChanged(toolsChanged)`.
         */
        fun cacheMissReason(toolsChanged: CacheMissToolsChanged) =
            cacheMissReason(CacheMissReason.ofToolsChanged(toolsChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * CacheMissToolsChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun toolsChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                CacheMissToolsChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `CacheMissReason.ofMessagesChanged(messagesChanged)`.
         */
        fun cacheMissReason(messagesChanged: CacheMissMessagesChanged) =
            cacheMissReason(CacheMissReason.ofMessagesChanged(messagesChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * CacheMissMessagesChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun messagesChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                CacheMissMessagesChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `CacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound)`.
         */
        fun cacheMissReason(previousMessageNotFound: CacheMissPreviousMessageNotFound) =
            cacheMissReason(CacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound))

        /**
         * Alias for calling [cacheMissReason] with `CacheMissReason.ofUnavailable(unavailable)`.
         */
        fun cacheMissReason(unavailable: CacheMissUnavailable) =
            cacheMissReason(CacheMissReason.ofUnavailable(unavailable))

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
         * Returns an immutable instance of [Diagnostics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheMissReason()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): Diagnostics =
            Diagnostics(
                checkRequired("cacheMissReason", cacheMissReason),
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
    fun validate(): Diagnostics = apply {
        if (validated) {
            return@apply
        }

        cacheMissReason().ifPresent { it.validate() }
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
    internal fun validity(): Int = (cacheMissReason.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is Diagnostics &&
            cacheMissReason == other.cacheMissReason &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(cacheMissReason, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Diagnostics{cacheMissReason=$cacheMissReason, additionalProperties=$additionalProperties}"
}
