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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class CitationPageLocationParam
private constructor(
    private val citedText: JsonField<String>,
    private val documentIndex: JsonField<Long>,
    private val documentTitle: JsonField<String>,
    private val endPageNumber: JsonField<Long>,
    private val startPageNumber: JsonField<Long>,
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
        @JsonProperty("end_page_number")
        @ExcludeMissing
        endPageNumber: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("start_page_number")
        @ExcludeMissing
        startPageNumber: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        citedText,
        documentIndex,
        documentTitle,
        endPageNumber,
        startPageNumber,
        type,
        mutableMapOf(),
    )

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
    fun documentTitle(): Optional<String> =
        Optional.ofNullable(documentTitle.getNullable("document_title"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endPageNumber(): Long = endPageNumber.getRequired("end_page_number")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startPageNumber(): Long = startPageNumber.getRequired("start_page_number")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("page_location")
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
     * Returns the raw JSON value of [endPageNumber].
     *
     * Unlike [endPageNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("end_page_number")
    @ExcludeMissing
    fun _endPageNumber(): JsonField<Long> = endPageNumber

    /**
     * Returns the raw JSON value of [startPageNumber].
     *
     * Unlike [startPageNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_page_number")
    @ExcludeMissing
    fun _startPageNumber(): JsonField<Long> = startPageNumber

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
         * Returns a mutable builder for constructing an instance of [CitationPageLocationParam].
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .documentIndex()
         * .documentTitle()
         * .endPageNumber()
         * .startPageNumber()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CitationPageLocationParam]. */
    class Builder internal constructor() {

        private var citedText: JsonField<String>? = null
        private var documentIndex: JsonField<Long>? = null
        private var documentTitle: JsonField<String>? = null
        private var endPageNumber: JsonField<Long>? = null
        private var startPageNumber: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("page_location")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(citationPageLocationParam: CitationPageLocationParam) = apply {
            citedText = citationPageLocationParam.citedText
            documentIndex = citationPageLocationParam.documentIndex
            documentTitle = citationPageLocationParam.documentTitle
            endPageNumber = citationPageLocationParam.endPageNumber
            startPageNumber = citationPageLocationParam.startPageNumber
            type = citationPageLocationParam.type
            additionalProperties = citationPageLocationParam.additionalProperties.toMutableMap()
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

        fun endPageNumber(endPageNumber: Long) = endPageNumber(JsonField.of(endPageNumber))

        /**
         * Sets [Builder.endPageNumber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endPageNumber] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun endPageNumber(endPageNumber: JsonField<Long>) = apply {
            this.endPageNumber = endPageNumber
        }

        fun startPageNumber(startPageNumber: Long) = startPageNumber(JsonField.of(startPageNumber))

        /**
         * Sets [Builder.startPageNumber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startPageNumber] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun startPageNumber(startPageNumber: JsonField<Long>) = apply {
            this.startPageNumber = startPageNumber
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("page_location")
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
         * Returns an immutable instance of [CitationPageLocationParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .documentIndex()
         * .documentTitle()
         * .endPageNumber()
         * .startPageNumber()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CitationPageLocationParam =
            CitationPageLocationParam(
                checkRequired("citedText", citedText),
                checkRequired("documentIndex", documentIndex),
                checkRequired("documentTitle", documentTitle),
                checkRequired("endPageNumber", endPageNumber),
                checkRequired("startPageNumber", startPageNumber),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): CitationPageLocationParam = apply {
        if (validated) {
            return@apply
        }

        citedText()
        documentIndex()
        documentTitle()
        endPageNumber()
        startPageNumber()
        _type().let {
            if (it != JsonValue.from("page_location")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is CitationPageLocationParam && citedText == other.citedText && documentIndex == other.documentIndex && documentTitle == other.documentTitle && endPageNumber == other.endPageNumber && startPageNumber == other.startPageNumber && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citedText, documentIndex, documentTitle, endPageNumber, startPageNumber, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CitationPageLocationParam{citedText=$citedText, documentIndex=$documentIndex, documentTitle=$documentTitle, endPageNumber=$endPageNumber, startPageNumber=$startPageNumber, type=$type, additionalProperties=$additionalProperties}"
}
