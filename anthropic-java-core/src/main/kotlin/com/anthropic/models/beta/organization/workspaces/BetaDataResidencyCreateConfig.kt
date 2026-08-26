// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaDataResidencyCreateConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val allowedInferenceGeos: JsonField<AllowedInferenceGeos>,
    private val defaultInferenceGeo: JsonField<DefaultInferenceGeo>,
    private val workspaceGeo: JsonField<WorkspaceGeo>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("allowed_inference_geos")
        @ExcludeMissing
        allowedInferenceGeos: JsonField<AllowedInferenceGeos> = JsonMissing.of(),
        @JsonProperty("default_inference_geo")
        @ExcludeMissing
        defaultInferenceGeo: JsonField<DefaultInferenceGeo> = JsonMissing.of(),
        @JsonProperty("workspace_geo")
        @ExcludeMissing
        workspaceGeo: JsonField<WorkspaceGeo> = JsonMissing.of(),
    ) : this(allowedInferenceGeos, defaultInferenceGeo, workspaceGeo, mutableMapOf())

    /**
     * Permitted inference geo values. Defaults to 'unrestricted' if omitted, which allows all geos.
     * Use the string 'unrestricted' to allow all geos, or a list of specific geos.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun allowedInferenceGeos(): Optional<AllowedInferenceGeos> =
        allowedInferenceGeos.getOptional("allowed_inference_geos")

    /**
     * Default inference geo applied when requests omit the parameter. Defaults to 'global' if
     * omitted. Must be a member of `allowed_inference_geos` unless `allowed_inference_geos` is
     * `"unrestricted"`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun defaultInferenceGeo(): Optional<DefaultInferenceGeo> =
        defaultInferenceGeo.getOptional("default_inference_geo")

    /**
     * Geographic region for workspace data storage. Immutable after creation. Defaults to 'us' if
     * omitted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workspaceGeo(): Optional<WorkspaceGeo> = workspaceGeo.getOptional("workspace_geo")

    /**
     * Returns the raw JSON value of [allowedInferenceGeos].
     *
     * Unlike [allowedInferenceGeos], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("allowed_inference_geos")
    @ExcludeMissing
    fun _allowedInferenceGeos(): JsonField<AllowedInferenceGeos> = allowedInferenceGeos

    /**
     * Returns the raw JSON value of [defaultInferenceGeo].
     *
     * Unlike [defaultInferenceGeo], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("default_inference_geo")
    @ExcludeMissing
    fun _defaultInferenceGeo(): JsonField<DefaultInferenceGeo> = defaultInferenceGeo

    /**
     * Returns the raw JSON value of [workspaceGeo].
     *
     * Unlike [workspaceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_geo")
    @ExcludeMissing
    fun _workspaceGeo(): JsonField<WorkspaceGeo> = workspaceGeo

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
         * Returns a mutable builder for constructing an instance of
         * [BetaDataResidencyCreateConfig].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaDataResidencyCreateConfig]. */
    class Builder internal constructor() {

        private var allowedInferenceGeos: JsonField<AllowedInferenceGeos> = JsonMissing.of()
        private var defaultInferenceGeo: JsonField<DefaultInferenceGeo> = JsonMissing.of()
        private var workspaceGeo: JsonField<WorkspaceGeo> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDataResidencyCreateConfig: BetaDataResidencyCreateConfig) = apply {
            allowedInferenceGeos = betaDataResidencyCreateConfig.allowedInferenceGeos
            defaultInferenceGeo = betaDataResidencyCreateConfig.defaultInferenceGeo
            workspaceGeo = betaDataResidencyCreateConfig.workspaceGeo
            additionalProperties = betaDataResidencyCreateConfig.additionalProperties.toMutableMap()
        }

        /**
         * Permitted inference geo values. Defaults to 'unrestricted' if omitted, which allows all
         * geos. Use the string 'unrestricted' to allow all geos, or a list of specific geos.
         */
        fun allowedInferenceGeos(allowedInferenceGeos: AllowedInferenceGeos?) =
            allowedInferenceGeos(JsonField.ofNullable(allowedInferenceGeos))

        /**
         * Alias for calling [Builder.allowedInferenceGeos] with
         * `allowedInferenceGeos.orElse(null)`.
         */
        fun allowedInferenceGeos(allowedInferenceGeos: Optional<AllowedInferenceGeos>) =
            allowedInferenceGeos(allowedInferenceGeos.getOrNull())

        /**
         * Sets [Builder.allowedInferenceGeos] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedInferenceGeos] with a well-typed
         * [AllowedInferenceGeos] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun allowedInferenceGeos(allowedInferenceGeos: JsonField<AllowedInferenceGeos>) = apply {
            this.allowedInferenceGeos = allowedInferenceGeos
        }

        /** Alias for calling [allowedInferenceGeos] with `AllowedInferenceGeos.ofGeos(geos)`. */
        fun allowedInferenceGeosOfGeos(geos: List<BetaAllowedInferenceGeo>) =
            allowedInferenceGeos(AllowedInferenceGeos.ofGeos(geos))

        /**
         * Alias for calling [allowedInferenceGeos] with `AllowedInferenceGeos.ofUnrestricted()`.
         */
        fun allowedInferenceGeosUnrestricted() =
            allowedInferenceGeos(AllowedInferenceGeos.ofUnrestricted())

        /**
         * Default inference geo applied when requests omit the parameter. Defaults to 'global' if
         * omitted. Must be a member of `allowed_inference_geos` unless `allowed_inference_geos` is
         * `"unrestricted"`.
         */
        fun defaultInferenceGeo(defaultInferenceGeo: DefaultInferenceGeo?) =
            defaultInferenceGeo(JsonField.ofNullable(defaultInferenceGeo))

        /**
         * Alias for calling [Builder.defaultInferenceGeo] with `defaultInferenceGeo.orElse(null)`.
         */
        fun defaultInferenceGeo(defaultInferenceGeo: Optional<DefaultInferenceGeo>) =
            defaultInferenceGeo(defaultInferenceGeo.getOrNull())

        /**
         * Sets [Builder.defaultInferenceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.defaultInferenceGeo] with a well-typed
         * [DefaultInferenceGeo] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun defaultInferenceGeo(defaultInferenceGeo: JsonField<DefaultInferenceGeo>) = apply {
            this.defaultInferenceGeo = defaultInferenceGeo
        }

        /**
         * Geographic region for workspace data storage. Immutable after creation. Defaults to 'us'
         * if omitted.
         */
        fun workspaceGeo(workspaceGeo: WorkspaceGeo?) =
            workspaceGeo(JsonField.ofNullable(workspaceGeo))

        /** Alias for calling [Builder.workspaceGeo] with `workspaceGeo.orElse(null)`. */
        fun workspaceGeo(workspaceGeo: Optional<WorkspaceGeo>) =
            workspaceGeo(workspaceGeo.getOrNull())

        /**
         * Sets [Builder.workspaceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceGeo] with a well-typed [WorkspaceGeo] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun workspaceGeo(workspaceGeo: JsonField<WorkspaceGeo>) = apply {
            this.workspaceGeo = workspaceGeo
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

        /**
         * Returns an immutable instance of [BetaDataResidencyCreateConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaDataResidencyCreateConfig =
            BetaDataResidencyCreateConfig(
                allowedInferenceGeos,
                defaultInferenceGeo,
                workspaceGeo,
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
    fun validate(): BetaDataResidencyCreateConfig = apply {
        if (validated) {
            return@apply
        }

        allowedInferenceGeos().ifPresent { it.validate() }
        defaultInferenceGeo().ifPresent { it.validate() }
        workspaceGeo().ifPresent { it.validate() }
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
        (allowedInferenceGeos.asKnown().getOrNull()?.validity() ?: 0) +
            (defaultInferenceGeo.asKnown().getOrNull()?.validity() ?: 0) +
            (workspaceGeo.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Permitted inference geo values. Defaults to 'unrestricted' if omitted, which allows all geos.
     * Use the string 'unrestricted' to allow all geos, or a list of specific geos.
     */
    @JsonDeserialize(using = AllowedInferenceGeos.Deserializer::class)
    @JsonSerialize(using = AllowedInferenceGeos.Serializer::class)
    class AllowedInferenceGeos
    private constructor(
        private val geos: List<BetaAllowedInferenceGeo>? = null,
        private val unrestricted: JsonValue? = null,
        private val _json: JsonValue? = null,
    ) {

        fun geos(): Optional<List<BetaAllowedInferenceGeo>> = Optional.ofNullable(geos)

        fun unrestricted(): Optional<JsonValue> = Optional.ofNullable(unrestricted)

        fun isGeos(): Boolean = geos != null

        fun isUnrestricted(): Boolean = unrestricted != null

        fun asGeos(): List<BetaAllowedInferenceGeo> = geos.getOrThrow("geos")

        fun asUnrestricted(): JsonValue = unrestricted.getOrThrow("unrestricted")

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
         * Optional<String> result = allowedInferenceGeos.accept(new AllowedInferenceGeos.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitGeos(List<BetaAllowedInferenceGeo> geos) {
         *         return Optional.of(geos.toString());
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
                geos != null -> visitor.visitGeos(geos)
                unrestricted != null -> visitor.visitUnrestricted(unrestricted)
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
        fun validate(): AllowedInferenceGeos = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitGeos(geos: List<BetaAllowedInferenceGeo>) {
                        geos.forEach { it.validate() }
                    }

                    override fun visitUnrestricted(unrestricted: JsonValue) {
                        unrestricted.let {
                            if (it != JsonValue.from("unrestricted")) {
                                throw AnthropicInvalidDataException(
                                    "'unrestricted' is invalid, received $it"
                                )
                            }
                        }
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
                    override fun visitGeos(geos: List<BetaAllowedInferenceGeo>) =
                        geos.sumOf { it.validity().toInt() }

                    override fun visitUnrestricted(unrestricted: JsonValue) =
                        unrestricted.let { if (it == JsonValue.from("unrestricted")) 1 else 0 }

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AllowedInferenceGeos &&
                geos == other.geos &&
                unrestricted == other.unrestricted
        }

        override fun hashCode(): Int = Objects.hash(geos, unrestricted)

        override fun toString(): String =
            when {
                geos != null -> "AllowedInferenceGeos{geos=$geos}"
                unrestricted != null -> "AllowedInferenceGeos{unrestricted=$unrestricted}"
                _json != null -> "AllowedInferenceGeos{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid AllowedInferenceGeos")
            }

        companion object {

            @JvmStatic
            fun ofGeos(geos: List<BetaAllowedInferenceGeo>) =
                AllowedInferenceGeos(geos = geos.toImmutable())

            @JvmStatic
            fun ofUnrestricted() =
                AllowedInferenceGeos(unrestricted = JsonValue.from("unrestricted"))
        }

        /**
         * An interface that defines how to map each variant of [AllowedInferenceGeos] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            fun visitGeos(geos: List<BetaAllowedInferenceGeo>): T

            fun visitUnrestricted(unrestricted: JsonValue): T

            /**
             * Maps an unknown variant of [AllowedInferenceGeos] to a value of type [T].
             *
             * An instance of [AllowedInferenceGeos] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown AllowedInferenceGeos: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<AllowedInferenceGeos>(AllowedInferenceGeos::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): AllowedInferenceGeos {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<JsonValue>())
                                ?.let { AllowedInferenceGeos(unrestricted = it, _json = json) }
                                ?.takeIf { it.isValid() },
                            tryDeserialize(node, jacksonTypeRef<List<BetaAllowedInferenceGeo>>())
                                ?.let { AllowedInferenceGeos(geos = it, _json = json) },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> AllowedInferenceGeos(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer :
            BaseSerializer<AllowedInferenceGeos>(AllowedInferenceGeos::class) {

            override fun serialize(
                value: AllowedInferenceGeos,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.geos != null -> generator.writeObject(value.geos)
                    value.unrestricted != null -> generator.writeObject(value.unrestricted)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid AllowedInferenceGeos")
                }
            }
        }
    }

    /**
     * Default inference geo applied when requests omit the parameter. Defaults to 'global' if
     * omitted. Must be a member of `allowed_inference_geos` unless `allowed_inference_geos` is
     * `"unrestricted"`.
     */
    class DefaultInferenceGeo
    @JsonCreator
    private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val GLOBAL = of("global")

            @JvmField val US = of("us")

            @JvmStatic fun of(value: String) = DefaultInferenceGeo(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): DefaultInferenceGeo =
                value.asString().getOrNull()?.let { of(it) } ?: DefaultInferenceGeo(value)
        }

        /** An enum containing [DefaultInferenceGeo]'s known values. */
        enum class Known {
            GLOBAL,
            US,
        }

        /**
         * An enum containing [DefaultInferenceGeo]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [DefaultInferenceGeo] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            GLOBAL,
            US,
            /**
             * An enum member indicating that [DefaultInferenceGeo] was instantiated with an unknown
             * value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                GLOBAL -> Value.GLOBAL
                US -> Value.US
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                GLOBAL -> Known.GLOBAL
                US -> Known.US
                else -> throw AnthropicInvalidDataException("Unknown DefaultInferenceGeo: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
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
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): DefaultInferenceGeo = apply {
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

            return other is DefaultInferenceGeo && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * Geographic region for workspace data storage. Immutable after creation. Defaults to 'us' if
     * omitted.
     */
    class WorkspaceGeo @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val US = of("us")

            @JvmStatic fun of(value: String) = WorkspaceGeo(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): WorkspaceGeo =
                value.asString().getOrNull()?.let { of(it) } ?: WorkspaceGeo(value)
        }

        /** An enum containing [WorkspaceGeo]'s known values. */
        enum class Known {
            US
        }

        /**
         * An enum containing [WorkspaceGeo]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [WorkspaceGeo] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            US,
            /**
             * An enum member indicating that [WorkspaceGeo] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                US -> Value.US
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                US -> Known.US
                else -> throw AnthropicInvalidDataException("Unknown WorkspaceGeo: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
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
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): WorkspaceGeo = apply {
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

            return other is WorkspaceGeo && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDataResidencyCreateConfig &&
            allowedInferenceGeos == other.allowedInferenceGeos &&
            defaultInferenceGeo == other.defaultInferenceGeo &&
            workspaceGeo == other.workspaceGeo &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(allowedInferenceGeos, defaultInferenceGeo, workspaceGeo, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDataResidencyCreateConfig{allowedInferenceGeos=$allowedInferenceGeos, defaultInferenceGeo=$defaultInferenceGeo, workspaceGeo=$workspaceGeo, additionalProperties=$additionalProperties}"
}
