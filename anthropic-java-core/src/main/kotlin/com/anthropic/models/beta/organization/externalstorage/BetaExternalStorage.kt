package com.anthropic.models.beta.organization.externalstorage

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
 * A storage bucket in the organization's own cloud account. Anthropic writes inference data to it
 * from each Workspace it is attached to.
 */
class BetaExternalStorage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val displayName: JsonField<String>,
    private val providerConfig: JsonField<ProviderConfig>,
    private val setupTemplateVersion: JsonField<String>,
    private val status: JsonField<Status>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val userProfileId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("provider_config")
        @ExcludeMissing
        providerConfig: JsonField<ProviderConfig> = JsonMissing.of(),
        @JsonProperty("setup_template_version")
        @ExcludeMissing
        setupTemplateVersion: JsonField<String> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("user_profile_id")
        @ExcludeMissing
        userProfileId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        displayName,
        providerConfig,
        setupTemplateVersion,
        status,
        type,
        updatedAt,
        userProfileId,
        mutableMapOf(),
    )

    /**
     * The External Storage ID, which starts with `estrg_`. Through Claude Platform on AWS, storage
     * whose `provider_config.type` is `aws` has its bucket ARN as its ID instead:
     * `arn:<partition>:s3:::<bucket_name>`, where `<partition>` is the partition of the bucket's
     * region.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

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
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun providerConfig(): ProviderConfig = providerConfig.getRequired("provider_config")

    /**
     * The version of Anthropic's setup template whose IAM role names this storage was found to use
     * at activation. Null if no version was recorded, as for storage not yet activated or not on
     * S3.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun setupTemplateVersion(): Optional<String> =
        setupTemplateVersion.getOptional("setup_template_version")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("external_storage")
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
     * The user profile the storage is bound to, or null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userProfileId(): Optional<String> = userProfileId.getOptional("user_profile_id")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

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
     * Returns the raw JSON value of [providerConfig].
     *
     * Unlike [providerConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("provider_config")
    @ExcludeMissing
    fun _providerConfig(): JsonField<ProviderConfig> = providerConfig

    /**
     * Returns the raw JSON value of [setupTemplateVersion].
     *
     * Unlike [setupTemplateVersion], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("setup_template_version")
    @ExcludeMissing
    fun _setupTemplateVersion(): JsonField<String> = setupTemplateVersion

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [userProfileId].
     *
     * Unlike [userProfileId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_profile_id")
    @ExcludeMissing
    fun _userProfileId(): JsonField<String> = userProfileId

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
         * Returns a mutable builder for constructing an instance of [BetaExternalStorage].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .displayName()
         * .providerConfig()
         * .setupTemplateVersion()
         * .status()
         * .updatedAt()
         * .userProfileId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaExternalStorage]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var displayName: JsonField<String>? = null
        private var providerConfig: JsonField<ProviderConfig>? = null
        private var setupTemplateVersion: JsonField<String>? = null
        private var status: JsonField<Status>? = null
        private var type: JsonValue = JsonValue.from("external_storage")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var userProfileId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaExternalStorage: BetaExternalStorage) = apply {
            id = betaExternalStorage.id
            createdAt = betaExternalStorage.createdAt
            displayName = betaExternalStorage.displayName
            providerConfig = betaExternalStorage.providerConfig
            setupTemplateVersion = betaExternalStorage.setupTemplateVersion
            status = betaExternalStorage.status
            type = betaExternalStorage.type
            updatedAt = betaExternalStorage.updatedAt
            userProfileId = betaExternalStorage.userProfileId
            additionalProperties = betaExternalStorage.additionalProperties.toMutableMap()
        }

        /**
         * The External Storage ID, which starts with `estrg_`. Through Claude Platform on AWS,
         * storage whose `provider_config.type` is `aws` has its bucket ARN as its ID instead:
         * `arn:<partition>:s3:::<bucket_name>`, where `<partition>` is the partition of the
         * bucket's region.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

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
        fun providerConfig(aws: BetaAwsExternalStorageConfig) =
            providerConfig(ProviderConfig.ofAws(aws))

        /** Alias for calling [providerConfig] with `ProviderConfig.ofGcp(gcp)`. */
        fun providerConfig(gcp: BetaGcpExternalStorageConfig) =
            providerConfig(ProviderConfig.ofGcp(gcp))

        /**
         * The version of Anthropic's setup template whose IAM role names this storage was found to
         * use at activation. Null if no version was recorded, as for storage not yet activated or
         * not on S3.
         */
        fun setupTemplateVersion(setupTemplateVersion: String?) =
            setupTemplateVersion(JsonField.ofNullable(setupTemplateVersion))

        /**
         * Alias for calling [Builder.setupTemplateVersion] with
         * `setupTemplateVersion.orElse(null)`.
         */
        fun setupTemplateVersion(setupTemplateVersion: Optional<String>) =
            setupTemplateVersion(setupTemplateVersion.getOrNull())

        /**
         * Sets [Builder.setupTemplateVersion] to an arbitrary JSON value.
         *
         * You should usually call [Builder.setupTemplateVersion] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun setupTemplateVersion(setupTemplateVersion: JsonField<String>) = apply {
            this.setupTemplateVersion = setupTemplateVersion
        }

        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        /**
         * Alias for calling [status] with `Status.ofPendingConfiguration(pendingConfiguration)`.
         */
        fun status(pendingConfiguration: BetaPendingConfigurationExternalStorageStatus) =
            status(Status.ofPendingConfiguration(pendingConfiguration))

        /** Alias for calling [status] with `Status.ofAvailable(available)`. */
        fun status(available: BetaAvailableExternalStorageStatus) =
            status(Status.ofAvailable(available))

        /** Alias for calling [status] with `Status.ofActive(active)`. */
        fun status(active: BetaActiveExternalStorageStatus) = status(Status.ofActive(active))

        /** Alias for calling [status] with `Status.ofDraining(draining)`. */
        fun status(draining: BetaDrainingExternalStorageStatus) =
            status(Status.ofDraining(draining))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("external_storage")
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

        /** The user profile the storage is bound to, or null. */
        fun userProfileId(userProfileId: String?) =
            userProfileId(JsonField.ofNullable(userProfileId))

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

        /**
         * Sets [Builder.userProfileId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userProfileId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun userProfileId(userProfileId: JsonField<String>) = apply {
            this.userProfileId = userProfileId
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
         * Returns an immutable instance of [BetaExternalStorage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .displayName()
         * .providerConfig()
         * .setupTemplateVersion()
         * .status()
         * .updatedAt()
         * .userProfileId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaExternalStorage =
            BetaExternalStorage(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("displayName", displayName),
                checkRequired("providerConfig", providerConfig),
                checkRequired("setupTemplateVersion", setupTemplateVersion),
                checkRequired("status", status),
                type,
                checkRequired("updatedAt", updatedAt),
                checkRequired("userProfileId", userProfileId),
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
    fun validate(): BetaExternalStorage = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        displayName()
        providerConfig().validate()
        setupTemplateVersion()
        status().validate()
        _type().let {
            if (it != JsonValue.from("external_storage")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        userProfileId()
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
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (displayName.asKnown().isPresent) 1 else 0) +
            (providerConfig.asKnown().getOrNull()?.validity() ?: 0) +
            (if (setupTemplateVersion.asKnown().isPresent) 1 else 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("external_storage")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (userProfileId.asKnown().isPresent) 1 else 0)

    @JsonDeserialize(using = ProviderConfig.Deserializer::class)
    @JsonSerialize(using = ProviderConfig.Serializer::class)
    class ProviderConfig
    private constructor(
        private val aws: BetaAwsExternalStorageConfig? = null,
        private val gcp: BetaGcpExternalStorageConfig? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                aws != null -> Type.AWS
                gcp != null -> Type.GCP
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        fun bucketName(): String =
            when {
                aws != null -> aws.bucketName()
                gcp != null -> gcp.bucketName()
                else -> _json.getProperty<String>("bucket_name").getRequired("bucket_name")
            }

        fun region(): String =
            when {
                aws != null -> aws.region()
                gcp != null -> gcp.region()
                else -> _json.getProperty<String>("region").getRequired("region")
            }

        /** An Amazon S3 bucket. */
        fun aws(): Optional<BetaAwsExternalStorageConfig> = Optional.ofNullable(aws)

        /** A Google Cloud Storage bucket. */
        fun gcp(): Optional<BetaGcpExternalStorageConfig> = Optional.ofNullable(gcp)

        fun isAws(): Boolean = aws != null

        fun isGcp(): Boolean = gcp != null

        /** An Amazon S3 bucket. */
        fun asAws(): BetaAwsExternalStorageConfig = aws.getOrThrow("aws")

        /** A Google Cloud Storage bucket. */
        fun asGcp(): BetaGcpExternalStorageConfig = gcp.getOrThrow("gcp")

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
         *     public Optional<String> visitAws(BetaAwsExternalStorageConfig aws) {
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

            when {
                aws != null -> aws.validate()
                gcp != null -> gcp.validate()
                else -> throw AnthropicInvalidDataException("Unknown ProviderConfig: $_json")
            }
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
            when {
                aws != null -> aws.validity()
                gcp != null -> gcp.validity()
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ProviderConfig && aws == other.aws && gcp == other.gcp
        }

        override fun hashCode(): Int = Objects.hash(aws, gcp)

        override fun toString(): String =
            when {
                aws != null -> "ProviderConfig{aws=$aws}"
                gcp != null -> "ProviderConfig{gcp=$gcp}"
                _json != null -> "ProviderConfig{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ProviderConfig")
            }

        companion object {

            /** An Amazon S3 bucket. */
            @JvmStatic fun ofAws(aws: BetaAwsExternalStorageConfig) = ProviderConfig(aws = aws)

            /** A Google Cloud Storage bucket. */
            @JvmStatic fun ofGcp(gcp: BetaGcpExternalStorageConfig) = ProviderConfig(gcp = gcp)
        }

        /**
         * An interface that defines how to map each variant of [ProviderConfig] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            /** An Amazon S3 bucket. */
            fun visitAws(aws: BetaAwsExternalStorageConfig): T

            /** A Google Cloud Storage bucket. */
            fun visitGcp(gcp: BetaGcpExternalStorageConfig): T

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
                        return tryDeserialize(node, jacksonTypeRef<BetaAwsExternalStorageConfig>())
                            ?.let { ProviderConfig(aws = it, _json = json) }
                            ?: ProviderConfig(_json = json)
                    }
                    "gcp" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaGcpExternalStorageConfig>())
                            ?.let { ProviderConfig(gcp = it, _json = json) }
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
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ProviderConfig")
                }
            }
        }

        class Type private constructor(private val value: JsonField<String>) : Enum {

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

                @JvmField val AWS = Type(JsonField.of("aws"))

                @JvmField val GCP = Type(JsonField.of("gcp"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "aws" -> AWS
                        "gcp" -> GCP
                        else -> Type(JsonField.of(value))
                    }

                @JsonCreator
                @JvmStatic
                fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                AWS,
                GCP,
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

    @JsonDeserialize(using = Status.Deserializer::class)
    @JsonSerialize(using = Status.Serializer::class)
    class Status
    private constructor(
        private val pendingConfiguration: BetaPendingConfigurationExternalStorageStatus? = null,
        private val available: BetaAvailableExternalStorageStatus? = null,
        private val active: BetaActiveExternalStorageStatus? = null,
        private val draining: BetaDrainingExternalStorageStatus? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                pendingConfiguration != null -> Type.PENDING_CONFIGURATION
                available != null -> Type.AVAILABLE
                active != null -> Type.ACTIVE
                draining != null -> Type.DRAINING
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        fun healthy(): Optional<Boolean> =
            when {
                pendingConfiguration != null -> Optional.empty()
                available != null -> Optional.of(available.healthy())
                active != null -> Optional.of(active.healthy())
                draining != null -> Optional.of(draining.healthy())
                else -> _json.getProperty<Boolean>("healthy").asKnown()
            }

        fun lastCheckedAt(): Optional<OffsetDateTime> =
            when {
                pendingConfiguration != null -> Optional.empty()
                available != null -> Optional.of(available.lastCheckedAt())
                active != null -> Optional.of(active.lastCheckedAt())
                draining != null -> Optional.of(draining.lastCheckedAt())
                else -> _json.getProperty<OffsetDateTime>("last_checked_at").asKnown()
            }

        /** The storage isn't verified yet. */
        fun pendingConfiguration(): Optional<BetaPendingConfigurationExternalStorageStatus> =
            Optional.ofNullable(pendingConfiguration)

        /**
         * The storage is verified, no Workspace has it attached, and Anthropic doesn't use the
         * bucket.
         */
        fun available(): Optional<BetaAvailableExternalStorageStatus> =
            Optional.ofNullable(available)

        /** At least one Workspace has the storage attached. */
        fun active(): Optional<BetaActiveExternalStorageStatus> = Optional.ofNullable(active)

        /**
         * No Workspace has the storage attached. A Workspace can attach it again, unless
         * `removal_started` is true.
         */
        fun draining(): Optional<BetaDrainingExternalStorageStatus> = Optional.ofNullable(draining)

        fun isPendingConfiguration(): Boolean = pendingConfiguration != null

        fun isAvailable(): Boolean = available != null

        fun isActive(): Boolean = active != null

        fun isDraining(): Boolean = draining != null

        /** The storage isn't verified yet. */
        fun asPendingConfiguration(): BetaPendingConfigurationExternalStorageStatus =
            pendingConfiguration.getOrThrow("pendingConfiguration")

        /**
         * The storage is verified, no Workspace has it attached, and Anthropic doesn't use the
         * bucket.
         */
        fun asAvailable(): BetaAvailableExternalStorageStatus = available.getOrThrow("available")

        /** At least one Workspace has the storage attached. */
        fun asActive(): BetaActiveExternalStorageStatus = active.getOrThrow("active")

        /**
         * No Workspace has the storage attached. A Workspace can attach it again, unless
         * `removal_started` is true.
         */
        fun asDraining(): BetaDrainingExternalStorageStatus = draining.getOrThrow("draining")

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
         * Optional<String> result = status.accept(new Status.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitPendingConfiguration(BetaPendingConfigurationExternalStorageStatus pendingConfiguration) {
         *         return Optional.of(pendingConfiguration.toString());
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
                pendingConfiguration != null ->
                    visitor.visitPendingConfiguration(pendingConfiguration)
                available != null -> visitor.visitAvailable(available)
                active != null -> visitor.visitActive(active)
                draining != null -> visitor.visitDraining(draining)
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
        fun validate(): Status = apply {
            if (validated) {
                return@apply
            }

            when {
                pendingConfiguration != null -> pendingConfiguration.validate()
                available != null -> available.validate()
                active != null -> active.validate()
                draining != null -> draining.validate()
                else -> throw AnthropicInvalidDataException("Unknown Status: $_json")
            }
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
            when {
                pendingConfiguration != null -> pendingConfiguration.validity()
                available != null -> available.validity()
                active != null -> active.validity()
                draining != null -> draining.validity()
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Status &&
                pendingConfiguration == other.pendingConfiguration &&
                available == other.available &&
                active == other.active &&
                draining == other.draining
        }

        override fun hashCode(): Int =
            Objects.hash(pendingConfiguration, available, active, draining)

        override fun toString(): String =
            when {
                pendingConfiguration != null -> "Status{pendingConfiguration=$pendingConfiguration}"
                available != null -> "Status{available=$available}"
                active != null -> "Status{active=$active}"
                draining != null -> "Status{draining=$draining}"
                _json != null -> "Status{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Status")
            }

        companion object {

            /** The storage isn't verified yet. */
            @JvmStatic
            fun ofPendingConfiguration(
                pendingConfiguration: BetaPendingConfigurationExternalStorageStatus
            ) = Status(pendingConfiguration = pendingConfiguration)

            /**
             * The storage is verified, no Workspace has it attached, and Anthropic doesn't use the
             * bucket.
             */
            @JvmStatic
            fun ofAvailable(available: BetaAvailableExternalStorageStatus) =
                Status(available = available)

            /** At least one Workspace has the storage attached. */
            @JvmStatic
            fun ofActive(active: BetaActiveExternalStorageStatus) = Status(active = active)

            /**
             * No Workspace has the storage attached. A Workspace can attach it again, unless
             * `removal_started` is true.
             */
            @JvmStatic
            fun ofDraining(draining: BetaDrainingExternalStorageStatus) =
                Status(draining = draining)
        }

        /** An interface that defines how to map each variant of [Status] to a value of type [T]. */
        interface Visitor<out T> {

            /** The storage isn't verified yet. */
            fun visitPendingConfiguration(
                pendingConfiguration: BetaPendingConfigurationExternalStorageStatus
            ): T

            /**
             * The storage is verified, no Workspace has it attached, and Anthropic doesn't use the
             * bucket.
             */
            fun visitAvailable(available: BetaAvailableExternalStorageStatus): T

            /** At least one Workspace has the storage attached. */
            fun visitActive(active: BetaActiveExternalStorageStatus): T

            /**
             * No Workspace has the storage attached. A Workspace can attach it again, unless
             * `removal_started` is true.
             */
            fun visitDraining(draining: BetaDrainingExternalStorageStatus): T

            /**
             * Maps an unknown variant of [Status] to a value of type [T].
             *
             * An instance of [Status] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Status: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Status>(Status::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Status {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "pending_configuration" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaPendingConfigurationExternalStorageStatus>(),
                            )
                            ?.let { Status(pendingConfiguration = it, _json = json) }
                            ?: Status(_json = json)
                    }
                    "available" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaAvailableExternalStorageStatus>(),
                            )
                            ?.let { Status(available = it, _json = json) } ?: Status(_json = json)
                    }
                    "active" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaActiveExternalStorageStatus>(),
                            )
                            ?.let { Status(active = it, _json = json) } ?: Status(_json = json)
                    }
                    "draining" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaDrainingExternalStorageStatus>(),
                            )
                            ?.let { Status(draining = it, _json = json) } ?: Status(_json = json)
                    }
                }

                return Status(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Status>(Status::class) {

            override fun serialize(
                value: Status,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.pendingConfiguration != null ->
                        generator.writeObject(value.pendingConfiguration)
                    value.available != null -> generator.writeObject(value.available)
                    value.active != null -> generator.writeObject(value.active)
                    value.draining != null -> generator.writeObject(value.draining)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Status")
                }
            }
        }

        class Type private constructor(private val value: JsonField<String>) : Enum {

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

                @JvmField val PENDING_CONFIGURATION = Type(JsonField.of("pending_configuration"))

                @JvmField val AVAILABLE = Type(JsonField.of("available"))

                @JvmField val ACTIVE = Type(JsonField.of("active"))

                @JvmField val DRAINING = Type(JsonField.of("draining"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "pending_configuration" -> PENDING_CONFIGURATION
                        "available" -> AVAILABLE
                        "active" -> ACTIVE
                        "draining" -> DRAINING
                        else -> Type(JsonField.of(value))
                    }

                @JsonCreator
                @JvmStatic
                fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                PENDING_CONFIGURATION,
                AVAILABLE,
                ACTIVE,
                DRAINING,
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
                PENDING_CONFIGURATION,
                AVAILABLE,
                ACTIVE,
                DRAINING,
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
                    PENDING_CONFIGURATION -> Value.PENDING_CONFIGURATION
                    AVAILABLE -> Value.AVAILABLE
                    ACTIVE -> Value.ACTIVE
                    DRAINING -> Value.DRAINING
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
                    PENDING_CONFIGURATION -> Known.PENDING_CONFIGURATION
                    AVAILABLE -> Known.AVAILABLE
                    ACTIVE -> Known.ACTIVE
                    DRAINING -> Known.DRAINING
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

        return other is BetaExternalStorage &&
            id == other.id &&
            createdAt == other.createdAt &&
            displayName == other.displayName &&
            providerConfig == other.providerConfig &&
            setupTemplateVersion == other.setupTemplateVersion &&
            status == other.status &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            userProfileId == other.userProfileId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            displayName,
            providerConfig,
            setupTemplateVersion,
            status,
            type,
            updatedAt,
            userProfileId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaExternalStorage{id=$id, createdAt=$createdAt, displayName=$displayName, providerConfig=$providerConfig, setupTemplateVersion=$setupTemplateVersion, status=$status, type=$type, updatedAt=$updatedAt, userProfileId=$userProfileId, additionalProperties=$additionalProperties}"
}
