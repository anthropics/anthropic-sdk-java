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
class TextBlockParam
@JsonCreator
private constructor(
    @JsonProperty("text") @ExcludeMissing private val text: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
    @JsonProperty("citations")
    @ExcludeMissing
    private val citations: JsonField<List<TextCitationParam>> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun text(): String = text.getRequired("text")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    fun cacheControl(): Optional<CacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    fun citations(): Optional<List<TextCitationParam>> =
        Optional.ofNullable(citations.getNullable("citations"))

    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<List<TextCitationParam>> = citations

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): TextBlockParam = apply {
        if (validated) {
            return@apply
        }

        text()
        _type().let {
            if (it != JsonValue.from("text")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        citations().ifPresent { it.forEach { it.validate() } }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TextBlockParam]. */
    class Builder internal constructor() {

        private var text: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("text")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<MutableList<TextCitationParam>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(textBlockParam: TextBlockParam) = apply {
            text = textBlockParam.text
            type = textBlockParam.type
            cacheControl = textBlockParam.cacheControl
            citations = textBlockParam.citations.map { it.toMutableList() }
            additionalProperties = textBlockParam.additionalProperties.toMutableMap()
        }

        fun text(text: String) = text(JsonField.of(text))

        fun text(text: JsonField<String>) = apply { this.text = text }

        fun type(type: JsonValue) = apply { this.type = type }

        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.orElse(null))

        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun citations(citations: List<TextCitationParam>?) =
            citations(JsonField.ofNullable(citations))

        fun citations(citations: Optional<List<TextCitationParam>>) =
            citations(citations.orElse(null))

        fun citations(citations: JsonField<List<TextCitationParam>>) = apply {
            this.citations = citations.map { it.toMutableList() }
        }

        fun addCitation(citation: TextCitationParam) = apply {
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

        fun addCitation(citationCharLocation: CitationCharLocationParam) =
            addCitation(TextCitationParam.ofCitationCharLocation(citationCharLocation))

        fun addCitation(citationPageLocation: CitationPageLocationParam) =
            addCitation(TextCitationParam.ofCitationPageLocation(citationPageLocation))

        fun addCitation(citationContentBlockLocation: CitationContentBlockLocationParam) =
            addCitation(
                TextCitationParam.ofCitationContentBlockLocation(citationContentBlockLocation)
            )

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

        fun build(): TextBlockParam =
            TextBlockParam(
                checkRequired("text", text),
                type,
                cacheControl,
                (citations ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is TextBlockParam && text == other.text && type == other.type && cacheControl == other.cacheControl && citations == other.citations && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(text, type, cacheControl, citations, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "TextBlockParam{text=$text, type=$type, cacheControl=$cacheControl, citations=$citations, additionalProperties=$additionalProperties}"
}
