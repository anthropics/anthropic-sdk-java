// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
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

/** Configuration for the web_fetch tool. */
class BetaManagedAgentsWebFetchToolConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val enabled: JsonField<Boolean>,
    private val name: JsonValue,
    private val permissionPolicy: JsonField<PermissionPolicy>,
    private val type: JsonValue,
    private val allowedDomains: JsonField<List<String>>,
    private val blockedDomains: JsonField<List<String>>,
    private val maxContentTokens: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("enabled") @ExcludeMissing enabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("permission_policy")
        @ExcludeMissing
        permissionPolicy: JsonField<PermissionPolicy> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("allowed_domains")
        @ExcludeMissing
        allowedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("blocked_domains")
        @ExcludeMissing
        blockedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("max_content_tokens")
        @ExcludeMissing
        maxContentTokens: JsonField<Int> = JsonMissing.of(),
    ) : this(
        enabled,
        name,
        permissionPolicy,
        type,
        allowedDomains,
        blockedDomains,
        maxContentTokens,
        mutableMapOf(),
    )

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun enabled(): Boolean = enabled.getRequired("enabled")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_fetch")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    /**
     * Permission policy for tool execution.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun permissionPolicy(): PermissionPolicy = permissionPolicy.getRequired("permission_policy")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_fetch")
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
    fun allowedDomains(): Optional<List<String>> = allowedDomains.getOptional("allowed_domains")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun blockedDomains(): Optional<List<String>> = blockedDomains.getOptional("blocked_domains")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxContentTokens(): Optional<Int> = maxContentTokens.getOptional("max_content_tokens")

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

    /**
     * Returns the raw JSON value of [allowedDomains].
     *
     * Unlike [allowedDomains], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_domains")
    @ExcludeMissing
    fun _allowedDomains(): JsonField<List<String>> = allowedDomains

    /**
     * Returns the raw JSON value of [blockedDomains].
     *
     * Unlike [blockedDomains], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("blocked_domains")
    @ExcludeMissing
    fun _blockedDomains(): JsonField<List<String>> = blockedDomains

    /**
     * Returns the raw JSON value of [maxContentTokens].
     *
     * Unlike [maxContentTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("max_content_tokens")
    @ExcludeMissing
    fun _maxContentTokens(): JsonField<Int> = maxContentTokens

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
         * [BetaManagedAgentsWebFetchToolConfig].
         *
         * The following fields are required:
         * ```java
         * .enabled()
         * .permissionPolicy()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWebFetchToolConfig]. */
    class Builder internal constructor() {

        private var enabled: JsonField<Boolean>? = null
        private var name: JsonValue = JsonValue.from("web_fetch")
        private var permissionPolicy: JsonField<PermissionPolicy>? = null
        private var type: JsonValue = JsonValue.from("web_fetch")
        private var allowedDomains: JsonField<MutableList<String>>? = null
        private var blockedDomains: JsonField<MutableList<String>>? = null
        private var maxContentTokens: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWebFetchToolConfig: BetaManagedAgentsWebFetchToolConfig
        ) = apply {
            enabled = betaManagedAgentsWebFetchToolConfig.enabled
            name = betaManagedAgentsWebFetchToolConfig.name
            permissionPolicy = betaManagedAgentsWebFetchToolConfig.permissionPolicy
            type = betaManagedAgentsWebFetchToolConfig.type
            allowedDomains =
                betaManagedAgentsWebFetchToolConfig.allowedDomains
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            blockedDomains =
                betaManagedAgentsWebFetchToolConfig.blockedDomains
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            maxContentTokens = betaManagedAgentsWebFetchToolConfig.maxContentTokens
            additionalProperties =
                betaManagedAgentsWebFetchToolConfig.additionalProperties.toMutableMap()
        }

        fun enabled(enabled: Boolean) = enabled(JsonField.of(enabled))

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_fetch")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun name(name: JsonValue) = apply { this.name = name }

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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_fetch")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun allowedDomains(allowedDomains: List<String>) =
            allowedDomains(JsonField.of(allowedDomains))

        /**
         * Sets [Builder.allowedDomains] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedDomains] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowedDomains(allowedDomains: JsonField<List<String>>) = apply {
            this.allowedDomains = allowedDomains.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [allowedDomains].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedDomain(allowedDomain: String) = apply {
            allowedDomains =
                (allowedDomains ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedDomains", it).add(allowedDomain)
                }
        }

        fun blockedDomains(blockedDomains: List<String>) =
            blockedDomains(JsonField.of(blockedDomains))

        /**
         * Sets [Builder.blockedDomains] to an arbitrary JSON value.
         *
         * You should usually call [Builder.blockedDomains] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun blockedDomains(blockedDomains: JsonField<List<String>>) = apply {
            this.blockedDomains = blockedDomains.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [blockedDomains].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBlockedDomain(blockedDomain: String) = apply {
            blockedDomains =
                (blockedDomains ?: JsonField.of(mutableListOf())).also {
                    checkKnown("blockedDomains", it).add(blockedDomain)
                }
        }

        fun maxContentTokens(maxContentTokens: Int?) =
            maxContentTokens(JsonField.ofNullable(maxContentTokens))

        /**
         * Alias for [Builder.maxContentTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxContentTokens(maxContentTokens: Int) = maxContentTokens(maxContentTokens as Int?)

        /** Alias for calling [Builder.maxContentTokens] with `maxContentTokens.orElse(null)`. */
        fun maxContentTokens(maxContentTokens: Optional<Int>) =
            maxContentTokens(maxContentTokens.getOrNull())

        /**
         * Sets [Builder.maxContentTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxContentTokens] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun maxContentTokens(maxContentTokens: JsonField<Int>) = apply {
            this.maxContentTokens = maxContentTokens
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
         * Returns an immutable instance of [BetaManagedAgentsWebFetchToolConfig].
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
        fun build(): BetaManagedAgentsWebFetchToolConfig =
            BetaManagedAgentsWebFetchToolConfig(
                checkRequired("enabled", enabled),
                name,
                checkRequired("permissionPolicy", permissionPolicy),
                type,
                (allowedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                (blockedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                maxContentTokens,
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
    fun validate(): BetaManagedAgentsWebFetchToolConfig = apply {
        if (validated) {
            return@apply
        }

        enabled()
        _name().let {
            if (it != JsonValue.from("web_fetch")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        permissionPolicy().validate()
        _type().let {
            if (it != JsonValue.from("web_fetch")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        allowedDomains()
        blockedDomains()
        maxContentTokens()
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
            name.let { if (it == JsonValue.from("web_fetch")) 1 else 0 } +
            (permissionPolicy.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("web_fetch")) 1 else 0 } +
            (allowedDomains.asKnown().getOrNull()?.size ?: 0) +
            (blockedDomains.asKnown().getOrNull()?.size ?: 0) +
            (if (maxContentTokens.asKnown().isPresent) 1 else 0)

    /** Permission policy for tool execution. */
    @JsonDeserialize(using = PermissionPolicy.Deserializer::class)
    @JsonSerialize(using = PermissionPolicy.Serializer::class)
    class PermissionPolicy
    private constructor(
        private val alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy? = null,
        private val alwaysAsk: BetaManagedAgentsAlwaysAskPolicy? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAlwaysAllow(
                        alwaysAllow: BetaManagedAgentsAlwaysAllowPolicy
                    ): Type = Type.ALWAYS_ALLOW

                    override fun visitAlwaysAsk(alwaysAsk: BetaManagedAgentsAlwaysAskPolicy): Type =
                        Type.ALWAYS_ASK

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

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

            /**
             * Returns an immutable instance of [PermissionPolicy] whose [ofAlwaysAllow] variant is
             * built from the given required [type].
             */
            @JvmStatic
            fun ofAlwaysAllow(type: BetaManagedAgentsAlwaysAllowPolicy.Type) =
                ofAlwaysAllow(BetaManagedAgentsAlwaysAllowPolicy.of(type))

            /** Tool calls require user confirmation before execution. */
            @JvmStatic
            fun ofAlwaysAsk(alwaysAsk: BetaManagedAgentsAlwaysAskPolicy) =
                PermissionPolicy(alwaysAsk = alwaysAsk)

            /**
             * Returns an immutable instance of [PermissionPolicy] whose [ofAlwaysAsk] variant is
             * built from the given required [type].
             */
            @JvmStatic
            fun ofAlwaysAsk(type: BetaManagedAgentsAlwaysAskPolicy.Type) =
                ofAlwaysAsk(BetaManagedAgentsAlwaysAskPolicy.of(type))
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

                @JvmField val ALWAYS_ALLOW = of("always_allow")

                @JvmField val ALWAYS_ASK = of("always_ask")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ALWAYS_ALLOW,
                ALWAYS_ASK,
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
                ALWAYS_ALLOW,
                ALWAYS_ASK,
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
                    ALWAYS_ALLOW -> Value.ALWAYS_ALLOW
                    ALWAYS_ASK -> Value.ALWAYS_ASK
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
                    ALWAYS_ALLOW -> Known.ALWAYS_ALLOW
                    ALWAYS_ASK -> Known.ALWAYS_ASK
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

        return other is BetaManagedAgentsWebFetchToolConfig &&
            enabled == other.enabled &&
            name == other.name &&
            permissionPolicy == other.permissionPolicy &&
            type == other.type &&
            allowedDomains == other.allowedDomains &&
            blockedDomains == other.blockedDomains &&
            maxContentTokens == other.maxContentTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            enabled,
            name,
            permissionPolicy,
            type,
            allowedDomains,
            blockedDomains,
            maxContentTokens,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWebFetchToolConfig{enabled=$enabled, name=$name, permissionPolicy=$permissionPolicy, type=$type, allowedDomains=$allowedDomains, blockedDomains=$blockedDomains, maxContentTokens=$maxContentTokens, additionalProperties=$additionalProperties}"
}
