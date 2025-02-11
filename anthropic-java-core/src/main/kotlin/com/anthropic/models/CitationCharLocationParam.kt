// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional

@NoAutoDetect
class CitationCharLocationParam
@JsonCreator
private constructor(
    @JsonProperty("cited_text")
    @ExcludeMissing
    private val citedText: JsonField<String> = JsonMissing.of(),
    @JsonProperty("document_index")
    @ExcludeMissing
    private val documentIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("document_title")
    @ExcludeMissing
    private val documentTitle: JsonField<String> = JsonMissing.of(),
    @JsonProperty("end_char_index")
    @ExcludeMissing
    private val endCharIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("start_char_index")
    @ExcludeMissing
    private val startCharIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun citedText(): String = citedText.getRequired("cited_text")

    fun documentIndex(): Long = documentIndex.getRequired("document_index")

    fun documentTitle(): Optional<String> =
        Optional.ofNullable(documentTitle.getNullable("document_title"))

    fun endCharIndex(): Long = endCharIndex.getRequired("end_char_index")

    fun startCharIndex(): Long = startCharIndex.getRequired("start_char_index")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("cited_text") @ExcludeMissing fun _citedText(): JsonField<String> = citedText

    @JsonProperty("document_index")
    @ExcludeMissing
    fun _documentIndex(): JsonField<Long> = documentIndex

    @JsonProperty("document_title")
    @ExcludeMissing
    fun _documentTitle(): JsonField<String> = documentTitle

    @JsonProperty("end_char_index")
    @ExcludeMissing
    fun _endCharIndex(): JsonField<Long> = endCharIndex

    @JsonProperty("start_char_index")
    @ExcludeMissing
    fun _startCharIndex(): JsonField<Long> = startCharIndex

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): CitationCharLocationParam = apply {
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

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CitationCharLocationParam]. */
    class Builder internal constructor() {

        private var citedText: JsonField<String>? = null
        private var documentIndex: JsonField<Long>? = null
        private var documentTitle: JsonField<String>? = null
        private var endCharIndex: JsonField<Long>? = null
        private var startCharIndex: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("char_location")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(citationCharLocationParam: CitationCharLocationParam) = apply {
            citedText = citationCharLocationParam.citedText
            documentIndex = citationCharLocationParam.documentIndex
            documentTitle = citationCharLocationParam.documentTitle
            endCharIndex = citationCharLocationParam.endCharIndex
            startCharIndex = citationCharLocationParam.startCharIndex
            type = citationCharLocationParam.type
            additionalProperties = citationCharLocationParam.additionalProperties.toMutableMap()
        }

        fun citedText(citedText: String) = citedText(JsonField.of(citedText))

        fun citedText(citedText: JsonField<String>) = apply { this.citedText = citedText }

        fun documentIndex(documentIndex: Long) = documentIndex(JsonField.of(documentIndex))

        fun documentIndex(documentIndex: JsonField<Long>) = apply {
            this.documentIndex = documentIndex
        }

        fun documentTitle(documentTitle: String?) =
            documentTitle(JsonField.ofNullable(documentTitle))

        fun documentTitle(documentTitle: Optional<String>) =
            documentTitle(documentTitle.orElse(null))

        fun documentTitle(documentTitle: JsonField<String>) = apply {
            this.documentTitle = documentTitle
        }

        fun endCharIndex(endCharIndex: Long) = endCharIndex(JsonField.of(endCharIndex))

        fun endCharIndex(endCharIndex: JsonField<Long>) = apply { this.endCharIndex = endCharIndex }

        fun startCharIndex(startCharIndex: Long) = startCharIndex(JsonField.of(startCharIndex))

        fun startCharIndex(startCharIndex: JsonField<Long>) = apply {
            this.startCharIndex = startCharIndex
        }

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

        fun build(): CitationCharLocationParam =
            CitationCharLocationParam(
                checkRequired("citedText", citedText),
                checkRequired("documentIndex", documentIndex),
                checkRequired("documentTitle", documentTitle),
                checkRequired("endCharIndex", endCharIndex),
                checkRequired("startCharIndex", startCharIndex),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is CitationCharLocationParam && citedText == other.citedText && documentIndex == other.documentIndex && documentTitle == other.documentTitle && endCharIndex == other.endCharIndex && startCharIndex == other.startCharIndex && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citedText, documentIndex, documentTitle, endCharIndex, startCharIndex, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "CitationCharLocationParam{citedText=$citedText, documentIndex=$documentIndex, documentTitle=$documentTitle, endCharIndex=$endCharIndex, startCharIndex=$startCharIndex, type=$type, additionalProperties=$additionalProperties}"
}
