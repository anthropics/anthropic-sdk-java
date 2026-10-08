package com.anthropic.models.beta.messages

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
class BetaDiagnostics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheMissReason: JsonField<BetaCacheMissReason>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_miss_reason")
        @ExcludeMissing
        cacheMissReason: JsonField<BetaCacheMissReason> = JsonMissing.of()
    ) : this(cacheMissReason, mutableMapOf())

    /**
     * Explains why the prompt cache could not fully reuse the prefix from the request identified by
     * `diagnostics.previous_message_id`. `null` means diagnosis is still pending — the response was
     * serialized before the background comparison completed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheMissReason(): Optional<BetaCacheMissReason> =
        cacheMissReason.getOptional("cache_miss_reason")

    /**
     * Returns the raw JSON value of [cacheMissReason].
     *
     * Unlike [cacheMissReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_miss_reason")
    @ExcludeMissing
    fun _cacheMissReason(): JsonField<BetaCacheMissReason> = cacheMissReason

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
         * Returns a mutable builder for constructing an instance of [BetaDiagnostics].
         *
         * The following fields are required:
         * ```java
         * .cacheMissReason()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaDiagnostics] with the required [cacheMissReason]
         * set to the given value.
         */
        @JvmStatic
        fun of(cacheMissReason: BetaCacheMissReason?) =
            builder().cacheMissReason(cacheMissReason).build()

        /** Alias for calling [of] with `cacheMissReason.orElse(null)`. */
        @JvmStatic
        fun of(cacheMissReason: Optional<BetaCacheMissReason>) = of(cacheMissReason.getOrNull())

        /** Alias for calling [of] with `BetaCacheMissReason.ofModelChanged(modelChanged)`. */
        @JvmStatic
        fun of(modelChanged: BetaCacheMissModelChanged) =
            of(BetaCacheMissReason.ofModelChanged(modelChanged))

        /** Alias for calling [of] with `BetaCacheMissReason.ofSystemChanged(systemChanged)`. */
        @JvmStatic
        fun of(systemChanged: BetaCacheMissSystemChanged) =
            of(BetaCacheMissReason.ofSystemChanged(systemChanged))

        /** Alias for calling [of] with `BetaCacheMissReason.ofToolsChanged(toolsChanged)`. */
        @JvmStatic
        fun of(toolsChanged: BetaCacheMissToolsChanged) =
            of(BetaCacheMissReason.ofToolsChanged(toolsChanged))

        /** Alias for calling [of] with `BetaCacheMissReason.ofMessagesChanged(messagesChanged)`. */
        @JvmStatic
        fun of(messagesChanged: BetaCacheMissMessagesChanged) =
            of(BetaCacheMissReason.ofMessagesChanged(messagesChanged))

        /**
         * Alias for calling [of] with
         * `BetaCacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound)`.
         */
        @JvmStatic
        fun of(previousMessageNotFound: BetaCacheMissPreviousMessageNotFound) =
            of(BetaCacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound))

        /** Alias for calling [of] with `BetaCacheMissReason.ofUnavailable(unavailable)`. */
        @JvmStatic
        fun of(unavailable: BetaCacheMissUnavailable) =
            of(BetaCacheMissReason.ofUnavailable(unavailable))
    }

    /** A builder for [BetaDiagnostics]. */
    class Builder internal constructor() {

        private var cacheMissReason: JsonField<BetaCacheMissReason>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDiagnostics: BetaDiagnostics) = apply {
            cacheMissReason = betaDiagnostics.cacheMissReason
            additionalProperties = betaDiagnostics.additionalProperties.toMutableMap()
        }

        /**
         * Explains why the prompt cache could not fully reuse the prefix from the request
         * identified by `diagnostics.previous_message_id`. `null` means diagnosis is still pending
         * — the response was serialized before the background comparison completed.
         */
        fun cacheMissReason(cacheMissReason: BetaCacheMissReason?) =
            cacheMissReason(JsonField.ofNullable(cacheMissReason))

        /** Alias for calling [Builder.cacheMissReason] with `cacheMissReason.orElse(null)`. */
        fun cacheMissReason(cacheMissReason: Optional<BetaCacheMissReason>) =
            cacheMissReason(cacheMissReason.getOrNull())

        /**
         * Sets [Builder.cacheMissReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheMissReason] with a well-typed [BetaCacheMissReason]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheMissReason(cacheMissReason: JsonField<BetaCacheMissReason>) = apply {
            this.cacheMissReason = cacheMissReason
        }

        /**
         * Alias for calling [cacheMissReason] with
         * `BetaCacheMissReason.ofModelChanged(modelChanged)`.
         */
        fun cacheMissReason(modelChanged: BetaCacheMissModelChanged) =
            cacheMissReason(BetaCacheMissReason.ofModelChanged(modelChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissModelChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun modelChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissModelChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `BetaCacheMissReason.ofSystemChanged(systemChanged)`.
         */
        fun cacheMissReason(systemChanged: BetaCacheMissSystemChanged) =
            cacheMissReason(BetaCacheMissReason.ofSystemChanged(systemChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissSystemChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun systemChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissSystemChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `BetaCacheMissReason.ofToolsChanged(toolsChanged)`.
         */
        fun cacheMissReason(toolsChanged: BetaCacheMissToolsChanged) =
            cacheMissReason(BetaCacheMissReason.ofToolsChanged(toolsChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissToolsChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun toolsChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissToolsChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `BetaCacheMissReason.ofMessagesChanged(messagesChanged)`.
         */
        fun cacheMissReason(messagesChanged: BetaCacheMissMessagesChanged) =
            cacheMissReason(BetaCacheMissReason.ofMessagesChanged(messagesChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissMessagesChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun messagesChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissMessagesChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `BetaCacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound)`.
         */
        fun cacheMissReason(previousMessageNotFound: BetaCacheMissPreviousMessageNotFound) =
            cacheMissReason(BetaCacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound))

        /**
         * Alias for calling [cacheMissReason] with
         * `BetaCacheMissReason.ofUnavailable(unavailable)`.
         */
        fun cacheMissReason(unavailable: BetaCacheMissUnavailable) =
            cacheMissReason(BetaCacheMissReason.ofUnavailable(unavailable))

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
         * Returns an immutable instance of [BetaDiagnostics].
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
        fun build(): BetaDiagnostics =
            BetaDiagnostics(
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
    fun validate(): BetaDiagnostics = apply {
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

        return other is BetaDiagnostics &&
            cacheMissReason == other.cacheMissReason &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(cacheMissReason, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDiagnostics{cacheMissReason=$cacheMissReason, additionalProperties=$additionalProperties}"
}
