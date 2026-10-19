package com.anthropic.models.userprofiles

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Create User Profile */
class UserProfileCreateParams
private constructor(
    private val workspaceId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Optional header to select the Workspace for this request. The value is a Workspace ID (for
     * example, `wrkspc_011CZkZaBF1tNoB5wlCeusgy`).
     *
     * Only needed for credentials that can act on more than one Workspace. A credential that
     * belongs to a specific Workspace may omit it; if sent, it must match that Workspace.
     */
    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    /**
     * How the platform uses the API for this entity. `application` (default): the profile
     * represents an individual end-user of the platform's product. `passthrough`: the profile
     * identifies a company the platform resells Claude access to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun accessType(): Optional<UserProfileAccessType> = body.accessType()

    /**
     * Details about the entity this profile represents, as the platform states them. Every field is
     * optional. Not accepted under the legacy `user-profiles-2026-03-24` and
     * `user-profiles-2026-08-18` beta headers, which take the top-level `external_id` (and, under
     * `user-profiles-2026-08-18`, `external_user_onboarded_at`) instead.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalUserDetails(): Optional<UserProfileExternalUserDetailsParams> =
        body.externalUserDetails()

    /**
     * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to 64
     * characters and values up to 512 characters. Values must be non-empty strings.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun metadata(): Optional<Metadata> = body.metadata()

    /**
     * Optional for all profiles. Real-world name of the entity this profile represents (company or
     * individual); for a company the platform resells Claude access to (`access_type`
     * `passthrough`), that company's name where known. Maximum 255 characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun name(): Optional<String> = body.name()

    /**
     * Returns the raw JSON value of [accessType].
     *
     * Unlike [accessType], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _accessType(): JsonField<UserProfileAccessType> = body._accessType()

    /**
     * Returns the raw JSON value of [externalUserDetails].
     *
     * Unlike [externalUserDetails], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _externalUserDetails(): JsonField<UserProfileExternalUserDetailsParams> =
        body._externalUserDetails()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<Metadata> = body._metadata()

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _name(): JsonField<String> = body._name()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): UserProfileCreateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [UserProfileCreateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileCreateParams]. */
    class Builder internal constructor() {

        private var workspaceId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(userProfileCreateParams: UserProfileCreateParams) = apply {
            workspaceId = userProfileCreateParams.workspaceId
            body = userProfileCreateParams.body.toBuilder()
            additionalHeaders = userProfileCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = userProfileCreateParams.additionalQueryParams.toBuilder()
        }

        /**
         * Optional header to select the Workspace for this request. The value is a Workspace ID
         * (for example, `wrkspc_011CZkZaBF1tNoB5wlCeusgy`).
         *
         * Only needed for credentials that can act on more than one Workspace. A credential that
         * belongs to a specific Workspace may omit it; if sent, it must match that Workspace.
         */
        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [accessType]
         * - [externalUserDetails]
         * - [metadata]
         * - [name]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * How the platform uses the API for this entity. `application` (default): the profile
         * represents an individual end-user of the platform's product. `passthrough`: the profile
         * identifies a company the platform resells Claude access to.
         */
        fun accessType(accessType: UserProfileAccessType) = apply { body.accessType(accessType) }

        /**
         * Sets [Builder.accessType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accessType] with a well-typed [UserProfileAccessType]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun accessType(accessType: JsonField<UserProfileAccessType>) = apply {
            body.accessType(accessType)
        }

        /**
         * Details about the entity this profile represents, as the platform states them. Every
         * field is optional. Not accepted under the legacy `user-profiles-2026-03-24` and
         * `user-profiles-2026-08-18` beta headers, which take the top-level `external_id` (and,
         * under `user-profiles-2026-08-18`, `external_user_onboarded_at`) instead.
         */
        fun externalUserDetails(externalUserDetails: UserProfileExternalUserDetailsParams) = apply {
            body.externalUserDetails(externalUserDetails)
        }

        /**
         * Sets [Builder.externalUserDetails] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalUserDetails] with a well-typed
         * [UserProfileExternalUserDetailsParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun externalUserDetails(
            externalUserDetails: JsonField<UserProfileExternalUserDetailsParams>
        ) = apply { body.externalUserDetails(externalUserDetails) }

        /**
         * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to
         * 64 characters and values up to 512 characters. Values must be non-empty strings.
         */
        fun metadata(metadata: Metadata) = apply { body.metadata(metadata) }

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { body.metadata(metadata) }

        /**
         * Optional for all profiles. Real-world name of the entity this profile represents (company
         * or individual); for a company the platform resells Claude access to (`access_type`
         * `passthrough`), that company's name where known. Maximum 255 characters.
         */
        fun name(name: String?) = apply { body.name(name) }

        /** Alias for calling [Builder.name] with `name.orElse(null)`. */
        fun name(name: Optional<String>) = name(name.getOrNull())

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { body.name(name) }

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
         * Returns an immutable instance of [UserProfileCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): UserProfileCreateParams =
            UserProfileCreateParams(
                workspaceId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                workspaceId?.let { put("anthropic-workspace-id", it) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(name = "anthropic.user_profiles.create", route = "/v1/user_profiles")

    /**
     * The settings for a new user profile.
     *
     * Each request creates a new profile, even when another profile already has the same values.
     */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val accessType: JsonField<UserProfileAccessType>,
        private val externalUserDetails: JsonField<UserProfileExternalUserDetailsParams>,
        private val metadata: JsonField<Metadata>,
        private val name: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("access_type")
            @ExcludeMissing
            accessType: JsonField<UserProfileAccessType> = JsonMissing.of(),
            @JsonProperty("external_user_details")
            @ExcludeMissing
            externalUserDetails: JsonField<UserProfileExternalUserDetailsParams> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        ) : this(accessType, externalUserDetails, metadata, name, mutableMapOf())

        /**
         * How the platform uses the API for this entity. `application` (default): the profile
         * represents an individual end-user of the platform's product. `passthrough`: the profile
         * identifies a company the platform resells Claude access to.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun accessType(): Optional<UserProfileAccessType> = accessType.getOptional("access_type")

        /**
         * Details about the entity this profile represents, as the platform states them. Every
         * field is optional. Not accepted under the legacy `user-profiles-2026-03-24` and
         * `user-profiles-2026-08-18` beta headers, which take the top-level `external_id` (and,
         * under `user-profiles-2026-08-18`, `external_user_onboarded_at`) instead.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun externalUserDetails(): Optional<UserProfileExternalUserDetailsParams> =
            externalUserDetails.getOptional("external_user_details")

        /**
         * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to
         * 64 characters and values up to 512 characters. Values must be non-empty strings.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun metadata(): Optional<Metadata> = metadata.getOptional("metadata")

        /**
         * Optional for all profiles. Real-world name of the entity this profile represents (company
         * or individual); for a company the platform resells Claude access to (`access_type`
         * `passthrough`), that company's name where known. Maximum 255 characters.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * Returns the raw JSON value of [accessType].
         *
         * Unlike [accessType], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("access_type")
        @ExcludeMissing
        fun _accessType(): JsonField<UserProfileAccessType> = accessType

        /**
         * Returns the raw JSON value of [externalUserDetails].
         *
         * Unlike [externalUserDetails], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("external_user_details")
        @ExcludeMissing
        fun _externalUserDetails(): JsonField<UserProfileExternalUserDetailsParams> =
            externalUserDetails

        /**
         * Returns the raw JSON value of [metadata].
         *
         * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

        /**
         * Returns the raw JSON value of [name].
         *
         * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var accessType: JsonField<UserProfileAccessType> = JsonMissing.of()
            private var externalUserDetails: JsonField<UserProfileExternalUserDetailsParams> =
                JsonMissing.of()
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                accessType = body.accessType
                externalUserDetails = body.externalUserDetails
                metadata = body.metadata
                name = body.name
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * How the platform uses the API for this entity. `application` (default): the profile
             * represents an individual end-user of the platform's product. `passthrough`: the
             * profile identifies a company the platform resells Claude access to.
             */
            fun accessType(accessType: UserProfileAccessType) = accessType(JsonField.of(accessType))

            /**
             * Sets [Builder.accessType] to an arbitrary JSON value.
             *
             * You should usually call [Builder.accessType] with a well-typed
             * [UserProfileAccessType] value instead. This method is primarily for setting the field
             * to an undocumented or not yet supported value.
             */
            fun accessType(accessType: JsonField<UserProfileAccessType>) = apply {
                this.accessType = accessType
            }

            /**
             * Details about the entity this profile represents, as the platform states them. Every
             * field is optional. Not accepted under the legacy `user-profiles-2026-03-24` and
             * `user-profiles-2026-08-18` beta headers, which take the top-level `external_id` (and,
             * under `user-profiles-2026-08-18`, `external_user_onboarded_at`) instead.
             */
            fun externalUserDetails(externalUserDetails: UserProfileExternalUserDetailsParams) =
                externalUserDetails(JsonField.of(externalUserDetails))

            /**
             * Sets [Builder.externalUserDetails] to an arbitrary JSON value.
             *
             * You should usually call [Builder.externalUserDetails] with a well-typed
             * [UserProfileExternalUserDetailsParams] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun externalUserDetails(
                externalUserDetails: JsonField<UserProfileExternalUserDetailsParams>
            ) = apply { this.externalUserDetails = externalUserDetails }

            /**
             * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys
             * up to 64 characters and values up to 512 characters. Values must be non-empty
             * strings.
             */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /**
             * Sets [Builder.metadata] to an arbitrary JSON value.
             *
             * You should usually call [Builder.metadata] with a well-typed [Metadata] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            /**
             * Optional for all profiles. Real-world name of the entity this profile represents
             * (company or individual); for a company the platform resells Claude access to
             * (`access_type` `passthrough`), that company's name where known. Maximum 255
             * characters.
             */
            fun name(name: String?) = name(JsonField.ofNullable(name))

            /** Alias for calling [Builder.name] with `name.orElse(null)`. */
            fun name(name: Optional<String>) = name(name.getOrNull())

            /**
             * Sets [Builder.name] to an arbitrary JSON value.
             *
             * You should usually call [Builder.name] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun name(name: JsonField<String>) = apply { this.name = name }

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
             */
            fun build(): Body =
                Body(
                    accessType,
                    externalUserDetails,
                    metadata,
                    name,
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

            accessType().ifPresent { it.validate() }
            externalUserDetails().ifPresent { it.validate() }
            metadata().ifPresent { it.validate() }
            name()
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
            (accessType.asKnown().getOrNull()?.validity() ?: 0) +
                (externalUserDetails.asKnown().getOrNull()?.validity() ?: 0) +
                (metadata.asKnown().getOrNull()?.validity() ?: 0) +
                (if (name.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                accessType == other.accessType &&
                externalUserDetails == other.externalUserDetails &&
                metadata == other.metadata &&
                name == other.name &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(accessType, externalUserDetails, metadata, name, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{accessType=$accessType, externalUserDetails=$externalUserDetails, metadata=$metadata, name=$name, additionalProperties=$additionalProperties}"
    }

    /**
     * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to 64
     * characters and values up to 512 characters. Values must be non-empty strings.
     */
    class Metadata
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

            /** Returns a mutable builder for constructing an instance of [Metadata]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Metadata]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(metadata: Metadata) = apply {
                additionalProperties = metadata.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Metadata].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Metadata = Metadata(additionalProperties.toImmutable())
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
        fun validate(): Metadata = apply {
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

            return other is Metadata && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileCreateParams &&
            workspaceId == other.workspaceId &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(workspaceId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "UserProfileCreateParams{workspaceId=$workspaceId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
