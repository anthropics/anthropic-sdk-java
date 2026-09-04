// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
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

/** Create an external key config owned by the caller's organization. */
class ExternalKeyCreateParams
private constructor(
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * KMS provider identity and auth coordinates.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun providerConfig(): ProviderConfig = body.providerConfig()

    /**
     * Human-friendly display name.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = body.displayName()

    /**
     * Data residency geo. Only `us` is supported.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun geo(): Optional<Geo> = body.geo()

    /**
     * Returns the raw JSON value of [providerConfig].
     *
     * Unlike [providerConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _providerConfig(): JsonField<ProviderConfig> = body._providerConfig()

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _displayName(): JsonField<String> = body._displayName()

    /**
     * Returns the raw JSON value of [geo].
     *
     * Unlike [geo], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _geo(): JsonField<Geo> = body._geo()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ExternalKeyCreateParams].
         *
         * The following fields are required:
         * ```java
         * .providerConfig()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ExternalKeyCreateParams]. */
    class Builder internal constructor() {

        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(externalKeyCreateParams: ExternalKeyCreateParams) = apply {
            body = externalKeyCreateParams.body.toBuilder()
            additionalHeaders = externalKeyCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = externalKeyCreateParams.additionalQueryParams.toBuilder()
        }

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [providerConfig]
         * - [displayName]
         * - [geo]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** KMS provider identity and auth coordinates. */
        fun providerConfig(providerConfig: ProviderConfig) = apply {
            body.providerConfig(providerConfig)
        }

        /**
         * Sets [Builder.providerConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.providerConfig] with a well-typed [ProviderConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun providerConfig(providerConfig: JsonField<ProviderConfig>) = apply {
            body.providerConfig(providerConfig)
        }

        /** Alias for calling [providerConfig] with `ProviderConfig.ofAws(aws)`. */
        fun providerConfig(aws: BetaAwsExternalKeyConfig) = apply { body.providerConfig(aws) }

        /**
         * Alias for calling [providerConfig] with the following:
         * ```java
         * BetaAwsExternalKeyConfig.builder()
         *     .kmsArn(kmsArn)
         *     .build()
         * ```
         */
        fun awsProviderConfig(kmsArn: String) = apply { body.awsProviderConfig(kmsArn) }

        /** Alias for calling [providerConfig] with `ProviderConfig.ofGcp(gcp)`. */
        fun providerConfig(gcp: BetaGcpExternalKeyConfig) = apply { body.providerConfig(gcp) }

        /**
         * Alias for calling [providerConfig] with the following:
         * ```java
         * BetaGcpExternalKeyConfig.builder()
         *     .keyName(keyName)
         *     .build()
         * ```
         */
        fun gcpProviderConfig(keyName: String) = apply { body.gcpProviderConfig(keyName) }

        /** Alias for calling [providerConfig] with `ProviderConfig.ofAzure(azure)`. */
        fun providerConfig(azure: BetaAzureExternalKeyConfigParam) = apply {
            body.providerConfig(azure)
        }

        /** Human-friendly display name. */
        fun displayName(displayName: String?) = apply { body.displayName(displayName) }

        /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
        fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

        /**
         * Sets [Builder.displayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayName(displayName: JsonField<String>) = apply { body.displayName(displayName) }

        /** Data residency geo. Only `us` is supported. */
        fun geo(geo: Geo) = apply { body.geo(geo) }

        /**
         * Sets [Builder.geo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.geo] with a well-typed [Geo] value instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun geo(geo: JsonField<Geo>) = apply { body.geo(geo) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [ExternalKeyCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .providerConfig()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ExternalKeyCreateParams =
            ExternalKeyCreateParams(
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val providerConfig: JsonField<ProviderConfig>,
        private val displayName: JsonField<String>,
        private val geo: JsonField<Geo>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("provider_config")
            @ExcludeMissing
            providerConfig: JsonField<ProviderConfig> = JsonMissing.of(),
            @JsonProperty("display_name")
            @ExcludeMissing
            displayName: JsonField<String> = JsonMissing.of(),
            @JsonProperty("geo") @ExcludeMissing geo: JsonField<Geo> = JsonMissing.of(),
        ) : this(providerConfig, displayName, geo, mutableMapOf())

        /**
         * KMS provider identity and auth coordinates.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun providerConfig(): ProviderConfig = providerConfig.getRequired("provider_config")

        /**
         * Human-friendly display name.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun displayName(): Optional<String> = displayName.getOptional("display_name")

        /**
         * Data residency geo. Only `us` is supported.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun geo(): Optional<Geo> = geo.getOptional("geo")

        /**
         * Returns the raw JSON value of [providerConfig].
         *
         * Unlike [providerConfig], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("provider_config")
        @ExcludeMissing
        fun _providerConfig(): JsonField<ProviderConfig> = providerConfig

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
        @JsonProperty("geo") @ExcludeMissing fun _geo(): JsonField<Geo> = geo

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
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .providerConfig()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [providerConfig] set to the
             * given value.
             */
            @JvmStatic
            fun of(providerConfig: ProviderConfig) =
                builder().providerConfig(providerConfig).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var providerConfig: JsonField<ProviderConfig>? = null
            private var displayName: JsonField<String> = JsonMissing.of()
            private var geo: JsonField<Geo> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                providerConfig = body.providerConfig
                displayName = body.displayName
                geo = body.geo
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** KMS provider identity and auth coordinates. */
            fun providerConfig(providerConfig: ProviderConfig) =
                providerConfig(JsonField.of(providerConfig))

            /**
             * Sets [Builder.providerConfig] to an arbitrary JSON value.
             *
             * You should usually call [Builder.providerConfig] with a well-typed [ProviderConfig]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
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
            fun providerConfig(azure: BetaAzureExternalKeyConfigParam) =
                providerConfig(ProviderConfig.ofAzure(azure))

            /** Human-friendly display name. */
            fun displayName(displayName: String?) = displayName(JsonField.ofNullable(displayName))

            /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
            fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

            /**
             * Sets [Builder.displayName] to an arbitrary JSON value.
             *
             * You should usually call [Builder.displayName] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun displayName(displayName: JsonField<String>) = apply {
                this.displayName = displayName
            }

            /** Data residency geo. Only `us` is supported. */
            fun geo(geo: Geo) = geo(JsonField.of(geo))

            /**
             * Sets [Builder.geo] to an arbitrary JSON value.
             *
             * You should usually call [Builder.geo] with a well-typed [Geo] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun geo(geo: JsonField<Geo>) = apply { this.geo = geo }

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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .providerConfig()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("providerConfig", providerConfig),
                    displayName,
                    geo,
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            providerConfig().validate()
            displayName()
            geo().ifPresent { it.validate() }
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
            (providerConfig.asKnown().getOrNull()?.validity() ?: 0) +
                (if (displayName.asKnown().isPresent) 1 else 0) +
                (geo.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                providerConfig == other.providerConfig &&
                displayName == other.displayName &&
                geo == other.geo &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(providerConfig, displayName, geo, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{providerConfig=$providerConfig, displayName=$displayName, geo=$geo, additionalProperties=$additionalProperties}"
    }

    /** KMS provider identity and auth coordinates. */
    @JsonDeserialize(using = ProviderConfig.Deserializer::class)
    @JsonSerialize(using = ProviderConfig.Serializer::class)
    class ProviderConfig
    private constructor(
        private val aws: BetaAwsExternalKeyConfig? = null,
        private val gcp: BetaGcpExternalKeyConfig? = null,
        private val azure: BetaAzureExternalKeyConfigParam? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAws(aws: BetaAwsExternalKeyConfig): Type = Type.AWS

                    override fun visitGcp(gcp: BetaGcpExternalKeyConfig): Type = Type.GCP

                    override fun visitAzure(azure: BetaAzureExternalKeyConfigParam): Type =
                        Type.AZURE

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

                    override fun visitAzure(
                        azure: BetaAzureExternalKeyConfigParam
                    ): Optional<String> = Optional.of(azure.keyName())

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("key_name").asKnown()
                }
            )

        fun aws(): Optional<BetaAwsExternalKeyConfig> = Optional.ofNullable(aws)

        fun gcp(): Optional<BetaGcpExternalKeyConfig> = Optional.ofNullable(gcp)

        /** Azure Key Vault provider configuration. */
        fun azure(): Optional<BetaAzureExternalKeyConfigParam> = Optional.ofNullable(azure)

        fun isAws(): Boolean = aws != null

        fun isGcp(): Boolean = gcp != null

        fun isAzure(): Boolean = azure != null

        fun asAws(): BetaAwsExternalKeyConfig = aws.getOrThrow("aws")

        fun asGcp(): BetaGcpExternalKeyConfig = gcp.getOrThrow("gcp")

        /** Azure Key Vault provider configuration. */
        fun asAzure(): BetaAzureExternalKeyConfigParam = azure.getOrThrow("azure")

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

                    override fun visitAzure(azure: BetaAzureExternalKeyConfigParam) {
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

                    override fun visitAzure(azure: BetaAzureExternalKeyConfigParam) =
                        azure.validity()

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

            /** Azure Key Vault provider configuration. */
            @JvmStatic
            fun ofAzure(azure: BetaAzureExternalKeyConfigParam) = ProviderConfig(azure = azure)
        }

        /**
         * An interface that defines how to map each variant of [ProviderConfig] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitAws(aws: BetaAwsExternalKeyConfig): T

            fun visitGcp(gcp: BetaGcpExternalKeyConfig): T

            /** Azure Key Vault provider configuration. */
            fun visitAzure(azure: BetaAzureExternalKeyConfigParam): T

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
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaAzureExternalKeyConfigParam>(),
                            )
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

    /** Data residency geo. Only `us` is supported. */
    class Geo @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmStatic fun of(value: String) = Geo(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Geo =
                value.asString().getOrNull()?.let { of(it) } ?: Geo(value)
        }

        /** An enum containing [Geo]'s known values. */
        enum class Known {
            US
        }

        /**
         * An enum containing [Geo]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Geo] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            US,
            /** An enum member indicating that [Geo] was instantiated with an unknown value. */
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
                else -> throw AnthropicInvalidDataException("Unknown Geo: $value")
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
        fun validate(): Geo = apply {
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

            return other is Geo && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ExternalKeyCreateParams &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int = Objects.hash(body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ExternalKeyCreateParams{body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
