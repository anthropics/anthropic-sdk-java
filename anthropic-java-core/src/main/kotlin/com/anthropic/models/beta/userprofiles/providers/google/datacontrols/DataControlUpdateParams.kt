package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Turn a control on or off, or change the key or bucket set for a region. Controls and regions that
 * you leave out stay as they are.
 */
class DataControlUpdateParams
private constructor(
    private val userProfileId: String?,
    private val betas: List<AnthropicBeta>?,
    private val workspaceId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The ID of the user profile whose Google-managed data controls to update (`uprof_...`). */
    fun userProfileId(): Optional<String> = Optional.ofNullable(userProfileId)

    /**
     * This endpoint is in beta: requests must send `google-managed-data-controls-2026-09-01` in
     * this header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Optional header to select the Workspace for this request. The value is a Workspace ID (for
     * example, `wrkspc_011CZkZaBF1tNoB5wlCeusgy`).
     *
     * Only needed for credentials that can act on more than one Workspace. A credential that
     * belongs to a specific Workspace may omit it; if sent, it must match that Workspace.
     */
    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    /**
     * The customer-managed encryption key control. Leave it out to keep the control as it is. Send
     * `cmek`, `storage`, or both.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cmek(): Optional<BetaGoogleManagedCmekControlParams> = body.cmek()

    /**
     * The customer-owned storage control. Leave it out to keep the control as it is. Send `cmek`,
     * `storage`, or both.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun storage(): Optional<BetaGoogleManagedStorageControlParams> = body.storage()

    /**
     * Returns the raw JSON value of [cmek].
     *
     * Unlike [cmek], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _cmek(): JsonField<BetaGoogleManagedCmekControlParams> = body._cmek()

    /**
     * Returns the raw JSON value of [storage].
     *
     * Unlike [storage], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _storage(): JsonField<BetaGoogleManagedStorageControlParams> = body._storage()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): DataControlUpdateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [DataControlUpdateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DataControlUpdateParams]. */
    class Builder internal constructor() {

        private var userProfileId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var workspaceId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(dataControlUpdateParams: DataControlUpdateParams) = apply {
            userProfileId = dataControlUpdateParams.userProfileId
            betas = dataControlUpdateParams.betas?.toMutableList()
            workspaceId = dataControlUpdateParams.workspaceId
            body = dataControlUpdateParams.body.toBuilder()
            additionalHeaders = dataControlUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = dataControlUpdateParams.additionalQueryParams.toBuilder()
        }

        /**
         * The ID of the user profile whose Google-managed data controls to update (`uprof_...`).
         */
        fun userProfileId(userProfileId: String?) = apply { this.userProfileId = userProfileId }

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

        /**
         * This endpoint is in beta: requests must send `google-managed-data-controls-2026-09-01` in
         * this header.
         */
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
         * - [cmek]
         * - [storage]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * The customer-managed encryption key control. Leave it out to keep the control as it is.
         * Send `cmek`, `storage`, or both.
         */
        fun cmek(cmek: BetaGoogleManagedCmekControlParams) = apply { body.cmek(cmek) }

        /**
         * Sets [Builder.cmek] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cmek] with a well-typed
         * [BetaGoogleManagedCmekControlParams] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun cmek(cmek: JsonField<BetaGoogleManagedCmekControlParams>) = apply { body.cmek(cmek) }

        /**
         * Alias for calling [cmek] with `BetaGoogleManagedCmekControlParams.ofDisabled(disabled)`.
         */
        fun cmek(disabled: BetaGoogleManagedCmekDisabledParams) = apply { body.cmek(disabled) }

        /**
         * Alias for calling [cmek] with `BetaGoogleManagedCmekControlParams.ofEnabled(enabled)`.
         */
        fun cmek(enabled: BetaGoogleManagedCmekEnabledParams) = apply { body.cmek(enabled) }

        /**
         * The customer-owned storage control. Leave it out to keep the control as it is. Send
         * `cmek`, `storage`, or both.
         */
        fun storage(storage: BetaGoogleManagedStorageControlParams) = apply {
            body.storage(storage)
        }

        /**
         * Sets [Builder.storage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.storage] with a well-typed
         * [BetaGoogleManagedStorageControlParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun storage(storage: JsonField<BetaGoogleManagedStorageControlParams>) = apply {
            body.storage(storage)
        }

        /**
         * Alias for calling [storage] with
         * `BetaGoogleManagedStorageControlParams.ofDisabled(disabled)`.
         */
        fun storage(disabled: BetaGoogleManagedStorageDisabledParams) = apply {
            body.storage(disabled)
        }

        /**
         * Alias for calling [storage] with
         * `BetaGoogleManagedStorageControlParams.ofEnabled(enabled)`.
         */
        fun storage(enabled: BetaGoogleManagedStorageEnabledParams) = apply {
            body.storage(enabled)
        }

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
         * Returns an immutable instance of [DataControlUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): DataControlUpdateParams =
            DataControlUpdateParams(
                userProfileId,
                betas?.toImmutable(),
                workspaceId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> userProfileId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put(
                            "anthropic-beta",
                            it.map { it.toString() }
                                .let {
                                    it +
                                        (listOf(
                                            "user-profiles-2026-09-04",
                                            "google-managed-data-controls-2026-09-01",
                                        ) - it)
                                }
                                .joinToString(","),
                        )
                    }
                }
                workspaceId?.let { put("anthropic-workspace-id", it) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.user_profiles.providers.google.data_controls.update",
            route = "/v1/user_profiles/{user_profile_id}/providers/google/data_controls",
            attributes = mapOf("anthropic.user_profile.id" to _pathParam(0)),
        )

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val cmek: JsonField<BetaGoogleManagedCmekControlParams>,
        private val storage: JsonField<BetaGoogleManagedStorageControlParams>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("cmek")
            @ExcludeMissing
            cmek: JsonField<BetaGoogleManagedCmekControlParams> = JsonMissing.of(),
            @JsonProperty("storage")
            @ExcludeMissing
            storage: JsonField<BetaGoogleManagedStorageControlParams> = JsonMissing.of(),
        ) : this(cmek, storage, mutableMapOf())

        /**
         * The customer-managed encryption key control. Leave it out to keep the control as it is.
         * Send `cmek`, `storage`, or both.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun cmek(): Optional<BetaGoogleManagedCmekControlParams> = cmek.getOptional("cmek")

        /**
         * The customer-owned storage control. Leave it out to keep the control as it is. Send
         * `cmek`, `storage`, or both.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun storage(): Optional<BetaGoogleManagedStorageControlParams> =
            storage.getOptional("storage")

        /**
         * Returns the raw JSON value of [cmek].
         *
         * Unlike [cmek], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("cmek")
        @ExcludeMissing
        fun _cmek(): JsonField<BetaGoogleManagedCmekControlParams> = cmek

        /**
         * Returns the raw JSON value of [storage].
         *
         * Unlike [storage], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("storage")
        @ExcludeMissing
        fun _storage(): JsonField<BetaGoogleManagedStorageControlParams> = storage

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

            private var cmek: JsonField<BetaGoogleManagedCmekControlParams> = JsonMissing.of()
            private var storage: JsonField<BetaGoogleManagedStorageControlParams> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                cmek = body.cmek
                storage = body.storage
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The customer-managed encryption key control. Leave it out to keep the control as it
             * is. Send `cmek`, `storage`, or both.
             */
            fun cmek(cmek: BetaGoogleManagedCmekControlParams) = cmek(JsonField.of(cmek))

            /**
             * Sets [Builder.cmek] to an arbitrary JSON value.
             *
             * You should usually call [Builder.cmek] with a well-typed
             * [BetaGoogleManagedCmekControlParams] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun cmek(cmek: JsonField<BetaGoogleManagedCmekControlParams>) = apply {
                this.cmek = cmek
            }

            /**
             * Alias for calling [cmek] with
             * `BetaGoogleManagedCmekControlParams.ofDisabled(disabled)`.
             */
            fun cmek(disabled: BetaGoogleManagedCmekDisabledParams) =
                cmek(BetaGoogleManagedCmekControlParams.ofDisabled(disabled))

            /**
             * Alias for calling [cmek] with
             * `BetaGoogleManagedCmekControlParams.ofEnabled(enabled)`.
             */
            fun cmek(enabled: BetaGoogleManagedCmekEnabledParams) =
                cmek(BetaGoogleManagedCmekControlParams.ofEnabled(enabled))

            /**
             * The customer-owned storage control. Leave it out to keep the control as it is. Send
             * `cmek`, `storage`, or both.
             */
            fun storage(storage: BetaGoogleManagedStorageControlParams) =
                storage(JsonField.of(storage))

            /**
             * Sets [Builder.storage] to an arbitrary JSON value.
             *
             * You should usually call [Builder.storage] with a well-typed
             * [BetaGoogleManagedStorageControlParams] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun storage(storage: JsonField<BetaGoogleManagedStorageControlParams>) = apply {
                this.storage = storage
            }

            /**
             * Alias for calling [storage] with
             * `BetaGoogleManagedStorageControlParams.ofDisabled(disabled)`.
             */
            fun storage(disabled: BetaGoogleManagedStorageDisabledParams) =
                storage(BetaGoogleManagedStorageControlParams.ofDisabled(disabled))

            /**
             * Alias for calling [storage] with
             * `BetaGoogleManagedStorageControlParams.ofEnabled(enabled)`.
             */
            fun storage(enabled: BetaGoogleManagedStorageEnabledParams) =
                storage(BetaGoogleManagedStorageControlParams.ofEnabled(enabled))

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
            fun build(): Body = Body(cmek, storage, additionalProperties.toMutableMap())
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

            cmek().ifPresent { it.validate() }
            storage().ifPresent { it.validate() }
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
            (cmek.asKnown().getOrNull()?.validity() ?: 0) +
                (storage.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                cmek == other.cmek &&
                storage == other.storage &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(cmek, storage, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{cmek=$cmek, storage=$storage, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DataControlUpdateParams &&
            userProfileId == other.userProfileId &&
            betas == other.betas &&
            workspaceId == other.workspaceId &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            userProfileId,
            betas,
            workspaceId,
            body,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "DataControlUpdateParams{userProfileId=$userProfileId, betas=$betas, workspaceId=$workspaceId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
