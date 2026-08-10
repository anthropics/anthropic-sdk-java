// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Response envelope for request-level diagnostics. Present (possibly null) whenever the caller
 * supplied `diagnostics` on the request.
 */
class BetaDiagnostics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheMissReason: JsonField<CacheMissReason>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_miss_reason")
        @ExcludeMissing
        cacheMissReason: JsonField<CacheMissReason> = JsonMissing.of()
    ) : this(cacheMissReason, mutableMapOf())

    /**
     * Explains why the prompt cache could not fully reuse the prefix from the request identified by
     * `diagnostics.previous_message_id`. `null` means diagnosis is still pending — the response was
     * serialized before the background comparison completed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheMissReason(): Optional<CacheMissReason> =
        cacheMissReason.getOptional("cache_miss_reason")

    /**
     * Returns the raw JSON value of [cacheMissReason].
     *
     * Unlike [cacheMissReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_miss_reason")
    @ExcludeMissing
    fun _cacheMissReason(): JsonField<CacheMissReason> = cacheMissReason

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
         * Returns a mutable builder for constructing an instance of [BetaDiagnostics].
         *
         * The following fields are required:
         * ```java
         * .cacheMissReason()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaDiagnostics] with the required [cacheMissReason]
         * set to the given value.
         */
        @JvmStatic
        fun of(cacheMissReason: CacheMissReason?) =
            builder().cacheMissReason(cacheMissReason).build()

        /** Alias for calling [of] with `cacheMissReason.orElse(null)`. */
        @JvmStatic
        fun of(cacheMissReason: Optional<CacheMissReason>) = of(cacheMissReason.getOrNull())
    }

    /** A builder for [BetaDiagnostics]. */
    class Builder internal constructor() {

        private var cacheMissReason: JsonField<CacheMissReason>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDiagnostics: BetaDiagnostics) = apply {
            cacheMissReason = betaDiagnostics.cacheMissReason
            additionalProperties = betaDiagnostics.additionalProperties.toMutableMap()
        }

        /**
         * Explains why the prompt cache could not fully reuse the prefix from the request
         * identified by `diagnostics.previous_message_id`. `null` means diagnosis is still pending
         * — the response was serialized before the background comparison completed.
         */
        fun cacheMissReason(cacheMissReason: CacheMissReason?) =
            cacheMissReason(JsonField.ofNullable(cacheMissReason))

        /** Alias for calling [Builder.cacheMissReason] with `cacheMissReason.orElse(null)`. */
        fun cacheMissReason(cacheMissReason: Optional<CacheMissReason>) =
            cacheMissReason(cacheMissReason.getOrNull())

        /**
         * Sets [Builder.cacheMissReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheMissReason] with a well-typed [CacheMissReason]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheMissReason(cacheMissReason: JsonField<CacheMissReason>) = apply {
            this.cacheMissReason = cacheMissReason
        }

        /**
         * Alias for calling [cacheMissReason] with `CacheMissReason.ofModelChanged(modelChanged)`.
         */
        fun cacheMissReason(modelChanged: BetaCacheMissModelChanged) =
            cacheMissReason(CacheMissReason.ofModelChanged(modelChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissModelChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun modelChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissModelChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `CacheMissReason.ofSystemChanged(systemChanged)`.
         */
        fun cacheMissReason(systemChanged: BetaCacheMissSystemChanged) =
            cacheMissReason(CacheMissReason.ofSystemChanged(systemChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissSystemChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun systemChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissSystemChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with `CacheMissReason.ofToolsChanged(toolsChanged)`.
         */
        fun cacheMissReason(toolsChanged: BetaCacheMissToolsChanged) =
            cacheMissReason(CacheMissReason.ofToolsChanged(toolsChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissToolsChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun toolsChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissToolsChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `CacheMissReason.ofMessagesChanged(messagesChanged)`.
         */
        fun cacheMissReason(messagesChanged: BetaCacheMissMessagesChanged) =
            cacheMissReason(CacheMissReason.ofMessagesChanged(messagesChanged))

        /**
         * Alias for calling [cacheMissReason] with the following:
         * ```java
         * BetaCacheMissMessagesChanged.builder()
         *     .cacheMissedInputTokens(cacheMissedInputTokens)
         *     .build()
         * ```
         */
        fun messagesChangedCacheMissReason(cacheMissedInputTokens: Long) =
            cacheMissReason(
                BetaCacheMissMessagesChanged.builder()
                    .cacheMissedInputTokens(cacheMissedInputTokens)
                    .build()
            )

        /**
         * Alias for calling [cacheMissReason] with
         * `CacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound)`.
         */
        fun cacheMissReason(previousMessageNotFound: BetaCacheMissPreviousMessageNotFound) =
            cacheMissReason(CacheMissReason.ofPreviousMessageNotFound(previousMessageNotFound))

        /**
         * Alias for calling [cacheMissReason] with `CacheMissReason.ofUnavailable(unavailable)`.
         */
        fun cacheMissReason(unavailable: BetaCacheMissUnavailable) =
            cacheMissReason(CacheMissReason.ofUnavailable(unavailable))

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
         * Returns an immutable instance of [BetaDiagnostics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheMissReason()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaDiagnostics =
            BetaDiagnostics(
                checkRequired("cacheMissReason", cacheMissReason),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaDiagnostics = apply {
        if (validated) {
            return@apply
        }

        cacheMissReason().ifPresent { it.validate() }
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
    internal fun validity(): Int = (cacheMissReason.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Explains why the prompt cache could not fully reuse the prefix from the request identified by
     * `diagnostics.previous_message_id`. `null` means diagnosis is still pending — the response was
     * serialized before the background comparison completed.
     */
    @JsonDeserialize(using = CacheMissReason.Deserializer::class)
    @JsonSerialize(using = CacheMissReason.Serializer::class)
    class CacheMissReason
    private constructor(
        private val modelChanged: BetaCacheMissModelChanged? = null,
        private val systemChanged: BetaCacheMissSystemChanged? = null,
        private val toolsChanged: BetaCacheMissToolsChanged? = null,
        private val messagesChanged: BetaCacheMissMessagesChanged? = null,
        private val previousMessageNotFound: BetaCacheMissPreviousMessageNotFound? = null,
        private val unavailable: BetaCacheMissUnavailable? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitModelChanged(modelChanged: BetaCacheMissModelChanged): Type =
                        Type.MODEL_CHANGED

                    override fun visitSystemChanged(
                        systemChanged: BetaCacheMissSystemChanged
                    ): Type = Type.SYSTEM_CHANGED

                    override fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged): Type =
                        Type.TOOLS_CHANGED

                    override fun visitMessagesChanged(
                        messagesChanged: BetaCacheMissMessagesChanged
                    ): Type = Type.MESSAGES_CHANGED

                    override fun visitPreviousMessageNotFound(
                        previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                    ): Type = Type.PREVIOUS_MESSAGE_NOT_FOUND

                    override fun visitUnavailable(unavailable: BetaCacheMissUnavailable): Type =
                        Type.UNAVAILABLE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun cacheMissedInputTokens(): Optional<Long> =
            accept(
                object : Visitor<Optional<Long>> {
                    override fun visitModelChanged(
                        modelChanged: BetaCacheMissModelChanged
                    ): Optional<Long> = Optional.of(modelChanged.cacheMissedInputTokens())

                    override fun visitSystemChanged(
                        systemChanged: BetaCacheMissSystemChanged
                    ): Optional<Long> = Optional.of(systemChanged.cacheMissedInputTokens())

                    override fun visitToolsChanged(
                        toolsChanged: BetaCacheMissToolsChanged
                    ): Optional<Long> = Optional.of(toolsChanged.cacheMissedInputTokens())

                    override fun visitMessagesChanged(
                        messagesChanged: BetaCacheMissMessagesChanged
                    ): Optional<Long> = Optional.of(messagesChanged.cacheMissedInputTokens())

                    override fun visitPreviousMessageNotFound(
                        previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                    ): Optional<Long> = Optional.empty()

                    override fun visitUnavailable(
                        unavailable: BetaCacheMissUnavailable
                    ): Optional<Long> = Optional.empty()
                }
            )

        fun modelChanged(): Optional<BetaCacheMissModelChanged> = Optional.ofNullable(modelChanged)

        fun systemChanged(): Optional<BetaCacheMissSystemChanged> =
            Optional.ofNullable(systemChanged)

        fun toolsChanged(): Optional<BetaCacheMissToolsChanged> = Optional.ofNullable(toolsChanged)

        fun messagesChanged(): Optional<BetaCacheMissMessagesChanged> =
            Optional.ofNullable(messagesChanged)

        fun previousMessageNotFound(): Optional<BetaCacheMissPreviousMessageNotFound> =
            Optional.ofNullable(previousMessageNotFound)

        fun unavailable(): Optional<BetaCacheMissUnavailable> = Optional.ofNullable(unavailable)

        fun isModelChanged(): Boolean = modelChanged != null

        fun isSystemChanged(): Boolean = systemChanged != null

        fun isToolsChanged(): Boolean = toolsChanged != null

        fun isMessagesChanged(): Boolean = messagesChanged != null

        fun isPreviousMessageNotFound(): Boolean = previousMessageNotFound != null

        fun isUnavailable(): Boolean = unavailable != null

        fun asModelChanged(): BetaCacheMissModelChanged = modelChanged.getOrThrow("modelChanged")

        fun asSystemChanged(): BetaCacheMissSystemChanged =
            systemChanged.getOrThrow("systemChanged")

        fun asToolsChanged(): BetaCacheMissToolsChanged = toolsChanged.getOrThrow("toolsChanged")

        fun asMessagesChanged(): BetaCacheMissMessagesChanged =
            messagesChanged.getOrThrow("messagesChanged")

        fun asPreviousMessageNotFound(): BetaCacheMissPreviousMessageNotFound =
            previousMessageNotFound.getOrThrow("previousMessageNotFound")

        fun asUnavailable(): BetaCacheMissUnavailable = unavailable.getOrThrow("unavailable")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = cacheMissReason.accept(new CacheMissReason.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitModelChanged(BetaCacheMissModelChanged modelChanged) {
         *         return Optional.of(modelChanged.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                modelChanged != null -> visitor.visitModelChanged(modelChanged)
                systemChanged != null -> visitor.visitSystemChanged(systemChanged)
                toolsChanged != null -> visitor.visitToolsChanged(toolsChanged)
                messagesChanged != null -> visitor.visitMessagesChanged(messagesChanged)
                previousMessageNotFound != null ->
                    visitor.visitPreviousMessageNotFound(previousMessageNotFound)
                unavailable != null -> visitor.visitUnavailable(unavailable)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): CacheMissReason = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitModelChanged(modelChanged: BetaCacheMissModelChanged) {
                        modelChanged.validate()
                    }

                    override fun visitSystemChanged(systemChanged: BetaCacheMissSystemChanged) {
                        systemChanged.validate()
                    }

                    override fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged) {
                        toolsChanged.validate()
                    }

                    override fun visitMessagesChanged(
                        messagesChanged: BetaCacheMissMessagesChanged
                    ) {
                        messagesChanged.validate()
                    }

                    override fun visitPreviousMessageNotFound(
                        previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                    ) {
                        previousMessageNotFound.validate()
                    }

                    override fun visitUnavailable(unavailable: BetaCacheMissUnavailable) {
                        unavailable.validate()
                    }
                }
            )
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitModelChanged(modelChanged: BetaCacheMissModelChanged) =
                        modelChanged.validity()

                    override fun visitSystemChanged(systemChanged: BetaCacheMissSystemChanged) =
                        systemChanged.validity()

                    override fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged) =
                        toolsChanged.validity()

                    override fun visitMessagesChanged(
                        messagesChanged: BetaCacheMissMessagesChanged
                    ) = messagesChanged.validity()

                    override fun visitPreviousMessageNotFound(
                        previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                    ) = previousMessageNotFound.validity()

                    override fun visitUnavailable(unavailable: BetaCacheMissUnavailable) =
                        unavailable.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is CacheMissReason &&
                modelChanged == other.modelChanged &&
                systemChanged == other.systemChanged &&
                toolsChanged == other.toolsChanged &&
                messagesChanged == other.messagesChanged &&
                previousMessageNotFound == other.previousMessageNotFound &&
                unavailable == other.unavailable
        }

        override fun hashCode(): Int =
            Objects.hash(
                modelChanged,
                systemChanged,
                toolsChanged,
                messagesChanged,
                previousMessageNotFound,
                unavailable,
            )

        override fun toString(): String =
            when {
                modelChanged != null -> "CacheMissReason{modelChanged=$modelChanged}"
                systemChanged != null -> "CacheMissReason{systemChanged=$systemChanged}"
                toolsChanged != null -> "CacheMissReason{toolsChanged=$toolsChanged}"
                messagesChanged != null -> "CacheMissReason{messagesChanged=$messagesChanged}"
                previousMessageNotFound != null ->
                    "CacheMissReason{previousMessageNotFound=$previousMessageNotFound}"
                unavailable != null -> "CacheMissReason{unavailable=$unavailable}"
                _json != null -> "CacheMissReason{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid CacheMissReason")
            }

        companion object {

            @JvmStatic
            fun ofModelChanged(modelChanged: BetaCacheMissModelChanged) =
                CacheMissReason(modelChanged = modelChanged)

            /**
             * Returns an immutable instance of [CacheMissReason] whose [ofModelChanged] variant is
             * built from the given required [cacheMissedInputTokens].
             */
            @JvmStatic
            fun ofModelChanged(cacheMissedInputTokens: Long) =
                ofModelChanged(BetaCacheMissModelChanged.of(cacheMissedInputTokens))

            @JvmStatic
            fun ofSystemChanged(systemChanged: BetaCacheMissSystemChanged) =
                CacheMissReason(systemChanged = systemChanged)

            /**
             * Returns an immutable instance of [CacheMissReason] whose [ofSystemChanged] variant is
             * built from the given required [cacheMissedInputTokens].
             */
            @JvmStatic
            fun ofSystemChanged(cacheMissedInputTokens: Long) =
                ofSystemChanged(BetaCacheMissSystemChanged.of(cacheMissedInputTokens))

            @JvmStatic
            fun ofToolsChanged(toolsChanged: BetaCacheMissToolsChanged) =
                CacheMissReason(toolsChanged = toolsChanged)

            /**
             * Returns an immutable instance of [CacheMissReason] whose [ofToolsChanged] variant is
             * built from the given required [cacheMissedInputTokens].
             */
            @JvmStatic
            fun ofToolsChanged(cacheMissedInputTokens: Long) =
                ofToolsChanged(BetaCacheMissToolsChanged.of(cacheMissedInputTokens))

            @JvmStatic
            fun ofMessagesChanged(messagesChanged: BetaCacheMissMessagesChanged) =
                CacheMissReason(messagesChanged = messagesChanged)

            /**
             * Returns an immutable instance of [CacheMissReason] whose [ofMessagesChanged] variant
             * is built from the given required [cacheMissedInputTokens].
             */
            @JvmStatic
            fun ofMessagesChanged(cacheMissedInputTokens: Long) =
                ofMessagesChanged(BetaCacheMissMessagesChanged.of(cacheMissedInputTokens))

            @JvmStatic
            fun ofPreviousMessageNotFound(
                previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
            ) = CacheMissReason(previousMessageNotFound = previousMessageNotFound)

            @JvmStatic
            fun ofUnavailable(unavailable: BetaCacheMissUnavailable) =
                CacheMissReason(unavailable = unavailable)
        }

        /**
         * An interface that defines how to map each variant of [CacheMissReason] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitModelChanged(modelChanged: BetaCacheMissModelChanged): T

            fun visitSystemChanged(systemChanged: BetaCacheMissSystemChanged): T

            fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged): T

            fun visitMessagesChanged(messagesChanged: BetaCacheMissMessagesChanged): T

            fun visitPreviousMessageNotFound(
                previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
            ): T

            fun visitUnavailable(unavailable: BetaCacheMissUnavailable): T

            /**
             * Maps an unknown variant of [CacheMissReason] to a value of type [T].
             *
             * An instance of [CacheMissReason] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown CacheMissReason: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<CacheMissReason>(CacheMissReason::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): CacheMissReason {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "model_changed" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCacheMissModelChanged>())
                            ?.let { CacheMissReason(modelChanged = it, _json = json) }
                            ?: CacheMissReason(_json = json)
                    }
                    "system_changed" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCacheMissSystemChanged>())
                            ?.let { CacheMissReason(systemChanged = it, _json = json) }
                            ?: CacheMissReason(_json = json)
                    }
                    "tools_changed" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCacheMissToolsChanged>())
                            ?.let { CacheMissReason(toolsChanged = it, _json = json) }
                            ?: CacheMissReason(_json = json)
                    }
                    "messages_changed" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCacheMissMessagesChanged>())
                            ?.let { CacheMissReason(messagesChanged = it, _json = json) }
                            ?: CacheMissReason(_json = json)
                    }
                    "previous_message_not_found" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaCacheMissPreviousMessageNotFound>(),
                            )
                            ?.let { CacheMissReason(previousMessageNotFound = it, _json = json) }
                            ?: CacheMissReason(_json = json)
                    }
                    "unavailable" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCacheMissUnavailable>())
                            ?.let { CacheMissReason(unavailable = it, _json = json) }
                            ?: CacheMissReason(_json = json)
                    }
                }

                return CacheMissReason(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<CacheMissReason>(CacheMissReason::class) {

            override fun serialize(
                value: CacheMissReason,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.modelChanged != null -> generator.writeObject(value.modelChanged)
                    value.systemChanged != null -> generator.writeObject(value.systemChanged)
                    value.toolsChanged != null -> generator.writeObject(value.toolsChanged)
                    value.messagesChanged != null -> generator.writeObject(value.messagesChanged)
                    value.previousMessageNotFound != null ->
                        generator.writeObject(value.previousMessageNotFound)
                    value.unavailable != null -> generator.writeObject(value.unavailable)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid CacheMissReason")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val MODEL_CHANGED = of("model_changed")

                @JvmField val SYSTEM_CHANGED = of("system_changed")

                @JvmField val TOOLS_CHANGED = of("tools_changed")

                @JvmField val MESSAGES_CHANGED = of("messages_changed")

                @JvmField val PREVIOUS_MESSAGE_NOT_FOUND = of("previous_message_not_found")

                @JvmField val UNAVAILABLE = of("unavailable")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonValue): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                MODEL_CHANGED,
                SYSTEM_CHANGED,
                TOOLS_CHANGED,
                MESSAGES_CHANGED,
                PREVIOUS_MESSAGE_NOT_FOUND,
                UNAVAILABLE,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                MODEL_CHANGED,
                SYSTEM_CHANGED,
                TOOLS_CHANGED,
                MESSAGES_CHANGED,
                PREVIOUS_MESSAGE_NOT_FOUND,
                UNAVAILABLE,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    MODEL_CHANGED -> Value.MODEL_CHANGED
                    SYSTEM_CHANGED -> Value.SYSTEM_CHANGED
                    TOOLS_CHANGED -> Value.TOOLS_CHANGED
                    MESSAGES_CHANGED -> Value.MESSAGES_CHANGED
                    PREVIOUS_MESSAGE_NOT_FOUND -> Value.PREVIOUS_MESSAGE_NOT_FOUND
                    UNAVAILABLE -> Value.UNAVAILABLE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    MODEL_CHANGED -> Known.MODEL_CHANGED
                    SYSTEM_CHANGED -> Known.SYSTEM_CHANGED
                    TOOLS_CHANGED -> Known.TOOLS_CHANGED
                    MESSAGES_CHANGED -> Known.MESSAGES_CHANGED
                    PREVIOUS_MESSAGE_NOT_FOUND -> Known.PREVIOUS_MESSAGE_NOT_FOUND
                    UNAVAILABLE -> Known.UNAVAILABLE
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
             * Returns a score indicating how many valid values are contained in this object
             * recursively.
             *
             * Used for best match union deserialization.
             */
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDiagnostics &&
            cacheMissReason == other.cacheMissReason &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(cacheMissReason, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDiagnostics{cacheMissReason=$cacheMissReason, additionalProperties=$additionalProperties}"
}
