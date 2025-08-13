// File generated from our OpenAPI spec by Stainless.

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
import kotlin.jvm.optionals.getOrNull

class RawContentBlockDeltaEvent
private constructor(
    private val delta: JsonField<RawContentBlockDelta>,
    private val index: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("delta")
        @ExcludeMissing
        delta: JsonField<RawContentBlockDelta> = JsonMissing.of(),
        @JsonProperty("index") @ExcludeMissing index: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(delta, index, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun delta(): RawContentBlockDelta = delta.getRequired("delta")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun index(): Long = index.getRequired("index")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("content_block_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [delta].
     *
     * Unlike [delta], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delta") @ExcludeMissing fun _delta(): JsonField<RawContentBlockDelta> = delta

    /**
     * Returns the raw JSON value of [index].
     *
     * Unlike [index], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Long> = index

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
         * Returns a mutable builder for constructing an instance of [RawContentBlockDeltaEvent].
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .index()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RawContentBlockDeltaEvent]. */
    class Builder internal constructor() {

        private var delta: JsonField<RawContentBlockDelta>? = null
        private var index: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("content_block_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(rawContentBlockDeltaEvent: RawContentBlockDeltaEvent) = apply {
            delta = rawContentBlockDeltaEvent.delta
            index = rawContentBlockDeltaEvent.index
            type = rawContentBlockDeltaEvent.type
            additionalProperties = rawContentBlockDeltaEvent.additionalProperties.toMutableMap()
        }

        fun delta(delta: RawContentBlockDelta) = delta(JsonField.of(delta))

        /**
         * Sets [Builder.delta] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delta] with a well-typed [RawContentBlockDelta] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun delta(delta: JsonField<RawContentBlockDelta>) = apply { this.delta = delta }

        /** Alias for calling [delta] with `RawContentBlockDelta.ofText(text)`. */
        fun delta(text: TextDelta) = delta(RawContentBlockDelta.ofText(text))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * TextDelta.builder()
         *     .text(text)
         *     .build()
         * ```
         */
        fun textDelta(text: String) = delta(TextDelta.builder().text(text).build())

        /** Alias for calling [delta] with `RawContentBlockDelta.ofInputJson(inputJson)`. */
        fun delta(inputJson: InputJsonDelta) = delta(RawContentBlockDelta.ofInputJson(inputJson))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * InputJsonDelta.builder()
         *     .partialJson(partialJson)
         *     .build()
         * ```
         */
        fun inputJsonDelta(partialJson: String) =
            delta(InputJsonDelta.builder().partialJson(partialJson).build())

        /** Alias for calling [delta] with `RawContentBlockDelta.ofCitations(citations)`. */
        fun delta(citations: CitationsDelta) = delta(RawContentBlockDelta.ofCitations(citations))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * CitationsDelta.builder()
         *     .citation(citation)
         *     .build()
         * ```
         */
        fun citationsDelta(citation: CitationsDelta.Citation) =
            delta(CitationsDelta.builder().citation(citation).build())

        /**
         * Alias for calling [citationsDelta] with
         * `CitationsDelta.Citation.ofCharLocation(charLocation)`.
         */
        fun citationsDelta(charLocation: CitationCharLocation) =
            citationsDelta(CitationsDelta.Citation.ofCharLocation(charLocation))

        /**
         * Alias for calling [citationsDelta] with
         * `CitationsDelta.Citation.ofPageLocation(pageLocation)`.
         */
        fun citationsDelta(pageLocation: CitationPageLocation) =
            citationsDelta(CitationsDelta.Citation.ofPageLocation(pageLocation))

        /**
         * Alias for calling [citationsDelta] with
         * `CitationsDelta.Citation.ofContentBlockLocation(contentBlockLocation)`.
         */
        fun citationsDelta(contentBlockLocation: CitationContentBlockLocation) =
            citationsDelta(CitationsDelta.Citation.ofContentBlockLocation(contentBlockLocation))

        /**
         * Alias for calling [citationsDelta] with
         * `CitationsDelta.Citation.ofWebSearchResultLocation(webSearchResultLocation)`.
         */
        fun citationsDelta(webSearchResultLocation: CitationsWebSearchResultLocation) =
            citationsDelta(
                CitationsDelta.Citation.ofWebSearchResultLocation(webSearchResultLocation)
            )

        /**
         * Alias for calling [citationsDelta] with
         * `CitationsDelta.Citation.ofSearchResultLocation(searchResultLocation)`.
         */
        fun citationsDelta(searchResultLocation: CitationsSearchResultLocation) =
            citationsDelta(CitationsDelta.Citation.ofSearchResultLocation(searchResultLocation))

        /** Alias for calling [delta] with `RawContentBlockDelta.ofThinking(thinking)`. */
        fun delta(thinking: ThinkingDelta) = delta(RawContentBlockDelta.ofThinking(thinking))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * ThinkingDelta.builder()
         *     .thinking(thinking)
         *     .build()
         * ```
         */
        fun thinkingDelta(thinking: String) =
            delta(ThinkingDelta.builder().thinking(thinking).build())

        /** Alias for calling [delta] with `RawContentBlockDelta.ofSignature(signature)`. */
        fun delta(signature: SignatureDelta) = delta(RawContentBlockDelta.ofSignature(signature))

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * SignatureDelta.builder()
         *     .signature(signature)
         *     .build()
         * ```
         */
        fun signatureDelta(signature: String) =
            delta(SignatureDelta.builder().signature(signature).build())

        fun index(index: Long) = index(JsonField.of(index))

        /**
         * Sets [Builder.index] to an arbitrary JSON value.
         *
         * You should usually call [Builder.index] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun index(index: JsonField<Long>) = apply { this.index = index }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("content_block_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [RawContentBlockDeltaEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .index()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): RawContentBlockDeltaEvent =
            RawContentBlockDeltaEvent(
                checkRequired("delta", delta),
                checkRequired("index", index),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): RawContentBlockDeltaEvent = apply {
        if (validated) {
            return@apply
        }

        delta().validate()
        index()
        _type().let {
            if (it != JsonValue.from("content_block_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (delta.asKnown().getOrNull()?.validity() ?: 0) +
            (if (index.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("content_block_delta")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is RawContentBlockDeltaEvent &&
            delta == other.delta &&
            index == other.index &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(delta, index, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RawContentBlockDeltaEvent{delta=$delta, index=$index, type=$type, additionalProperties=$additionalProperties}"
}
