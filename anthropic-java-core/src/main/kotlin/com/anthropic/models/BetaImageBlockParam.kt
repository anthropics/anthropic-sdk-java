// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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
class BetaImageBlockParam
@JsonCreator
private constructor(
    @JsonProperty("source")
    @ExcludeMissing
    private val source: JsonField<Source> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun source(): Source = source.getRequired("source")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaImageBlockParam = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        _type().let {
            if (it != JsonValue.from("image")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaImageBlockParam]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonValue = JsonValue.from("image")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaImageBlockParam: BetaImageBlockParam) = apply {
            source = betaImageBlockParam.source
            type = betaImageBlockParam.type
            cacheControl = betaImageBlockParam.cacheControl
            additionalProperties = betaImageBlockParam.additionalProperties.toMutableMap()
        }

        fun source(source: Source) = source(JsonField.of(source))

        fun source(source: JsonField<Source>) = apply { this.source = source }

        fun source(betaBase64Image: BetaBase64ImageSource) =
            source(Source.ofBetaBase64Image(betaBase64Image))

        fun source(betaUrlImage: BetaUrlImageSource) = source(Source.ofBetaUrlImage(betaUrlImage))

        fun betaUrlImageSource(url: String) = source(BetaUrlImageSource.builder().url(url).build())

        fun type(type: JsonValue) = apply { this.type = type }

        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.orElse(null))

        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

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

        fun build(): BetaImageBlockParam =
            BetaImageBlockParam(
                checkRequired("source", source),
                type,
                cacheControl,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val betaBase64Image: BetaBase64ImageSource? = null,
        private val betaUrlImage: BetaUrlImageSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun betaBase64Image(): Optional<BetaBase64ImageSource> =
            Optional.ofNullable(betaBase64Image)

        fun betaUrlImage(): Optional<BetaUrlImageSource> = Optional.ofNullable(betaUrlImage)

        fun isBetaBase64Image(): Boolean = betaBase64Image != null

        fun isBetaUrlImage(): Boolean = betaUrlImage != null

        fun asBetaBase64Image(): BetaBase64ImageSource =
            betaBase64Image.getOrThrow("betaBase64Image")

        fun asBetaUrlImage(): BetaUrlImageSource = betaUrlImage.getOrThrow("betaUrlImage")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                betaBase64Image != null -> visitor.visitBetaBase64Image(betaBase64Image)
                betaUrlImage != null -> visitor.visitBetaUrlImage(betaUrlImage)
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
                    override fun visitBetaBase64Image(betaBase64Image: BetaBase64ImageSource) {
                        betaBase64Image.validate()
                    }

                    override fun visitBetaUrlImage(betaUrlImage: BetaUrlImageSource) {
                        betaUrlImage.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Source && betaBase64Image == other.betaBase64Image && betaUrlImage == other.betaUrlImage /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(betaBase64Image, betaUrlImage) /* spotless:on */

        override fun toString(): String =
            when {
                betaBase64Image != null -> "Source{betaBase64Image=$betaBase64Image}"
                betaUrlImage != null -> "Source{betaUrlImage=$betaUrlImage}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic
            fun ofBetaBase64Image(betaBase64Image: BetaBase64ImageSource) =
                Source(betaBase64Image = betaBase64Image)

            @JvmStatic
            fun ofBetaUrlImage(betaUrlImage: BetaUrlImageSource) =
                Source(betaUrlImage = betaUrlImage)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBetaBase64Image(betaBase64Image: BetaBase64ImageSource): T

            fun visitBetaUrlImage(betaUrlImage: BetaUrlImageSource): T

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
                        tryDeserialize(node, jacksonTypeRef<BetaBase64ImageSource>()) {
                                it.validate()
                            }
                            ?.let {
                                return Source(betaBase64Image = it, _json = json)
                            }
                    }
                    "url" -> {
                        tryDeserialize(node, jacksonTypeRef<BetaUrlImageSource>()) { it.validate() }
                            ?.let {
                                return Source(betaUrlImage = it, _json = json)
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
                    value.betaBase64Image != null -> generator.writeObject(value.betaBase64Image)
                    value.betaUrlImage != null -> generator.writeObject(value.betaUrlImage)
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

        return /* spotless:off */ other is BetaImageBlockParam && source == other.source && type == other.type && cacheControl == other.cacheControl && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(source, type, cacheControl, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaImageBlockParam{source=$source, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
