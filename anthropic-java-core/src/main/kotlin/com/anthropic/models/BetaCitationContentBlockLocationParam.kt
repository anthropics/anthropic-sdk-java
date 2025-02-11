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
class BetaCitationContentBlockLocationParam
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
    @JsonProperty("end_block_index")
    @ExcludeMissing
    private val endBlockIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("start_block_index")
    @ExcludeMissing
    private val startBlockIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun citedText(): String = citedText.getRequired("cited_text")

    fun documentIndex(): Long = documentIndex.getRequired("document_index")

    fun documentTitle(): Optional<String> =
        Optional.ofNullable(documentTitle.getNullable("document_title"))

    fun endBlockIndex(): Long = endBlockIndex.getRequired("end_block_index")

    fun startBlockIndex(): Long = startBlockIndex.getRequired("start_block_index")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("cited_text") @ExcludeMissing fun _citedText(): JsonField<String> = citedText

    @JsonProperty("document_index")
    @ExcludeMissing
    fun _documentIndex(): JsonField<Long> = documentIndex

    @JsonProperty("document_title")
    @ExcludeMissing
    fun _documentTitle(): JsonField<String> = documentTitle

    @JsonProperty("end_block_index")
    @ExcludeMissing
    fun _endBlockIndex(): JsonField<Long> = endBlockIndex

    @JsonProperty("start_block_index")
    @ExcludeMissing
    fun _startBlockIndex(): JsonField<Long> = startBlockIndex

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaCitationContentBlockLocationParam = apply {
        if (validated) {
            return@apply
        }

        citedText()
        documentIndex()
        documentTitle()
        endBlockIndex()
        startBlockIndex()
        _type().let {
            if (it != JsonValue.from("content_block_location")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCitationContentBlockLocationParam]. */
    class Builder internal constructor() {

        private var citedText: JsonField<String>? = null
        private var documentIndex: JsonField<Long>? = null
        private var documentTitle: JsonField<String>? = null
        private var endBlockIndex: JsonField<Long>? = null
        private var startBlockIndex: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("content_block_location")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaCitationContentBlockLocationParam: BetaCitationContentBlockLocationParam
        ) = apply {
            citedText = betaCitationContentBlockLocationParam.citedText
            documentIndex = betaCitationContentBlockLocationParam.documentIndex
            documentTitle = betaCitationContentBlockLocationParam.documentTitle
            endBlockIndex = betaCitationContentBlockLocationParam.endBlockIndex
            startBlockIndex = betaCitationContentBlockLocationParam.startBlockIndex
            type = betaCitationContentBlockLocationParam.type
            additionalProperties =
                betaCitationContentBlockLocationParam.additionalProperties.toMutableMap()
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

        fun endBlockIndex(endBlockIndex: Long) = endBlockIndex(JsonField.of(endBlockIndex))

        fun endBlockIndex(endBlockIndex: JsonField<Long>) = apply {
            this.endBlockIndex = endBlockIndex
        }

        fun startBlockIndex(startBlockIndex: Long) = startBlockIndex(JsonField.of(startBlockIndex))

        fun startBlockIndex(startBlockIndex: JsonField<Long>) = apply {
            this.startBlockIndex = startBlockIndex
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

        fun build(): BetaCitationContentBlockLocationParam =
            BetaCitationContentBlockLocationParam(
                checkRequired("citedText", citedText),
                checkRequired("documentIndex", documentIndex),
                checkRequired("documentTitle", documentTitle),
                checkRequired("endBlockIndex", endBlockIndex),
                checkRequired("startBlockIndex", startBlockIndex),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaCitationContentBlockLocationParam && citedText == other.citedText && documentIndex == other.documentIndex && documentTitle == other.documentTitle && endBlockIndex == other.endBlockIndex && startBlockIndex == other.startBlockIndex && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citedText, documentIndex, documentTitle, endBlockIndex, startBlockIndex, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCitationContentBlockLocationParam{citedText=$citedText, documentIndex=$documentIndex, documentTitle=$documentTitle, endBlockIndex=$endBlockIndex, startBlockIndex=$startBlockIndex, type=$type, additionalProperties=$additionalProperties}"
}
