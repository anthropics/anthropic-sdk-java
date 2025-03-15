// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
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
class BetaBase64PdfBlock
@JsonCreator
private constructor(
    @JsonProperty("source")
    @ExcludeMissing
    private val source: JsonField<Source> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    @JsonProperty("citations")
    @ExcludeMissing
    private val citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of(),
    @JsonProperty("context")
    @ExcludeMissing
    private val context: JsonField<String> = JsonMissing.of(),
    @JsonProperty("title") @ExcludeMissing private val title: JsonField<String> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("document")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun citations(): Optional<BetaCitationsConfigParam> =
        Optional.ofNullable(citations.getNullable("citations"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun context(): Optional<String> = Optional.ofNullable(context.getNullable("context"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun title(): Optional<String> = Optional.ofNullable(title.getNullable("title"))

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<BetaCitationsConfigParam> = citations

    /**
     * Returns the raw JSON value of [context].
     *
     * Unlike [context], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("context") @ExcludeMissing fun _context(): JsonField<String> = context

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaBase64PdfBlock = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        _type().let {
            if (it != JsonValue.from("document")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        citations().ifPresent { it.validate() }
        context()
        title()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaBase64PdfBlock].
         *
         * The following fields are required:
         * ```java
         * .source()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBase64PdfBlock]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonValue = JsonValue.from("document")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of()
        private var context: JsonField<String> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBase64PdfBlock: BetaBase64PdfBlock) = apply {
            source = betaBase64PdfBlock.source
            type = betaBase64PdfBlock.type
            cacheControl = betaBase64PdfBlock.cacheControl
            citations = betaBase64PdfBlock.citations
            context = betaBase64PdfBlock.context
            title = betaBase64PdfBlock.title
            additionalProperties = betaBase64PdfBlock.additionalProperties.toMutableMap()
        }

        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofBetaBase64Pdf(betaBase64Pdf)`. */
        fun source(betaBase64Pdf: BetaBase64PdfSource) =
            source(Source.ofBetaBase64Pdf(betaBase64Pdf))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaBase64PdfSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun betaBase64PdfSource(data: String) =
            source(BetaBase64PdfSource.builder().data(data).build())

        /** Alias for calling [source] with `Source.ofBetaPlainText(betaPlainText)`. */
        fun source(betaPlainText: BetaPlainTextSource) =
            source(Source.ofBetaPlainText(betaPlainText))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaPlainTextSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun betaPlainTextSource(data: String) =
            source(BetaPlainTextSource.builder().data(data).build())

        /** Alias for calling [source] with `Source.ofBetaContentBlock(betaContentBlock)`. */
        fun source(betaContentBlock: BetaContentBlockSource) =
            source(Source.ofBetaContentBlock(betaContentBlock))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaContentBlockSource.builder()
         *     .content(content)
         *     .build()
         * ```
         */
        fun betaContentBlockSource(content: BetaContentBlockSource.Content) =
            source(BetaContentBlockSource.builder().content(content).build())

        /**
         * Alias for calling [betaContentBlockSource] with
         * `BetaContentBlockSource.Content.ofString(string)`.
         */
        fun betaContentBlockSource(string: String) =
            betaContentBlockSource(BetaContentBlockSource.Content.ofString(string))

        /**
         * Alias for calling [Builder.betaContentBlockSource] with
         * `BetaContentBlockSource.Content.ofBetaContentBlockSource(betaContentBlockSource)`.
         */
        fun betaContentBlockSourceOfBetaContentBlockSource(
            betaContentBlockSource: List<BetaContentBlockSourceContent>
        ) =
            betaContentBlockSource(
                BetaContentBlockSource.Content.ofBetaContentBlockSource(betaContentBlockSource)
            )

        /** Alias for calling [source] with `Source.ofBetaUrlPdf(betaUrlPdf)`. */
        fun source(betaUrlPdf: BetaUrlPdfSource) = source(Source.ofBetaUrlPdf(betaUrlPdf))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaUrlPdfSource.builder()
         *     .url(url)
         *     .build()
         * ```
         */
        fun betaUrlPdfSource(url: String) = source(BetaUrlPdfSource.builder().url(url).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("document")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun citations(citations: BetaCitationsConfigParam) = citations(JsonField.of(citations))

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [BetaCitationsConfigParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun citations(citations: JsonField<BetaCitationsConfigParam>) = apply {
            this.citations = citations
        }

        fun context(context: String?) = context(JsonField.ofNullable(context))

        /** Alias for calling [Builder.context] with `context.orElse(null)`. */
        fun context(context: Optional<String>) = context(context.getOrNull())

        /**
         * Sets [Builder.context] to an arbitrary JSON value.
         *
         * You should usually call [Builder.context] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun context(context: JsonField<String>) = apply { this.context = context }

        fun title(title: String?) = title(JsonField.ofNullable(title))

        /** Alias for calling [Builder.title] with `title.orElse(null)`. */
        fun title(title: Optional<String>) = title(title.getOrNull())

        /**
         * Sets [Builder.title] to an arbitrary JSON value.
         *
         * You should usually call [Builder.title] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
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

        fun build(): BetaBase64PdfBlock =
            BetaBase64PdfBlock(
                checkRequired("source", source),
                type,
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
        private val betaBase64Pdf: BetaBase64PdfSource? = null,
        private val betaPlainText: BetaPlainTextSource? = null,
        private val betaContentBlock: BetaContentBlockSource? = null,
        private val betaUrlPdf: BetaUrlPdfSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaBase64Pdf(): Optional<BetaBase64PdfSource> = Optional.ofNullable(betaBase64Pdf)

        fun betaPlainText(): Optional<BetaPlainTextSource> = Optional.ofNullable(betaPlainText)

        fun betaContentBlock(): Optional<BetaContentBlockSource> =
            Optional.ofNullable(betaContentBlock)

        fun betaUrlPdf(): Optional<BetaUrlPdfSource> = Optional.ofNullable(betaUrlPdf)

        fun isBetaBase64Pdf(): Boolean = betaBase64Pdf != null

        fun isBetaPlainText(): Boolean = betaPlainText != null

        fun isBetaContentBlock(): Boolean = betaContentBlock != null

        fun isBetaUrlPdf(): Boolean = betaUrlPdf != null

        fun asBetaBase64Pdf(): BetaBase64PdfSource = betaBase64Pdf.getOrThrow("betaBase64Pdf")

        fun asBetaPlainText(): BetaPlainTextSource = betaPlainText.getOrThrow("betaPlainText")

        fun asBetaContentBlock(): BetaContentBlockSource =
            betaContentBlock.getOrThrow("betaContentBlock")

        fun asBetaUrlPdf(): BetaUrlPdfSource = betaUrlPdf.getOrThrow("betaUrlPdf")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaBase64Pdf != null -> visitor.visitBetaBase64Pdf(betaBase64Pdf)
                betaPlainText != null -> visitor.visitBetaPlainText(betaPlainText)
                betaContentBlock != null -> visitor.visitBetaContentBlock(betaContentBlock)
                betaUrlPdf != null -> visitor.visitBetaUrlPdf(betaUrlPdf)
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
                    override fun visitBetaBase64Pdf(betaBase64Pdf: BetaBase64PdfSource) {
                        betaBase64Pdf.validate()
                    }

                    override fun visitBetaPlainText(betaPlainText: BetaPlainTextSource) {
                        betaPlainText.validate()
                    }

                    override fun visitBetaContentBlock(betaContentBlock: BetaContentBlockSource) {
                        betaContentBlock.validate()
                    }

                    override fun visitBetaUrlPdf(betaUrlPdf: BetaUrlPdfSource) {
                        betaUrlPdf.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Source && betaBase64Pdf == other.betaBase64Pdf && betaPlainText == other.betaPlainText && betaContentBlock == other.betaContentBlock && betaUrlPdf == other.betaUrlPdf /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaBase64Pdf, betaPlainText, betaContentBlock, betaUrlPdf) /* spotless:on */

        override fun toString(): String =
            when {
                betaBase64Pdf != null -> "Source{betaBase64Pdf=$betaBase64Pdf}"
                betaPlainText != null -> "Source{betaPlainText=$betaPlainText}"
                betaContentBlock != null -> "Source{betaContentBlock=$betaContentBlock}"
                betaUrlPdf != null -> "Source{betaUrlPdf=$betaUrlPdf}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic
            fun ofBetaBase64Pdf(betaBase64Pdf: BetaBase64PdfSource) =
                Source(betaBase64Pdf = betaBase64Pdf)

            @JvmStatic
            fun ofBetaPlainText(betaPlainText: BetaPlainTextSource) =
                Source(betaPlainText = betaPlainText)

            @JvmStatic
            fun ofBetaContentBlock(betaContentBlock: BetaContentBlockSource) =
                Source(betaContentBlock = betaContentBlock)

            @JvmStatic
            fun ofBetaUrlPdf(betaUrlPdf: BetaUrlPdfSource) = Source(betaUrlPdf = betaUrlPdf)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBetaBase64Pdf(betaBase64Pdf: BetaBase64PdfSource): T

            fun visitBetaPlainText(betaPlainText: BetaPlainTextSource): T

            fun visitBetaContentBlock(betaContentBlock: BetaContentBlockSource): T

            fun visitBetaUrlPdf(betaUrlPdf: BetaUrlPdfSource): T

            /**
             * Maps an unknown variant of [Source] to a value of type [T].
             *
             * An instance of [Source] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Source: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Source>(Source::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Source {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "base64" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaBase64PdfSource>()) {
                                it.validate()
                            }
                            ?.let {
                                return Source(betaBase64Pdf = it, _json = json)
                            }
                    }
                    "text" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaPlainTextSource>()) {
                                it.validate()
                            }
                            ?.let {
                                return Source(betaPlainText = it, _json = json)
                            }
                    }
                    "content" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaContentBlockSource>()) {
                                it.validate()
                            }
                            ?.let {
                                return Source(betaContentBlock = it, _json = json)
                            }
                    }
                    "url" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaUrlPdfSource>()) { it.validate() }
                            ?.let {
                                return Source(betaUrlPdf = it, _json = json)
                            }
                    }
                }

                return Source(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Source>(Source::class) {

            override fun serialize(
                value: Source,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaBase64Pdf != null -> generator.writeObject(value.betaBase64Pdf)
                    value.betaPlainText != null -> generator.writeObject(value.betaPlainText)
                    value.betaContentBlock != null -> generator.writeObject(value.betaContentBlock)
                    value.betaUrlPdf != null -> generator.writeObject(value.betaUrlPdf)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Source")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaBase64PdfBlock && source == other.source && type == other.type && cacheControl == other.cacheControl && citations == other.citations && context == other.context && title == other.title && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(source, type, cacheControl, citations, context, title, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBase64PdfBlock{source=$source, type=$type, cacheControl=$cacheControl, citations=$citations, context=$context, title=$title, additionalProperties=$additionalProperties}"
}
