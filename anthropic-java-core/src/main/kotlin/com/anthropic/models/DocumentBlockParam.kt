// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@NoAutoDetect
class DocumentBlockParam
@JsonCreator
private constructor(
    @JsonProperty("source")
    @ExcludeMissing
    private val source: JsonField<Source> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
    @JsonProperty("citations")
    @ExcludeMissing
    private val citations: JsonField<CitationsConfigParam> = JsonMissing.of(),
    @JsonProperty("context")
    @ExcludeMissing
    private val context: JsonField<String> = JsonMissing.of(),
    @JsonProperty("title") @ExcludeMissing private val title: JsonField<String> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun source(): Source = source.getRequired("source")

    fun type(): Type = type.getRequired("type")

    fun cacheControl(): Optional<CacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    fun citations(): Optional<CitationsConfigParam> =
        Optional.ofNullable(citations.getNullable("citations"))

    fun context(): Optional<String> = Optional.ofNullable(context.getNullable("context"))

    fun title(): Optional<String> = Optional.ofNullable(title.getNullable("title"))

    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<CitationsConfigParam> = citations

    @JsonProperty("context") @ExcludeMissing fun _context(): JsonField<String> = context

    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): DocumentBlockParam = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        type()
        cacheControl().ifPresent { it.validate() }
        citations().ifPresent { it.validate() }
        context()
        title()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var source: JsonField<Source>? = null
        private var type: JsonField<Type>? = null
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<CitationsConfigParam> = JsonMissing.of()
        private var context: JsonField<String> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(documentBlockParam: DocumentBlockParam) = apply {
            source = documentBlockParam.source
            type = documentBlockParam.type
            cacheControl = documentBlockParam.cacheControl
            citations = documentBlockParam.citations
            context = documentBlockParam.context
            title = documentBlockParam.title
            additionalProperties = documentBlockParam.additionalProperties.toMutableMap()
        }

        fun source(source: Source) = source(JsonField.of(source))

        fun source(source: JsonField<Source>) = apply { this.source = source }

        fun source(base64PdfSource: Base64PdfSource) =
            source(Source.ofBase64PdfSource(base64PdfSource))

        fun source(plainTextSource: PlainTextSource) =
            source(Source.ofPlainTextSource(plainTextSource))

        fun source(contentBlockSource: ContentBlockSource) =
            source(Source.ofContentBlockSource(contentBlockSource))

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.orElse(null))

        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun citations(citations: CitationsConfigParam) = citations(JsonField.of(citations))

        fun citations(citations: JsonField<CitationsConfigParam>) = apply {
            this.citations = citations
        }

        fun context(context: String?) = context(JsonField.ofNullable(context))

        fun context(context: Optional<String>) = context(context.orElse(null))

        fun context(context: JsonField<String>) = apply { this.context = context }

        fun title(title: String?) = title(JsonField.ofNullable(title))

        fun title(title: Optional<String>) = title(title.orElse(null))

        fun title(title: JsonField<String>) = apply { this.title = title }

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

        fun build(): DocumentBlockParam =
            DocumentBlockParam(
                checkRequired("source", source),
                checkRequired("type", type),
                cacheControl,
                citations,
                context,
                title,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64PdfSource: Base64PdfSource? = null,
        private val plainTextSource: PlainTextSource? = null,
        private val contentBlockSource: ContentBlockSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun base64PdfSource(): Optional<Base64PdfSource> = Optional.ofNullable(base64PdfSource)

        fun plainTextSource(): Optional<PlainTextSource> = Optional.ofNullable(plainTextSource)

        fun contentBlockSource(): Optional<ContentBlockSource> =
            Optional.ofNullable(contentBlockSource)

        fun isBase64PdfSource(): Boolean = base64PdfSource != null

        fun isPlainTextSource(): Boolean = plainTextSource != null

        fun isContentBlockSource(): Boolean = contentBlockSource != null

        fun asBase64PdfSource(): Base64PdfSource = base64PdfSource.getOrThrow("base64PdfSource")

        fun asPlainTextSource(): PlainTextSource = plainTextSource.getOrThrow("plainTextSource")

        fun asContentBlockSource(): ContentBlockSource =
            contentBlockSource.getOrThrow("contentBlockSource")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                base64PdfSource != null -> visitor.visitBase64PdfSource(base64PdfSource)
                plainTextSource != null -> visitor.visitPlainTextSource(plainTextSource)
                contentBlockSource != null -> visitor.visitContentBlockSource(contentBlockSource)
                else -> visitor.unknown(_json)
            }
        }

        private var validated: Boolean = false

        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBase64PdfSource(base64PdfSource: Base64PdfSource) {
                        base64PdfSource.validate()
                    }

                    override fun visitPlainTextSource(plainTextSource: PlainTextSource) {
                        plainTextSource.validate()
                    }

                    override fun visitContentBlockSource(contentBlockSource: ContentBlockSource) {
                        contentBlockSource.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Source && base64PdfSource == other.base64PdfSource && plainTextSource == other.plainTextSource && contentBlockSource == other.contentBlockSource /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(base64PdfSource, plainTextSource, contentBlockSource) /* spotless:on */

        override fun toString(): String =
            when {
                base64PdfSource != null -> "Source{base64PdfSource=$base64PdfSource}"
                plainTextSource != null -> "Source{plainTextSource=$plainTextSource}"
                contentBlockSource != null -> "Source{contentBlockSource=$contentBlockSource}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic
            fun ofBase64PdfSource(base64PdfSource: Base64PdfSource) =
                Source(base64PdfSource = base64PdfSource)

            @JvmStatic
            fun ofPlainTextSource(plainTextSource: PlainTextSource) =
                Source(plainTextSource = plainTextSource)

            @JvmStatic
            fun ofContentBlockSource(contentBlockSource: ContentBlockSource) =
                Source(contentBlockSource = contentBlockSource)
        }

        interface Visitor<out T> {

            fun visitBase64PdfSource(base64PdfSource: Base64PdfSource): T

            fun visitPlainTextSource(plainTextSource: PlainTextSource): T

            fun visitContentBlockSource(contentBlockSource: ContentBlockSource): T

            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Source: $json")
            }
        }

        class Deserializer : BaseDeserializer<Source>(Source::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Source {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "base64" -> {
                        tryDeserialize(node, jacksonTypeRef<Base64PdfSource>()) { it.validate() }
                            ?.let {
                                return Source(base64PdfSource = it, _json = json)
                            }
                    }
                    "text" -> {
                        tryDeserialize(node, jacksonTypeRef<PlainTextSource>()) { it.validate() }
                            ?.let {
                                return Source(plainTextSource = it, _json = json)
                            }
                    }
                    "content" -> {
                        tryDeserialize(node, jacksonTypeRef<ContentBlockSource>()) { it.validate() }
                            ?.let {
                                return Source(contentBlockSource = it, _json = json)
                            }
                    }
                }

                return Source(_json = json)
            }
        }

        class Serializer : BaseSerializer<Source>(Source::class) {

            override fun serialize(
                value: Source,
                generator: JsonGenerator,
                provider: SerializerProvider
            ) {
                when {
                    value.base64PdfSource != null -> generator.writeObject(value.base64PdfSource)
                    value.plainTextSource != null -> generator.writeObject(value.plainTextSource)
                    value.contentBlockSource != null ->
                        generator.writeObject(value.contentBlockSource)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Source")
                }
            }
        }
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val DOCUMENT = of("document")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            DOCUMENT,
        }

        enum class Value {
            DOCUMENT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                DOCUMENT -> Value.DOCUMENT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                DOCUMENT -> Known.DOCUMENT
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is DocumentBlockParam && source == other.source && type == other.type && cacheControl == other.cacheControl && citations == other.citations && context == other.context && title == other.title && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(source, type, cacheControl, citations, context, title, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "DocumentBlockParam{source=$source, type=$type, cacheControl=$cacheControl, citations=$citations, context=$context, title=$title, additionalProperties=$additionalProperties}"
}
