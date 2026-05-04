// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
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

/** Resolved default configuration for agent tools. */
class BetaManagedAgentsAgentToolsetDefaultConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val enabled: JsonField<Boolean>,
    private val permissionPolicy: JsonField<PermissionPolicy>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("enabled") @ExcludeMissing enabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("permission_policy")
        @ExcludeMissing
        permissionPolicy: JsonField<PermissionPolicy> = JsonMissing.of(),
    ) : this(enabled, permissionPolicy, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun enabled(): Boolean = enabled.getRequired("enabled")

    /**
     * Permission policy for tool execution.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun permissionPolicy(): PermissionPolicy = permissionPolicy.getRequired("permission_policy")

    /**
     * Returns the raw JSON value of [enabled].
     *
     * Unlike [enabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled") @ExcludeMissing fun _enabled(): JsonField<Boolean> = enabled

    /**
     * Returns the raw JSON value of [permissionPolicy].
     *
     * Unlike [permissionPolicy], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("permission_policy")
    @ExcludeMissing
    fun _permissionPolicy(): JsonField<PermissionPolicy> = permissionPolicy

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
         * [BetaManagedAgentsAgentToolsetDefaultConfig].
         *
         * The following fields are required:
         * ```java
         * .enabled()
         * .permissionPolicy()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolsetDefaultConfig]. */
    class Builder internal constructor() {

        private var enabled: JsonField<Boolean>? = null
        private var permissionPolicy: JsonField<PermissionPolicy>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolsetDefaultConfig: BetaManagedAgentsAgentToolsetDefaultConfig
        ) = apply {
            enabled = betaManagedAgentsAgentToolsetDefaultConfig.enabled
            permissionPolicy = betaManagedAgentsAgentToolsetDefaultConfig.permissionPolicy
            additionalProperties =
                betaManagedAgentsAgentToolsetDefaultConfig.additionalProperties.toMutableMap()
        }

        fun enabled(enabled: Boolean) = enabled(JsonField.of(enabled))

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

        /** Permission policy for tool execution. */
        fun permissionPolicy(permissionPolicy: PermissionPolicy) =
            permissionPolicy(JsonField.of(permissionPolicy))

        /**
         * Sets [Builder.permissionPolicy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.permissionPolicy] with a well-typed [PermissionPolicy]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun permissionPolicy(permissionPolicy: JsonField<PermissionPolicy>) = apply {
            this.permissionPolicy = permissionPolicy
        }

        /**
         * Alias for calling [permissionPolicy] with `PermissionPolicy.ofAlwaysAllow(alwaysAllow)`.
         */
        fun permissionPolicy(alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy) =
            permissionPolicy(PermissionPolicy.ofAlwaysAllow(alwaysAllow))

        /** Alias for calling [permissionPolicy] with `PermissionPolicy.ofAlwaysAsk(alwaysAsk)`. */
        fun permissionPolicy(alwaysAsk: BetaManagedAgentsAlwaysAskPolicy) =
            permissionPolicy(PermissionPolicy.ofAlwaysAsk(alwaysAsk))

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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolsetDefaultConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .enabled()
         * .permissionPolicy()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentToolsetDefaultConfig =
            BetaManagedAgentsAgentToolsetDefaultConfig(
                checkRequired("enabled", enabled),
                checkRequired("permissionPolicy", permissionPolicy),
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
    fun validate(): BetaManagedAgentsAgentToolsetDefaultConfig = apply {
        if (validated) {
            return@apply
        }

        enabled()
        permissionPolicy().validate()
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
        (if (enabled.asKnown().isPresent) 1 else 0) +
            (permissionPolicy.asKnown().getOrNull()?.validity() ?: 0)

    /** Permission policy for tool execution. */
    @JsonDeserialize(using = PermissionPolicy.Deserializer::class)
    @JsonSerialize(using = PermissionPolicy.Serializer::class)
    class PermissionPolicy
    private constructor(
        private val alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy? = null,
        private val alwaysAsk: BetaManagedAgentsAlwaysAskPolicy? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Tool calls are automatically approved without user confirmation. */
        fun alwaysAllow(): Optional<BetaManagedAgentsAlwaysAllowPolicy> =
            Optional.ofNullable(alwaysAllow)

        /** Tool calls require user confirmation before execution. */
        fun alwaysAsk(): Optional<BetaManagedAgentsAlwaysAskPolicy> = Optional.ofNullable(alwaysAsk)

        fun isAlwaysAllow(): Boolean = alwaysAllow != null

        fun isAlwaysAsk(): Boolean = alwaysAsk != null

        /** Tool calls are automatically approved without user confirmation. */
        fun asAlwaysAllow(): BetaManagedAgentsAlwaysAllowPolicy =
            alwaysAllow.getOrThrow("alwaysAllow")

        /** Tool calls require user confirmation before execution. */
        fun asAlwaysAsk(): BetaManagedAgentsAlwaysAskPolicy = alwaysAsk.getOrThrow("alwaysAsk")

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
         * Optional<String> result = permissionPolicy.accept(new PermissionPolicy.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAlwaysAllow(BetaManagedAgentsAlwaysAllowPolicy alwaysAllow) {
         *         return Optional.of(alwaysAllow.toString());
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
                alwaysAllow != null -> visitor.visitAlwaysAllow(alwaysAllow)
                alwaysAsk != null -> visitor.visitAlwaysAsk(alwaysAsk)
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
        fun validate(): PermissionPolicy = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAlwaysAllow(alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy) {
                        alwaysAllow.validate()
                    }

                    override fun visitAlwaysAsk(alwaysAsk: BetaManagedAgentsAlwaysAskPolicy) {
                        alwaysAsk.validate()
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
                    override fun visitAlwaysAllow(alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy) =
                        alwaysAllow.validity()

                    override fun visitAlwaysAsk(alwaysAsk: BetaManagedAgentsAlwaysAskPolicy) =
                        alwaysAsk.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is PermissionPolicy &&
                alwaysAllow == other.alwaysAllow &&
                alwaysAsk == other.alwaysAsk
        }

        override fun hashCode(): Int = Objects.hash(alwaysAllow, alwaysAsk)

        override fun toString(): String =
            when {
                alwaysAllow != null -> "PermissionPolicy{alwaysAllow=$alwaysAllow}"
                alwaysAsk != null -> "PermissionPolicy{alwaysAsk=$alwaysAsk}"
                _json != null -> "PermissionPolicy{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid PermissionPolicy")
            }

        companion object {

            /** Tool calls are automatically approved without user confirmation. */
            @JvmStatic
            fun ofAlwaysAllow(alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy) =
                PermissionPolicy(alwaysAllow = alwaysAllow)

            /** Tool calls require user confirmation before execution. */
            @JvmStatic
            fun ofAlwaysAsk(alwaysAsk: BetaManagedAgentsAlwaysAskPolicy) =
                PermissionPolicy(alwaysAsk = alwaysAsk)
        }

        /**
         * An interface that defines how to map each variant of [PermissionPolicy] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            /** Tool calls are automatically approved without user confirmation. */
            fun visitAlwaysAllow(alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy): T

            /** Tool calls require user confirmation before execution. */
            fun visitAlwaysAsk(alwaysAsk: BetaManagedAgentsAlwaysAskPolicy): T

            /**
             * Maps an unknown variant of [PermissionPolicy] to a value of type [T].
             *
             * An instance of [PermissionPolicy] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown PermissionPolicy: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<PermissionPolicy>(PermissionPolicy::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): PermissionPolicy {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "always_allow" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsAlwaysAllowPolicy>(),
                            )
                            ?.let { PermissionPolicy(alwaysAllow = it, _json = json) }
                            ?: PermissionPolicy(_json = json)
                    }
                    "always_ask" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsAlwaysAskPolicy>(),
                            )
                            ?.let { PermissionPolicy(alwaysAsk = it, _json = json) }
                            ?: PermissionPolicy(_json = json)
                    }
                }

                return PermissionPolicy(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<PermissionPolicy>(PermissionPolicy::class) {

            override fun serialize(
                value: PermissionPolicy,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.alwaysAllow != null -> generator.writeObject(value.alwaysAllow)
                    value.alwaysAsk != null -> generator.writeObject(value.alwaysAsk)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid PermissionPolicy")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolsetDefaultConfig &&
            enabled == other.enabled &&
            permissionPolicy == other.permissionPolicy &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(enabled, permissionPolicy, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolsetDefaultConfig{enabled=$enabled, permissionPolicy=$permissionPolicy, additionalProperties=$additionalProperties}"
}
