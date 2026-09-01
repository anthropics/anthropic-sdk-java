// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Create Workspace */
class WorkspaceCreateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Name of the Workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = body.name()

    /**
     * Data residency configuration for the workspace. If omitted, defaults to `workspace_geo:
     * "us"`, `allowed_inference_geos: "unrestricted"`, and `default_inference_geo: "global"`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun dataResidency(): Optional<BetaDataResidencyCreateConfig> = body.dataResidency()

    /**
     * Hex color code representing the Workspace in the Anthropic Console.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayColor(): Optional<String> = body.displayColor()

    /**
     * ID of the customer-managed encryption key (CMEK) configuration to use for this Workspace.
     * Setting this field requires CMEK to be enabled for your organization. When set, data stored
     * for this Workspace is encrypted with the referenced key. Create key configurations with the
     * External Keys API. On Claude Platform on AWS the value is the AWS KMS key ARN, and the key
     * must be a single-Region key in the same AWS account and Region as the Workspace. On that
     * platform the key is validated against this Workspace when it is attached, so a key-policy
     * problem is reported as an error on this request. This field is write-once: once a key is
     * attached to a Workspace it cannot be detached or replaced. To rotate key material, rotate the
     * underlying key on your cloud KMS; the `external_key_id` stays the same.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalKeyId(): Optional<String> = body.externalKeyId()

    /**
     * User-defined tags as string key-value pairs. Keys may not begin with `anthropic`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tags(): Optional<Tags> = body.tags()

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _name(): JsonField<String> = body._name()

    /**
     * Returns the raw JSON value of [dataResidency].
     *
     * Unlike [dataResidency], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _dataResidency(): JsonField<BetaDataResidencyCreateConfig> = body._dataResidency()

    /**
     * Returns the raw JSON value of [displayColor].
     *
     * Unlike [displayColor], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _displayColor(): JsonField<String> = body._displayColor()

    /**
     * Returns the raw JSON value of [externalKeyId].
     *
     * Unlike [externalKeyId], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _externalKeyId(): JsonField<String> = body._externalKeyId()

    /**
     * Returns the raw JSON value of [tags].
     *
     * Unlike [tags], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _tags(): JsonField<Tags> = body._tags()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [WorkspaceCreateParams].
         *
         * The following fields are required:
         * ```java
         * .name()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WorkspaceCreateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(workspaceCreateParams: WorkspaceCreateParams) = apply {
            betas = workspaceCreateParams.betas?.toMutableList()
            body = workspaceCreateParams.body.toBuilder()
            additionalHeaders = workspaceCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = workspaceCreateParams.additionalQueryParams.toBuilder()
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [name]
         * - [dataResidency]
         * - [displayColor]
         * - [externalKeyId]
         * - [tags]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Name of the Workspace. */
        fun name(name: String) = apply { body.name(name) }

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { body.name(name) }

        /**
         * Data residency configuration for the workspace. If omitted, defaults to `workspace_geo:
         * "us"`, `allowed_inference_geos: "unrestricted"`, and `default_inference_geo: "global"`.
         */
        fun dataResidency(dataResidency: BetaDataResidencyCreateConfig?) = apply {
            body.dataResidency(dataResidency)
        }

        /** Alias for calling [Builder.dataResidency] with `dataResidency.orElse(null)`. */
        fun dataResidency(dataResidency: Optional<BetaDataResidencyCreateConfig>) =
            dataResidency(dataResidency.getOrNull())

        /**
         * Sets [Builder.dataResidency] to an arbitrary JSON value.
         *
         * You should usually call [Builder.dataResidency] with a well-typed
         * [BetaDataResidencyCreateConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun dataResidency(dataResidency: JsonField<BetaDataResidencyCreateConfig>) = apply {
            body.dataResidency(dataResidency)
        }

        /** Hex color code representing the Workspace in the Anthropic Console. */
        fun displayColor(displayColor: String?) = apply { body.displayColor(displayColor) }

        /** Alias for calling [Builder.displayColor] with `displayColor.orElse(null)`. */
        fun displayColor(displayColor: Optional<String>) = displayColor(displayColor.getOrNull())

        /**
         * Sets [Builder.displayColor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayColor] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayColor(displayColor: JsonField<String>) = apply {
            body.displayColor(displayColor)
        }

        /**
         * ID of the customer-managed encryption key (CMEK) configuration to use for this Workspace.
         * Setting this field requires CMEK to be enabled for your organization. When set, data
         * stored for this Workspace is encrypted with the referenced key. Create key configurations
         * with the External Keys API. On Claude Platform on AWS the value is the AWS KMS key ARN,
         * and the key must be a single-Region key in the same AWS account and Region as the
         * Workspace. On that platform the key is validated against this Workspace when it is
         * attached, so a key-policy problem is reported as an error on this request. This field is
         * write-once: once a key is attached to a Workspace it cannot be detached or replaced. To
         * rotate key material, rotate the underlying key on your cloud KMS; the `external_key_id`
         * stays the same.
         */
        fun externalKeyId(externalKeyId: String?) = apply { body.externalKeyId(externalKeyId) }

        /** Alias for calling [Builder.externalKeyId] with `externalKeyId.orElse(null)`. */
        fun externalKeyId(externalKeyId: Optional<String>) =
            externalKeyId(externalKeyId.getOrNull())

        /**
         * Sets [Builder.externalKeyId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalKeyId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun externalKeyId(externalKeyId: JsonField<String>) = apply {
            body.externalKeyId(externalKeyId)
        }

        /** User-defined tags as string key-value pairs. Keys may not begin with `anthropic`. */
        fun tags(tags: Tags?) = apply { body.tags(tags) }

        /** Alias for calling [Builder.tags] with `tags.orElse(null)`. */
        fun tags(tags: Optional<Tags>) = tags(tags.getOrNull())

        /**
         * Sets [Builder.tags] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tags] with a well-typed [Tags] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tags(tags: JsonField<Tags>) = apply { body.tags(tags) }

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
         * Returns an immutable instance of [WorkspaceCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .name()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WorkspaceCreateParams =
            WorkspaceCreateParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val name: JsonField<String>,
        private val dataResidency: JsonField<BetaDataResidencyCreateConfig>,
        private val displayColor: JsonField<String>,
        private val externalKeyId: JsonField<String>,
        private val tags: JsonField<Tags>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("data_residency")
            @ExcludeMissing
            dataResidency: JsonField<BetaDataResidencyCreateConfig> = JsonMissing.of(),
            @JsonProperty("display_color")
            @ExcludeMissing
            displayColor: JsonField<String> = JsonMissing.of(),
            @JsonProperty("external_key_id")
            @ExcludeMissing
            externalKeyId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("tags") @ExcludeMissing tags: JsonField<Tags> = JsonMissing.of(),
        ) : this(name, dataResidency, displayColor, externalKeyId, tags, mutableMapOf())

        /**
         * Name of the Workspace.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun name(): String = name.getRequired("name")

        /**
         * Data residency configuration for the workspace. If omitted, defaults to `workspace_geo:
         * "us"`, `allowed_inference_geos: "unrestricted"`, and `default_inference_geo: "global"`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun dataResidency(): Optional<BetaDataResidencyCreateConfig> =
            dataResidency.getOptional("data_residency")

        /**
         * Hex color code representing the Workspace in the Anthropic Console.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun displayColor(): Optional<String> = displayColor.getOptional("display_color")

        /**
         * ID of the customer-managed encryption key (CMEK) configuration to use for this Workspace.
         * Setting this field requires CMEK to be enabled for your organization. When set, data
         * stored for this Workspace is encrypted with the referenced key. Create key configurations
         * with the External Keys API. On Claude Platform on AWS the value is the AWS KMS key ARN,
         * and the key must be a single-Region key in the same AWS account and Region as the
         * Workspace. On that platform the key is validated against this Workspace when it is
         * attached, so a key-policy problem is reported as an error on this request. This field is
         * write-once: once a key is attached to a Workspace it cannot be detached or replaced. To
         * rotate key material, rotate the underlying key on your cloud KMS; the `external_key_id`
         * stays the same.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun externalKeyId(): Optional<String> = externalKeyId.getOptional("external_key_id")

        /**
         * User-defined tags as string key-value pairs. Keys may not begin with `anthropic`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun tags(): Optional<Tags> = tags.getOptional("tags")

        /**
         * Returns the raw JSON value of [name].
         *
         * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        /**
         * Returns the raw JSON value of [dataResidency].
         *
         * Unlike [dataResidency], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("data_residency")
        @ExcludeMissing
        fun _dataResidency(): JsonField<BetaDataResidencyCreateConfig> = dataResidency

        /**
         * Returns the raw JSON value of [displayColor].
         *
         * Unlike [displayColor], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("display_color")
        @ExcludeMissing
        fun _displayColor(): JsonField<String> = displayColor

        /**
         * Returns the raw JSON value of [externalKeyId].
         *
         * Unlike [externalKeyId], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("external_key_id")
        @ExcludeMissing
        fun _externalKeyId(): JsonField<String> = externalKeyId

        /**
         * Returns the raw JSON value of [tags].
         *
         * Unlike [tags], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tags") @ExcludeMissing fun _tags(): JsonField<Tags> = tags

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
             * .name()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [name] set to the given
             * value.
             */
            @JvmStatic fun of(name: String) = builder().name(name).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var name: JsonField<String>? = null
            private var dataResidency: JsonField<BetaDataResidencyCreateConfig> = JsonMissing.of()
            private var displayColor: JsonField<String> = JsonMissing.of()
            private var externalKeyId: JsonField<String> = JsonMissing.of()
            private var tags: JsonField<Tags> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                name = body.name
                dataResidency = body.dataResidency
                displayColor = body.displayColor
                externalKeyId = body.externalKeyId
                tags = body.tags
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** Name of the Workspace. */
            fun name(name: String) = name(JsonField.of(name))

            /**
             * Sets [Builder.name] to an arbitrary JSON value.
             *
             * You should usually call [Builder.name] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /**
             * Data residency configuration for the workspace. If omitted, defaults to
             * `workspace_geo: "us"`, `allowed_inference_geos: "unrestricted"`, and
             * `default_inference_geo: "global"`.
             */
            fun dataResidency(dataResidency: BetaDataResidencyCreateConfig?) =
                dataResidency(JsonField.ofNullable(dataResidency))

            /** Alias for calling [Builder.dataResidency] with `dataResidency.orElse(null)`. */
            fun dataResidency(dataResidency: Optional<BetaDataResidencyCreateConfig>) =
                dataResidency(dataResidency.getOrNull())

            /**
             * Sets [Builder.dataResidency] to an arbitrary JSON value.
             *
             * You should usually call [Builder.dataResidency] with a well-typed
             * [BetaDataResidencyCreateConfig] value instead. This method is primarily for setting
             * the field to an undocumented or not yet supported value.
             */
            fun dataResidency(dataResidency: JsonField<BetaDataResidencyCreateConfig>) = apply {
                this.dataResidency = dataResidency
            }

            /** Hex color code representing the Workspace in the Anthropic Console. */
            fun displayColor(displayColor: String?) =
                displayColor(JsonField.ofNullable(displayColor))

            /** Alias for calling [Builder.displayColor] with `displayColor.orElse(null)`. */
            fun displayColor(displayColor: Optional<String>) =
                displayColor(displayColor.getOrNull())

            /**
             * Sets [Builder.displayColor] to an arbitrary JSON value.
             *
             * You should usually call [Builder.displayColor] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun displayColor(displayColor: JsonField<String>) = apply {
                this.displayColor = displayColor
            }

            /**
             * ID of the customer-managed encryption key (CMEK) configuration to use for this
             * Workspace. Setting this field requires CMEK to be enabled for your organization. When
             * set, data stored for this Workspace is encrypted with the referenced key. Create key
             * configurations with the External Keys API. On Claude Platform on AWS the value is the
             * AWS KMS key ARN, and the key must be a single-Region key in the same AWS account and
             * Region as the Workspace. On that platform the key is validated against this Workspace
             * when it is attached, so a key-policy problem is reported as an error on this request.
             * This field is write-once: once a key is attached to a Workspace it cannot be detached
             * or replaced. To rotate key material, rotate the underlying key on your cloud KMS; the
             * `external_key_id` stays the same.
             */
            fun externalKeyId(externalKeyId: String?) =
                externalKeyId(JsonField.ofNullable(externalKeyId))

            /** Alias for calling [Builder.externalKeyId] with `externalKeyId.orElse(null)`. */
            fun externalKeyId(externalKeyId: Optional<String>) =
                externalKeyId(externalKeyId.getOrNull())

            /**
             * Sets [Builder.externalKeyId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.externalKeyId] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun externalKeyId(externalKeyId: JsonField<String>) = apply {
                this.externalKeyId = externalKeyId
            }

            /** User-defined tags as string key-value pairs. Keys may not begin with `anthropic`. */
            fun tags(tags: Tags?) = tags(JsonField.ofNullable(tags))

            /** Alias for calling [Builder.tags] with `tags.orElse(null)`. */
            fun tags(tags: Optional<Tags>) = tags(tags.getOrNull())

            /**
             * Sets [Builder.tags] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tags] with a well-typed [Tags] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun tags(tags: JsonField<Tags>) = apply { this.tags = tags }

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
             * .name()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("name", name),
                    dataResidency,
                    displayColor,
                    externalKeyId,
                    tags,
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

            name()
            dataResidency().ifPresent { it.validate() }
            displayColor()
            externalKeyId()
            tags().ifPresent { it.validate() }
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
            (if (name.asKnown().isPresent) 1 else 0) +
                (dataResidency.asKnown().getOrNull()?.validity() ?: 0) +
                (if (displayColor.asKnown().isPresent) 1 else 0) +
                (if (externalKeyId.asKnown().isPresent) 1 else 0) +
                (tags.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                name == other.name &&
                dataResidency == other.dataResidency &&
                displayColor == other.displayColor &&
                externalKeyId == other.externalKeyId &&
                tags == other.tags &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                name,
                dataResidency,
                displayColor,
                externalKeyId,
                tags,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{name=$name, dataResidency=$dataResidency, displayColor=$displayColor, externalKeyId=$externalKeyId, tags=$tags, additionalProperties=$additionalProperties}"
    }

    /** User-defined tags as string key-value pairs. Keys may not begin with `anthropic`. */
    class Tags
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Tags]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Tags]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(tags: Tags) = apply {
                additionalProperties = tags.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Tags].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Tags = Tags(additionalProperties.toImmutable())
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
        fun validate(): Tags = apply {
            if (validated) {
                return@apply
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
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tags && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Tags{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WorkspaceCreateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "WorkspaceCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
