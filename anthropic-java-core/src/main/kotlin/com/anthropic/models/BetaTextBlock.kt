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
class BetaTextBlock
@JsonCreator
private constructor(
    @JsonProperty("citations")
    @ExcludeMissing
    private val citations: JsonField<List<BetaTextCitation>> = JsonMissing.of(),
    @JsonProperty("text") @ExcludeMissing private val text: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * Citations supporting the text block.
     *
     * The type of citation returned will depend on the type of document being cited. Citing a PDF
     * results in `page_location`, plain text results in `char_location`, and content document
     * results in `content_block_location`.
     */
    fun citations(): Optional<List<BetaTextCitation>> =
        Optional.ofNullable(citations.getNullable("citations"))

    fun text(): String = text.getRequired("text")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Citations supporting the text block.
     *
     * The type of citation returned will depend on the type of document being cited. Citing a PDF
     * results in `page_location`, plain text results in `char_location`, and content document
     * results in `content_block_location`.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<List<BetaTextCitation>> = citations

    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun toParam(): BetaTextBlockParam = BetaTextBlockParam.builder().text(_text()).build()

    private var validated: Boolean = false

    fun validate(): BetaTextBlock = apply {
        if (validated) {
            return@apply
        }

        citations().ifPresent { it.forEach { it.validate() } }
        text()
        _type().let {
            if (it != JsonValue.from("text")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTextBlock]. */
    class Builder internal constructor() {

        private var citations: JsonField<MutableList<BetaTextCitation>>? = null
        private var text: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("text")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTextBlock: BetaTextBlock) = apply {
            citations = betaTextBlock.citations.map { it.toMutableList() }
            text = betaTextBlock.text
            type = betaTextBlock.type
            additionalProperties = betaTextBlock.additionalProperties.toMutableMap()
        }

        /**
         * Citations supporting the text block.
         *
         * The type of citation returned will depend on the type of document being cited. Citing a
         * PDF results in `page_location`, plain text results in `char_location`, and content
         * document results in `content_block_location`.
         */
        fun citations(citations: List<BetaTextCitation>?) =
            citations(JsonField.ofNullable(citations))

        /**
         * Citations supporting the text block.
         *
         * The type of citation returned will depend on the type of document being cited. Citing a
         * PDF results in `page_location`, plain text results in `char_location`, and content
         * document results in `content_block_location`.
         */
        fun citations(citations: Optional<List<BetaTextCitation>>) =
            citations(citations.orElse(null))

        /**
         * Citations supporting the text block.
         *
         * The type of citation returned will depend on the type of document being cited. Citing a
         * PDF results in `page_location`, plain text results in `char_location`, and content
         * document results in `content_block_location`.
         */
        fun citations(citations: JsonField<List<BetaTextCitation>>) = apply {
            this.citations = citations.map { it.toMutableList() }
        }

        /**
         * Citations supporting the text block.
         *
         * The type of citation returned will depend on the type of document being cited. Citing a
         * PDF results in `page_location`, plain text results in `char_location`, and content
         * document results in `content_block_location`.
         */
        fun addCitation(citation: BetaTextCitation) = apply {
            citations =
                (citations ?: JsonField.of(mutableListOf())).apply {
                    asKnown()
                        .orElseThrow {
                            IllegalStateException(
                                "Field was set to non-list type: ${javaClass.simpleName}"
                            )
                        }
                        .add(citation)
                }
        }

        /**
         * Citations supporting the text block.
         *
         * The type of citation returned will depend on the type of document being cited. Citing a
         * PDF results in `page_location`, plain text results in `char_location`, and content
         * document results in `content_block_location`.
         */
        fun addCitation(citationCharLocation: BetaCitationCharLocation) =
            addCitation(BetaTextCitation.ofCitationCharLocation(citationCharLocation))

        /**
         * Citations supporting the text block.
         *
         * The type of citation returned will depend on the type of document being cited. Citing a
         * PDF results in `page_location`, plain text results in `char_location`, and content
         * document results in `content_block_location`.
         */
        fun addCitation(citationPageLocation: BetaCitationPageLocation) =
            addCitation(BetaTextCitation.ofCitationPageLocation(citationPageLocation))

        /**
         * Citations supporting the text block.
         *
         * The type of citation returned will depend on the type of document being cited. Citing a
         * PDF results in `page_location`, plain text results in `char_location`, and content
         * document results in `content_block_location`.
         */
        fun addCitation(citationContentBlockLocation: BetaCitationContentBlockLocation) =
            addCitation(
                BetaTextCitation.ofCitationContentBlockLocation(citationContentBlockLocation)
            )

        fun text(text: String) = text(JsonField.of(text))

        fun text(text: JsonField<String>) = apply { this.text = text }

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

        fun build(): BetaTextBlock =
            BetaTextBlock(
                checkRequired("citations", citations).map { it.toImmutable() },
                checkRequired("text", text),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaTextBlock && citations == other.citations && text == other.text && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citations, text, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTextBlock{citations=$citations, text=$text, type=$type, additionalProperties=$additionalProperties}"
}
