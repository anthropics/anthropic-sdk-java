// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * CMEK external key config belonging to the caller's organization.
 *
 * Configs are organization-scoped. Workspaces attach to a config; once any workspace references it,
 * the provider fields become effectively immutable (existing encrypted data needs the config for
 * decrypt).
 */
class BetaExternalKey
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val attachment: JsonField<Attachment>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val displayName: JsonField<String>,
    private val geo: JsonField<String>,
    private val providerConfig: JsonField<ProviderConfig>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("attachment")
        @ExcludeMissing
        attachment: JsonField<Attachment> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("geo") @ExcludeMissing geo: JsonField<String> = JsonMissing.of(),
        @JsonProperty("provider_config")
        @ExcludeMissing
        providerConfig: JsonField<ProviderConfig> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(
        id,
        attachment,
        createdAt,
        displayName,
        geo,
        providerConfig,
        type,
        updatedAt,
        mutableMapOf(),
    )

    /**
     * Identifier of the external key config. A tagged ID prefixed `ekey_`, or — for organizations
     * on the Claude Platform on AWS — the AWS KMS key ARN.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Whether any workspace uses this config to encrypt its data — counting live and archived
     * workspaces (an archived workspace's data remains encrypted under the config), excluding
     * deleted ones. Only an attached config is used by the encryption path; an `unattached` config
     * is inert and can be deleted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun attachment(): Attachment = attachment.getRequired("attachment")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Human-friendly display name. Null if none was set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = displayName.getOptional("display_name")

    /**
     * Data residency geo. Selects which regional validator handles this key's encrypt/decrypt
     * roundtrips.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun geo(): String = geo.getRequired("geo")

    /**
     * KMS provider identity and auth coordinates.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun providerConfig(): ProviderConfig = providerConfig.getRequired("provider_config")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("external_key")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [attachment].
     *
     * Unlike [attachment], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("attachment")
    @ExcludeMissing
    fun _attachment(): JsonField<Attachment> = attachment

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_name")
    @ExcludeMissing
    fun _displayName(): JsonField<String> = displayName

    /**
     * Returns the raw JSON value of [geo].
     *
     * Unlike [geo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("geo") @ExcludeMissing fun _geo(): JsonField<String> = geo

    /**
     * Returns the raw JSON value of [providerConfig].
     *
     * Unlike [providerConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("provider_config")
    @ExcludeMissing
    fun _providerConfig(): JsonField<ProviderConfig> = providerConfig

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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
         * Returns a mutable builder for constructing an instance of [BetaExternalKey].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .attachment()
         * .createdAt()
         * .displayName()
         * .geo()
         * .providerConfig()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaExternalKey]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var attachment: JsonField<Attachment>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var displayName: JsonField<String>? = null
        private var geo: JsonField<String>? = null
        private var providerConfig: JsonField<ProviderConfig>? = null
        private var type: JsonValue = JsonValue.from("external_key")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaExternalKey: BetaExternalKey) = apply {
            id = betaExternalKey.id
            attachment = betaExternalKey.attachment
            createdAt = betaExternalKey.createdAt
            displayName = betaExternalKey.displayName
            geo = betaExternalKey.geo
            providerConfig = betaExternalKey.providerConfig
            type = betaExternalKey.type
            updatedAt = betaExternalKey.updatedAt
            additionalProperties = betaExternalKey.additionalProperties.toMutableMap()
        }

        /**
         * Identifier of the external key config. A tagged ID prefixed `ekey_`, or — for
         * organizations on the Claude Platform on AWS — the AWS KMS key ARN.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Whether any workspace uses this config to encrypt its data — counting live and archived
         * workspaces (an archived workspace's data remains encrypted under the config), excluding
         * deleted ones. Only an attached config is used by the encryption path; an `unattached`
         * config is inert and can be deleted.
         */
        fun attachment(attachment: Attachment) = attachment(JsonField.of(attachment))

        /**
         * Sets [Builder.attachment] to an arbitrary JSON value.
         *
         * You should usually call [Builder.attachment] with a well-typed [Attachment] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun attachment(attachment: JsonField<Attachment>) = apply { this.attachment = attachment }

        /** Alias for calling [attachment] with `Attachment.ofAttached(attached)`. */
        fun attachment(attached: BetaExternalKeyAttachedAttachment) =
            attachment(Attachment.ofAttached(attached))

        /** Alias for calling [attachment] with `Attachment.ofUnattached(unattached)`. */
        fun attachment(unattached: BetaExternalKeyUnattachedAttachment) =
            attachment(Attachment.ofUnattached(unattached))

        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Human-friendly display name. Null if none was set. */
        fun displayName(displayName: String?) = displayName(JsonField.ofNullable(displayName))

        /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
        fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

        /**
         * Sets [Builder.displayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayName(displayName: JsonField<String>) = apply { this.displayName = displayName }

        /**
         * Data residency geo. Selects which regional validator handles this key's encrypt/decrypt
         * roundtrips.
         */
        fun geo(geo: String) = geo(JsonField.of(geo))

        /**
         * Sets [Builder.geo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.geo] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun geo(geo: JsonField<String>) = apply { this.geo = geo }

        /** KMS provider identity and auth coordinates. */
        fun providerConfig(providerConfig: ProviderConfig) =
            providerConfig(JsonField.of(providerConfig))

        /**
         * Sets [Builder.providerConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.providerConfig] with a well-typed [ProviderConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun providerConfig(providerConfig: JsonField<ProviderConfig>) = apply {
            this.providerConfig = providerConfig
        }

        /** Alias for calling [providerConfig] with `ProviderConfig.ofAws(aws)`. */
        fun providerConfig(aws: BetaAwsExternalKeyConfig) =
            providerConfig(ProviderConfig.ofAws(aws))

        /**
         * Alias for calling [providerConfig] with the following:
         * ```java
         * BetaAwsExternalKeyConfig.builder()
         *     .kmsArn(kmsArn)
         *     .build()
         * ```
         */
        fun awsProviderConfig(kmsArn: String) =
            providerConfig(BetaAwsExternalKeyConfig.builder().kmsArn(kmsArn).build())

        /** Alias for calling [providerConfig] with `ProviderConfig.ofGcp(gcp)`. */
        fun providerConfig(gcp: BetaGcpExternalKeyConfig) =
            providerConfig(ProviderConfig.ofGcp(gcp))

        /**
         * Alias for calling [providerConfig] with the following:
         * ```java
         * BetaGcpExternalKeyConfig.builder()
         *     .keyName(keyName)
         *     .build()
         * ```
         */
        fun gcpProviderConfig(keyName: String) =
            providerConfig(BetaGcpExternalKeyConfig.builder().keyName(keyName).build())

        /** Alias for calling [providerConfig] with `ProviderConfig.ofAzure(azure)`. */
        fun providerConfig(azure: BetaAzureExternalKeyConfig) =
            providerConfig(ProviderConfig.ofAzure(azure))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("external_key")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

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
         * Returns an immutable instance of [BetaExternalKey].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .attachment()
         * .createdAt()
         * .displayName()
         * .geo()
         * .providerConfig()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaExternalKey =
            BetaExternalKey(
                checkRequired("id", id),
                checkRequired("attachment", attachment),
                checkRequired("createdAt", createdAt),
                checkRequired("displayName", displayName),
                checkRequired("geo", geo),
                checkRequired("providerConfig", providerConfig),
                type,
                checkRequired("updatedAt", updatedAt),
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
    fun validate(): BetaExternalKey = apply {
        if (validated) {
            return@apply
        }

        id()
        attachment().validate()
        createdAt()
        displayName()
        geo()
        providerConfig().validate()
        _type().let {
            if (it != JsonValue.from("external_key")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (attachment.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (displayName.asKnown().isPresent) 1 else 0) +
            (if (geo.asKnown().isPresent) 1 else 0) +
            (providerConfig.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("external_key")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    /**
     * Whether any workspace uses this config to encrypt its data — counting live and archived
     * workspaces (an archived workspace's data remains encrypted under the config), excluding
     * deleted ones. Only an attached config is used by the encryption path; an `unattached` config
     * is inert and can be deleted.
     */
    @JsonDeserialize(using = Attachment.Deserializer::class)
    @JsonSerialize(using = Attachment.Serializer::class)
    class Attachment
    private constructor(
        private val attached: BetaExternalKeyAttachedAttachment? = null,
        private val unattached: BetaExternalKeyUnattachedAttachment? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAttached(attached: BetaExternalKeyAttachedAttachment): Type =
                        Type.ATTACHED

                    override fun visitUnattached(
                        unattached: BetaExternalKeyUnattachedAttachment
                    ): Type = Type.UNATTACHED

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun attached(): Optional<BetaExternalKeyAttachedAttachment> = Optional.ofNullable(attached)

        fun unattached(): Optional<BetaExternalKeyUnattachedAttachment> =
            Optional.ofNullable(unattached)

        fun isAttached(): Boolean = attached != null

        fun isUnattached(): Boolean = unattached != null

        fun asAttached(): BetaExternalKeyAttachedAttachment = attached.getOrThrow("attached")

        fun asUnattached(): BetaExternalKeyUnattachedAttachment =
            unattached.getOrThrow("unattached")

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
         * Optional<String> result = attachment.accept(new Attachment.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAttached(BetaExternalKeyAttachedAttachment attached) {
         *         return Optional.of(attached.toString());
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
                attached != null -> visitor.visitAttached(attached)
                unattached != null -> visitor.visitUnattached(unattached)
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
        fun validate(): Attachment = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAttached(attached: BetaExternalKeyAttachedAttachment) {
                        attached.validate()
                    }

                    override fun visitUnattached(unattached: BetaExternalKeyUnattachedAttachment) {
                        unattached.validate()
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
                    override fun visitAttached(attached: BetaExternalKeyAttachedAttachment) =
                        attached.validity()

                    override fun visitUnattached(unattached: BetaExternalKeyUnattachedAttachment) =
                        unattached.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Attachment &&
                attached == other.attached &&
                unattached == other.unattached
        }

        override fun hashCode(): Int = Objects.hash(attached, unattached)

        override fun toString(): String =
            when {
                attached != null -> "Attachment{attached=$attached}"
                unattached != null -> "Attachment{unattached=$unattached}"
                _json != null -> "Attachment{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Attachment")
            }

        companion object {

            @JvmStatic
            fun ofAttached(attached: BetaExternalKeyAttachedAttachment) =
                Attachment(attached = attached)

            @JvmStatic
            fun ofUnattached(unattached: BetaExternalKeyUnattachedAttachment) =
                Attachment(unattached = unattached)
        }

        /**
         * An interface that defines how to map each variant of [Attachment] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitAttached(attached: BetaExternalKeyAttachedAttachment): T

            fun visitUnattached(unattached: BetaExternalKeyUnattachedAttachment): T

            /**
             * Maps an unknown variant of [Attachment] to a value of type [T].
             *
             * An instance of [Attachment] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Attachment: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Attachment>(Attachment::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Attachment {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "attached" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaExternalKeyAttachedAttachment>(),
                            )
                            ?.let { Attachment(attached = it, _json = json) }
                            ?: Attachment(_json = json)
                    }
                    "unattached" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaExternalKeyUnattachedAttachment>(),
                            )
                            ?.let { Attachment(unattached = it, _json = json) }
                            ?: Attachment(_json = json)
                    }
                }

                return Attachment(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Attachment>(Attachment::class) {

            override fun serialize(
                value: Attachment,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.attached != null -> generator.writeObject(value.attached)
                    value.unattached != null -> generator.writeObject(value.unattached)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Attachment")
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

                @JvmField val ATTACHED = of("attached")

                @JvmField val UNATTACHED = of("unattached")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ATTACHED,
                UNATTACHED,
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
                ATTACHED,
                UNATTACHED,
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
                    ATTACHED -> Value.ATTACHED
                    UNATTACHED -> Value.UNATTACHED
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
                    ATTACHED -> Known.ATTACHED
                    UNATTACHED -> Known.UNATTACHED
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

    /** KMS provider identity and auth coordinates. */
    @JsonDeserialize(using = ProviderConfig.Deserializer::class)
    @JsonSerialize(using = ProviderConfig.Serializer::class)
    class ProviderConfig
    private constructor(
        private val aws: BetaAwsExternalKeyConfig? = null,
        private val gcp: BetaGcpExternalKeyConfig? = null,
        private val azure: BetaAzureExternalKeyConfig? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAws(aws: BetaAwsExternalKeyConfig): Type = Type.AWS

                    override fun visitGcp(gcp: BetaGcpExternalKeyConfig): Type = Type.GCP

                    override fun visitAzure(azure: BetaAzureExternalKeyConfig): Type = Type.AZURE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun keyName(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitAws(aws: BetaAwsExternalKeyConfig): Optional<String> =
                        Optional.empty()

                    override fun visitGcp(gcp: BetaGcpExternalKeyConfig): Optional<String> =
                        Optional.of(gcp.keyName())

                    override fun visitAzure(azure: BetaAzureExternalKeyConfig): Optional<String> =
                        Optional.of(azure.keyName())

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("key_name").asKnown()
                }
            )

        fun aws(): Optional<BetaAwsExternalKeyConfig> = Optional.ofNullable(aws)

        fun gcp(): Optional<BetaGcpExternalKeyConfig> = Optional.ofNullable(gcp)

        fun azure(): Optional<BetaAzureExternalKeyConfig> = Optional.ofNullable(azure)

        fun isAws(): Boolean = aws != null

        fun isGcp(): Boolean = gcp != null

        fun isAzure(): Boolean = azure != null

        fun asAws(): BetaAwsExternalKeyConfig = aws.getOrThrow("aws")

        fun asGcp(): BetaGcpExternalKeyConfig = gcp.getOrThrow("gcp")

        fun asAzure(): BetaAzureExternalKeyConfig = azure.getOrThrow("azure")

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
         * Optional<String> result = providerConfig.accept(new ProviderConfig.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAws(BetaAwsExternalKeyConfig aws) {
         *         return Optional.of(aws.toString());
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
                aws != null -> visitor.visitAws(aws)
                gcp != null -> visitor.visitGcp(gcp)
                azure != null -> visitor.visitAzure(azure)
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
        fun validate(): ProviderConfig = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAws(aws: BetaAwsExternalKeyConfig) {
                        aws.validate()
                    }

                    override fun visitGcp(gcp: BetaGcpExternalKeyConfig) {
                        gcp.validate()
                    }

                    override fun visitAzure(azure: BetaAzureExternalKeyConfig) {
                        azure.validate()
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
                    override fun visitAws(aws: BetaAwsExternalKeyConfig) = aws.validity()

                    override fun visitGcp(gcp: BetaGcpExternalKeyConfig) = gcp.validity()

                    override fun visitAzure(azure: BetaAzureExternalKeyConfig) = azure.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ProviderConfig &&
                aws == other.aws &&
                gcp == other.gcp &&
                azure == other.azure
        }

        override fun hashCode(): Int = Objects.hash(aws, gcp, azure)

        override fun toString(): String =
            when {
                aws != null -> "ProviderConfig{aws=$aws}"
                gcp != null -> "ProviderConfig{gcp=$gcp}"
                azure != null -> "ProviderConfig{azure=$azure}"
                _json != null -> "ProviderConfig{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ProviderConfig")
            }

        companion object {

            @JvmStatic fun ofAws(aws: BetaAwsExternalKeyConfig) = ProviderConfig(aws = aws)

            /**
             * Returns an immutable instance of [ProviderConfig] whose [ofAws] variant is built from
             * the given required [kmsArn].
             */
            @JvmStatic fun ofAws(kmsArn: String) = ofAws(BetaAwsExternalKeyConfig.of(kmsArn))

            @JvmStatic fun ofGcp(gcp: BetaGcpExternalKeyConfig) = ProviderConfig(gcp = gcp)

            /**
             * Returns an immutable instance of [ProviderConfig] whose [ofGcp] variant is built from
             * the given required [keyName].
             */
            @JvmStatic fun ofGcp(keyName: String) = ofGcp(BetaGcpExternalKeyConfig.of(keyName))

            @JvmStatic
            fun ofAzure(azure: BetaAzureExternalKeyConfig) = ProviderConfig(azure = azure)
        }

        /**
         * An interface that defines how to map each variant of [ProviderConfig] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitAws(aws: BetaAwsExternalKeyConfig): T

            fun visitGcp(gcp: BetaGcpExternalKeyConfig): T

            fun visitAzure(azure: BetaAzureExternalKeyConfig): T

            /**
             * Maps an unknown variant of [ProviderConfig] to a value of type [T].
             *
             * An instance of [ProviderConfig] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ProviderConfig: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<ProviderConfig>(ProviderConfig::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ProviderConfig {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "aws" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaAwsExternalKeyConfig>())
                            ?.let { ProviderConfig(aws = it, _json = json) }
                            ?: ProviderConfig(_json = json)
                    }
                    "gcp" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaGcpExternalKeyConfig>())
                            ?.let { ProviderConfig(gcp = it, _json = json) }
                            ?: ProviderConfig(_json = json)
                    }
                    "azure" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaAzureExternalKeyConfig>())
                            ?.let { ProviderConfig(azure = it, _json = json) }
                            ?: ProviderConfig(_json = json)
                    }
                }

                return ProviderConfig(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<ProviderConfig>(ProviderConfig::class) {

            override fun serialize(
                value: ProviderConfig,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.aws != null -> generator.writeObject(value.aws)
                    value.gcp != null -> generator.writeObject(value.gcp)
                    value.azure != null -> generator.writeObject(value.azure)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ProviderConfig")
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

                @JvmField val AWS = of("aws")

                @JvmField val GCP = of("gcp")

                @JvmField val AZURE = of("azure")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                AWS,
                GCP,
                AZURE,
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
                AWS,
                GCP,
                AZURE,
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
                    AWS -> Value.AWS
                    GCP -> Value.GCP
                    AZURE -> Value.AZURE
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
                    AWS -> Known.AWS
                    GCP -> Known.GCP
                    AZURE -> Known.AZURE
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

        return other is BetaExternalKey &&
            id == other.id &&
            attachment == other.attachment &&
            createdAt == other.createdAt &&
            displayName == other.displayName &&
            geo == other.geo &&
            providerConfig == other.providerConfig &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            attachment,
            createdAt,
            displayName,
            geo,
            providerConfig,
            type,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaExternalKey{id=$id, attachment=$attachment, createdAt=$createdAt, displayName=$displayName, geo=$geo, providerConfig=$providerConfig, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
