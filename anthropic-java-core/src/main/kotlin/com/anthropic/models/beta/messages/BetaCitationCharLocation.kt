// File generated from our OpenAPI spec by Stainless.

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

class BetaCitationCharLocation
private constructor(
    private val citedText: JsonField<String>,
    private val documentIndex: JsonField<Long>,
    private val documentTitle: JsonField<String>,
    private val endCharIndex: JsonField<Long>,
    private val startCharIndex: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cited_text") @ExcludeMissing citedText: JsonField<String> = JsonMissing.of(),
        @JsonProperty("document_index")
        @ExcludeMissing
        documentIndex: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("document_title")
        @ExcludeMissing
        documentTitle: JsonField<String> = JsonMissing.of(),
        @JsonProperty("end_char_index")
        @ExcludeMissing
        endCharIndex: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("start_char_index")
        @ExcludeMissing
        startCharIndex: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        citedText,
        documentIndex,
        documentTitle,
        endCharIndex,
        startCharIndex,
        type,
        mutableMapOf(),
    )

    fun toParam(): BetaCitationCharLocationParam =
        BetaCitationCharLocationParam.builder()
            .citedText(_citedText())
            .documentIndex(_documentIndex())
            .documentTitle(_documentTitle())
            .endCharIndex(_endCharIndex())
            .startCharIndex(_startCharIndex())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citedText(): String = citedText.getRequired("cited_text")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun documentIndex(): Long = documentIndex.getRequired("document_index")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun documentTitle(): Optional<String> = documentTitle.getOptional("document_title")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endCharIndex(): Long = endCharIndex.getRequired("end_char_index")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startCharIndex(): Long = startCharIndex.getRequired("start_char_index")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("char_location")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [citedText].
     *
     * Unlike [citedText], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cited_text") @ExcludeMissing fun _citedText(): JsonField<String> = citedText

    /**
     * Returns the raw JSON value of [documentIndex].
     *
     * Unlike [documentIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("document_index")
    @ExcludeMissing
    fun _documentIndex(): JsonField<Long> = documentIndex

    /**
     * Returns the raw JSON value of [documentTitle].
     *
     * Unlike [documentTitle], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("document_title")
    @ExcludeMissing
    fun _documentTitle(): JsonField<String> = documentTitle

    /**
     * Returns the raw JSON value of [endCharIndex].
     *
     * Unlike [endCharIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("end_char_index")
    @ExcludeMissing
    fun _endCharIndex(): JsonField<Long> = endCharIndex

    /**
     * Returns the raw JSON value of [startCharIndex].
     *
     * Unlike [startCharIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_char_index")
    @ExcludeMissing
    fun _startCharIndex(): JsonField<Long> = startCharIndex

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
         * Returns a mutable builder for constructing an instance of [BetaCitationCharLocation].
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .documentIndex()
         * .documentTitle()
         * .endCharIndex()
         * .startCharIndex()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCitationCharLocation]. */
    class Builder internal constructor() {

        private var citedText: JsonField<String>? = null
        private var documentIndex: JsonField<Long>? = null
        private var documentTitle: JsonField<String>? = null
        private var endCharIndex: JsonField<Long>? = null
        private var startCharIndex: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("char_location")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCitationCharLocation: BetaCitationCharLocation) = apply {
            citedText = betaCitationCharLocation.citedText
            documentIndex = betaCitationCharLocation.documentIndex
            documentTitle = betaCitationCharLocation.documentTitle
            endCharIndex = betaCitationCharLocation.endCharIndex
            startCharIndex = betaCitationCharLocation.startCharIndex
            type = betaCitationCharLocation.type
            additionalProperties = betaCitationCharLocation.additionalProperties.toMutableMap()
        }

        fun citedText(citedText: String) = citedText(JsonField.of(citedText))

        /**
         * Sets [Builder.citedText] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citedText] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun citedText(citedText: JsonField<String>) = apply { this.citedText = citedText }

        fun documentIndex(documentIndex: Long) = documentIndex(JsonField.of(documentIndex))

        /**
         * Sets [Builder.documentIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.documentIndex] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun documentIndex(documentIndex: JsonField<Long>) = apply {
            this.documentIndex = documentIndex
        }

        fun documentTitle(documentTitle: String?) =
            documentTitle(JsonField.ofNullable(documentTitle))

        /** Alias for calling [Builder.documentTitle] with `documentTitle.orElse(null)`. */
        fun documentTitle(documentTitle: Optional<String>) =
            documentTitle(documentTitle.getOrNull())

        /**
         * Sets [Builder.documentTitle] to an arbitrary JSON value.
         *
         * You should usually call [Builder.documentTitle] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun documentTitle(documentTitle: JsonField<String>) = apply {
            this.documentTitle = documentTitle
        }

        fun endCharIndex(endCharIndex: Long) = endCharIndex(JsonField.of(endCharIndex))

        /**
         * Sets [Builder.endCharIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endCharIndex] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun endCharIndex(endCharIndex: JsonField<Long>) = apply { this.endCharIndex = endCharIndex }

        fun startCharIndex(startCharIndex: Long) = startCharIndex(JsonField.of(startCharIndex))

        /**
         * Sets [Builder.startCharIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startCharIndex] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun startCharIndex(startCharIndex: JsonField<Long>) = apply {
            this.startCharIndex = startCharIndex
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("char_location")
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
         * Returns an immutable instance of [BetaCitationCharLocation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .documentIndex()
         * .documentTitle()
         * .endCharIndex()
         * .startCharIndex()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCitationCharLocation =
            BetaCitationCharLocation(
                checkRequired("citedText", citedText),
                checkRequired("documentIndex", documentIndex),
                checkRequired("documentTitle", documentTitle),
                checkRequired("endCharIndex", endCharIndex),
                checkRequired("startCharIndex", startCharIndex),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCitationCharLocation = apply {
        if (validated) {
            return@apply
        }

        citedText()
        documentIndex()
        documentTitle()
        endCharIndex()
        startCharIndex()
        _type().let {
            if (it != JsonValue.from("char_location")) {
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
        (if (citedText.asKnown().isPresent) 1 else 0) +
            (if (documentIndex.asKnown().isPresent) 1 else 0) +
            (if (documentTitle.asKnown().isPresent) 1 else 0) +
            (if (endCharIndex.asKnown().isPresent) 1 else 0) +
            (if (startCharIndex.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("char_location")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaCitationCharLocation && citedText == other.citedText && documentIndex == other.documentIndex && documentTitle == other.documentTitle && endCharIndex == other.endCharIndex && startCharIndex == other.startCharIndex && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citedText, documentIndex, documentTitle, endCharIndex, startCharIndex, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCitationCharLocation{citedText=$citedText, documentIndex=$documentIndex, documentTitle=$documentTitle, endCharIndex=$endCharIndex, startCharIndex=$startCharIndex, type=$type, additionalProperties=$additionalProperties}"
}
