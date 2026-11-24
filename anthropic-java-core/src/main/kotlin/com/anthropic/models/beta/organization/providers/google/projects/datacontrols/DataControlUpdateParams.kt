package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

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
 *
 * Your organization must have proved that it owns the project. Only one organization at a time can
 * have controls turned on for a project.
 *
 * **Requires a credential that identifies a user or a service account**: an OAuth access token with
 * the `org:admin` scope, or an organization-scoped API key that belongs to a user or service
 * account whose organization role can update these controls. The `admin`, `owner` and
 * `primary_owner` roles can. An Admin API key gets a `403` error.
 */
class DataControlUpdateParams
private constructor(
    private val projectNumber: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The Google Cloud project's number, such as `123456789012`, not its project ID. */
    fun projectNumber(): Optional<String> = Optional.ofNullable(projectNumber)

    /**
     * This endpoint is in beta: requests must send `google-managed-data-controls-2026-09-01` in
     * this header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The customer-managed encryption key control. Leave it out to keep the control as it is. Send
     * `cmek`, `storage`, or both.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cmek(): Optional<BetaGoogleManagedProjectCmekControlParams> = body.cmek()

    /**
     * The customer-owned storage control. Leave it out to keep the control as it is. Send `cmek`,
     * `storage`, or both.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun storage(): Optional<BetaGoogleManagedProjectStorageControlParams> = body.storage()

    /**
     * Returns the raw JSON value of [cmek].
     *
     * Unlike [cmek], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _cmek(): JsonField<BetaGoogleManagedProjectCmekControlParams> = body._cmek()

    /**
     * Returns the raw JSON value of [storage].
     *
     * Unlike [storage], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _storage(): JsonField<BetaGoogleManagedProjectStorageControlParams> = body._storage()

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

        private var projectNumber: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(dataControlUpdateParams: DataControlUpdateParams) = apply {
            projectNumber = dataControlUpdateParams.projectNumber
            betas = dataControlUpdateParams.betas?.toMutableList()
            body = dataControlUpdateParams.body.toBuilder()
            additionalHeaders = dataControlUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = dataControlUpdateParams.additionalQueryParams.toBuilder()
        }

        /** The Google Cloud project's number, such as `123456789012`, not its project ID. */
        fun projectNumber(projectNumber: String?) = apply { this.projectNumber = projectNumber }

        /** Alias for calling [Builder.projectNumber] with `projectNumber.orElse(null)`. */
        fun projectNumber(projectNumber: Optional<String>) =
            projectNumber(projectNumber.getOrNull())

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
        fun cmek(cmek: BetaGoogleManagedProjectCmekControlParams) = apply { body.cmek(cmek) }

        /**
         * Sets [Builder.cmek] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cmek] with a well-typed
         * [BetaGoogleManagedProjectCmekControlParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun cmek(cmek: JsonField<BetaGoogleManagedProjectCmekControlParams>) = apply {
            body.cmek(cmek)
        }

        /**
         * Alias for calling [cmek] with
         * `BetaGoogleManagedProjectCmekControlParams.ofDisabled(disabled)`.
         */
        fun cmek(disabled: BetaGoogleManagedProjectCmekDisabledParams) = apply {
            body.cmek(disabled)
        }

        /**
         * Alias for calling [cmek] with
         * `BetaGoogleManagedProjectCmekControlParams.ofEnabled(enabled)`.
         */
        fun cmek(enabled: BetaGoogleManagedProjectCmekEnabledParams) = apply { body.cmek(enabled) }

        /**
         * The customer-owned storage control. Leave it out to keep the control as it is. Send
         * `cmek`, `storage`, or both.
         */
        fun storage(storage: BetaGoogleManagedProjectStorageControlParams) = apply {
            body.storage(storage)
        }

        /**
         * Sets [Builder.storage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.storage] with a well-typed
         * [BetaGoogleManagedProjectStorageControlParams] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun storage(storage: JsonField<BetaGoogleManagedProjectStorageControlParams>) = apply {
            body.storage(storage)
        }

        /**
         * Alias for calling [storage] with
         * `BetaGoogleManagedProjectStorageControlParams.ofDisabled(disabled)`.
         */
        fun storage(disabled: BetaGoogleManagedProjectStorageDisabledParams) = apply {
            body.storage(disabled)
        }

        /**
         * Alias for calling [storage] with
         * `BetaGoogleManagedProjectStorageControlParams.ofEnabled(enabled)`.
         */
        fun storage(enabled: BetaGoogleManagedProjectStorageEnabledParams) = apply {
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
                projectNumber,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> projectNumber ?: ""
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
                                    it + (listOf("google-managed-data-controls-2026-09-01") - it)
                                }
                                .joinToString(","),
                        )
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.providers.google.projects.data_controls.update",
            route = "/v1/organizations/providers/google/projects/{project_number}/data_controls",
            attributes = mapOf("anthropic.organization.provider.google.project.id" to _pathParam(0)),
        )

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val cmek: JsonField<BetaGoogleManagedProjectCmekControlParams>,
        private val storage: JsonField<BetaGoogleManagedProjectStorageControlParams>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("cmek")
            @ExcludeMissing
            cmek: JsonField<BetaGoogleManagedProjectCmekControlParams> = JsonMissing.of(),
            @JsonProperty("storage")
            @ExcludeMissing
            storage: JsonField<BetaGoogleManagedProjectStorageControlParams> = JsonMissing.of(),
        ) : this(cmek, storage, mutableMapOf())

        /**
         * The customer-managed encryption key control. Leave it out to keep the control as it is.
         * Send `cmek`, `storage`, or both.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun cmek(): Optional<BetaGoogleManagedProjectCmekControlParams> = cmek.getOptional("cmek")

        /**
         * The customer-owned storage control. Leave it out to keep the control as it is. Send
         * `cmek`, `storage`, or both.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun storage(): Optional<BetaGoogleManagedProjectStorageControlParams> =
            storage.getOptional("storage")

        /**
         * Returns the raw JSON value of [cmek].
         *
         * Unlike [cmek], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("cmek")
        @ExcludeMissing
        fun _cmek(): JsonField<BetaGoogleManagedProjectCmekControlParams> = cmek

        /**
         * Returns the raw JSON value of [storage].
         *
         * Unlike [storage], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("storage")
        @ExcludeMissing
        fun _storage(): JsonField<BetaGoogleManagedProjectStorageControlParams> = storage

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

            private var cmek: JsonField<BetaGoogleManagedProjectCmekControlParams> =
                JsonMissing.of()
            private var storage: JsonField<BetaGoogleManagedProjectStorageControlParams> =
                JsonMissing.of()
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
            fun cmek(cmek: BetaGoogleManagedProjectCmekControlParams) = cmek(JsonField.of(cmek))

            /**
             * Sets [Builder.cmek] to an arbitrary JSON value.
             *
             * You should usually call [Builder.cmek] with a well-typed
             * [BetaGoogleManagedProjectCmekControlParams] value instead. This method is primarily
             * for setting the field to an undocumented or not yet supported value.
             */
            fun cmek(cmek: JsonField<BetaGoogleManagedProjectCmekControlParams>) = apply {
                this.cmek = cmek
            }

            /**
             * Alias for calling [cmek] with
             * `BetaGoogleManagedProjectCmekControlParams.ofDisabled(disabled)`.
             */
            fun cmek(disabled: BetaGoogleManagedProjectCmekDisabledParams) =
                cmek(BetaGoogleManagedProjectCmekControlParams.ofDisabled(disabled))

            /**
             * Alias for calling [cmek] with
             * `BetaGoogleManagedProjectCmekControlParams.ofEnabled(enabled)`.
             */
            fun cmek(enabled: BetaGoogleManagedProjectCmekEnabledParams) =
                cmek(BetaGoogleManagedProjectCmekControlParams.ofEnabled(enabled))

            /**
             * The customer-owned storage control. Leave it out to keep the control as it is. Send
             * `cmek`, `storage`, or both.
             */
            fun storage(storage: BetaGoogleManagedProjectStorageControlParams) =
                storage(JsonField.of(storage))

            /**
             * Sets [Builder.storage] to an arbitrary JSON value.
             *
             * You should usually call [Builder.storage] with a well-typed
             * [BetaGoogleManagedProjectStorageControlParams] value instead. This method is
             * primarily for setting the field to an undocumented or not yet supported value.
             */
            fun storage(storage: JsonField<BetaGoogleManagedProjectStorageControlParams>) = apply {
                this.storage = storage
            }

            /**
             * Alias for calling [storage] with
             * `BetaGoogleManagedProjectStorageControlParams.ofDisabled(disabled)`.
             */
            fun storage(disabled: BetaGoogleManagedProjectStorageDisabledParams) =
                storage(BetaGoogleManagedProjectStorageControlParams.ofDisabled(disabled))

            /**
             * Alias for calling [storage] with
             * `BetaGoogleManagedProjectStorageControlParams.ofEnabled(enabled)`.
             */
            fun storage(enabled: BetaGoogleManagedProjectStorageEnabledParams) =
                storage(BetaGoogleManagedProjectStorageControlParams.ofEnabled(enabled))

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
            projectNumber == other.projectNumber &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(projectNumber, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "DataControlUpdateParams{projectNumber=$projectNumber, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
