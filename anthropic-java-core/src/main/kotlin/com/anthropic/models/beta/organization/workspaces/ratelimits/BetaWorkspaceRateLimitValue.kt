package com.anthropic.models.beta.organization.workspaces.ratelimits

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

class BetaWorkspaceRateLimitValue
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val orgLimit: JsonField<Long>,
    private val source: JsonField<Source>,
    private val type: JsonField<String>,
    private val value: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("org_limit") @ExcludeMissing orgLimit: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
        @JsonProperty("value") @ExcludeMissing value: JsonField<Long> = JsonMissing.of(),
    ) : this(orgLimit, source, type, value, mutableMapOf())

    /**
     * The organization-level value for the same limiter type, for reference. `null` when the
     * organization has no limit configured for this limiter type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun orgLimit(): Optional<Long> = orgLimit.getOptional("org_limit")

    /**
     * Where `value` comes from. `organization` values are listed only when `include_inherited` is
     * `true`, and then `value` equals `org_limit`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

    /**
     * The limiter type (for example, `requests_per_minute` or `input_tokens_per_minute`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): String = type.getRequired("type")

    /**
     * The workspace's value for this limiter type: the workspace-level override when `source.type`
     * is `workspace`, otherwise the organization's value.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun value(): Long = value.getRequired("value")

    /**
     * Returns the raw JSON value of [orgLimit].
     *
     * Unlike [orgLimit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("org_limit") @ExcludeMissing fun _orgLimit(): JsonField<Long> = orgLimit

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

    /**
     * Returns the raw JSON value of [value].
     *
     * Unlike [value], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("value") @ExcludeMissing fun _value(): JsonField<Long> = value

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
         * Returns a mutable builder for constructing an instance of [BetaWorkspaceRateLimitValue].
         *
         * The following fields are required:
         * ```java
         * .orgLimit()
         * .source()
         * .type()
         * .value()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWorkspaceRateLimitValue]. */
    class Builder internal constructor() {

        private var orgLimit: JsonField<Long>? = null
        private var source: JsonField<Source>? = null
        private var type: JsonField<String>? = null
        private var value: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWorkspaceRateLimitValue: BetaWorkspaceRateLimitValue) = apply {
            orgLimit = betaWorkspaceRateLimitValue.orgLimit
            source = betaWorkspaceRateLimitValue.source
            type = betaWorkspaceRateLimitValue.type
            value = betaWorkspaceRateLimitValue.value
            additionalProperties = betaWorkspaceRateLimitValue.additionalProperties.toMutableMap()
        }

        /**
         * The organization-level value for the same limiter type, for reference. `null` when the
         * organization has no limit configured for this limiter type.
         */
        fun orgLimit(orgLimit: Long?) = orgLimit(JsonField.ofNullable(orgLimit))

        /**
         * Alias for [Builder.orgLimit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun orgLimit(orgLimit: Long) = orgLimit(orgLimit as Long?)

        /** Alias for calling [Builder.orgLimit] with `orgLimit.orElse(null)`. */
        fun orgLimit(orgLimit: Optional<Long>) = orgLimit(orgLimit.getOrNull())

        /**
         * Sets [Builder.orgLimit] to an arbitrary JSON value.
         *
         * You should usually call [Builder.orgLimit] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun orgLimit(orgLimit: JsonField<Long>) = apply { this.orgLimit = orgLimit }

        /**
         * Where `value` comes from. `organization` values are listed only when `include_inherited`
         * is `true`, and then `value` equals `org_limit`.
         */
        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofWorkspace(workspace)`. */
        fun source(workspace: BetaWorkspaceRateLimitWorkspaceSource) =
            source(Source.ofWorkspace(workspace))

        /** Alias for calling [source] with `Source.ofOrganization(organization)`. */
        fun source(organization: BetaWorkspaceRateLimitOrganizationSource) =
            source(Source.ofOrganization(organization))

        /** The limiter type (for example, `requests_per_minute` or `input_tokens_per_minute`). */
        fun type(type: String) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<String>) = apply { this.type = type }

        /**
         * The workspace's value for this limiter type: the workspace-level override when
         * `source.type` is `workspace`, otherwise the organization's value.
         */
        fun value(value: Long) = value(JsonField.of(value))

        /**
         * Sets [Builder.value] to an arbitrary JSON value.
         *
         * You should usually call [Builder.value] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun value(value: JsonField<Long>) = apply { this.value = value }

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
         * Returns an immutable instance of [BetaWorkspaceRateLimitValue].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .orgLimit()
         * .source()
         * .type()
         * .value()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWorkspaceRateLimitValue =
            BetaWorkspaceRateLimitValue(
                checkRequired("orgLimit", orgLimit),
                checkRequired("source", source),
                checkRequired("type", type),
                checkRequired("value", value),
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
    fun validate(): BetaWorkspaceRateLimitValue = apply {
        if (validated) {
            return@apply
        }

        orgLimit()
        source().validate()
        type()
        value()
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
        (if (orgLimit.asKnown().isPresent) 1 else 0) +
            (source.asKnown().getOrNull()?.validity() ?: 0) +
            (if (type.asKnown().isPresent) 1 else 0) +
            (if (value.asKnown().isPresent) 1 else 0)

    /**
     * Where `value` comes from. `organization` values are listed only when `include_inherited` is
     * `true`, and then `value` equals `org_limit`.
     */
    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val workspace: BetaWorkspaceRateLimitWorkspaceSource? = null,
        private val organization: BetaWorkspaceRateLimitOrganizationSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitWorkspace(
                        workspace: BetaWorkspaceRateLimitWorkspaceSource
                    ): Type = Type.WORKSPACE

                    override fun visitOrganization(
                        organization: BetaWorkspaceRateLimitOrganizationSource
                    ): Type = Type.ORGANIZATION

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun workspace(): Optional<BetaWorkspaceRateLimitWorkspaceSource> =
            Optional.ofNullable(workspace)

        fun organization(): Optional<BetaWorkspaceRateLimitOrganizationSource> =
            Optional.ofNullable(organization)

        fun isWorkspace(): Boolean = workspace != null

        fun isOrganization(): Boolean = organization != null

        fun asWorkspace(): BetaWorkspaceRateLimitWorkspaceSource = workspace.getOrThrow("workspace")

        fun asOrganization(): BetaWorkspaceRateLimitOrganizationSource =
            organization.getOrThrow("organization")

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
         * Optional<String> result = source.accept(new Source.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitWorkspace(BetaWorkspaceRateLimitWorkspaceSource workspace) {
         *         return Optional.of(workspace.toString());
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
                workspace != null -> visitor.visitWorkspace(workspace)
                organization != null -> visitor.visitOrganization(organization)
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
        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitWorkspace(workspace: BetaWorkspaceRateLimitWorkspaceSource) {
                        workspace.validate()
                    }

                    override fun visitOrganization(
                        organization: BetaWorkspaceRateLimitOrganizationSource
                    ) {
                        organization.validate()
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
                    override fun visitWorkspace(workspace: BetaWorkspaceRateLimitWorkspaceSource) =
                        workspace.validity()

                    override fun visitOrganization(
                        organization: BetaWorkspaceRateLimitOrganizationSource
                    ) = organization.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Source &&
                workspace == other.workspace &&
                organization == other.organization
        }

        override fun hashCode(): Int = Objects.hash(workspace, organization)

        override fun toString(): String =
            when {
                workspace != null -> "Source{workspace=$workspace}"
                organization != null -> "Source{organization=$organization}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic
            fun ofWorkspace(workspace: BetaWorkspaceRateLimitWorkspaceSource) =
                Source(workspace = workspace)

            @JvmStatic
            fun ofOrganization(organization: BetaWorkspaceRateLimitOrganizationSource) =
                Source(organization = organization)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitWorkspace(workspace: BetaWorkspaceRateLimitWorkspaceSource): T

            fun visitOrganization(organization: BetaWorkspaceRateLimitOrganizationSource): T

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
                    "workspace" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaWorkspaceRateLimitWorkspaceSource>(),
                            )
                            ?.let { Source(workspace = it, _json = json) } ?: Source(_json = json)
                    }
                    "organization" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaWorkspaceRateLimitOrganizationSource>(),
                            )
                            ?.let { Source(organization = it, _json = json) }
                            ?: Source(_json = json)
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
                    value.workspace != null -> generator.writeObject(value.workspace)
                    value.organization != null -> generator.writeObject(value.organization)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Source")
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

                @JvmField val WORKSPACE = of("workspace")

                @JvmField val ORGANIZATION = of("organization")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                WORKSPACE,
                ORGANIZATION,
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
                WORKSPACE,
                ORGANIZATION,
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
                    WORKSPACE -> Value.WORKSPACE
                    ORGANIZATION -> Value.ORGANIZATION
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
                    WORKSPACE -> Known.WORKSPACE
                    ORGANIZATION -> Known.ORGANIZATION
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

        return other is BetaWorkspaceRateLimitValue &&
            orgLimit == other.orgLimit &&
            source == other.source &&
            type == other.type &&
            value == other.value &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(orgLimit, source, type, value, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWorkspaceRateLimitValue{orgLimit=$orgLimit, source=$source, type=$type, value=$value, additionalProperties=$additionalProperties}"
}
