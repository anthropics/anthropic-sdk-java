package com.anthropic.models.organization.workspaces.serviceaccounts

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
 * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
 * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
 *
 * Add a service account to a workspace with the given `workspace_role`.
 *
 * The role determines what the service account can do in the workspace and which workspace-scoped
 * permissions it can be granted when authenticating through federation. Every service account is
 * already an implicit `workspace_user` member of the default workspace; adding it explicitly
 * assigns a chosen role. If the service account is already an explicit member of the workspace, its
 * `workspace_role` is replaced with the value supplied here. Archived workspaces return 400.
 * Archived service accounts cannot be added and are rejected.
 */
class ServiceAccountAddParams
private constructor(
    private val workspaceId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the workspace. */
    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    /**
     * Tagged service account ID to add.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun serviceAccountId(): String = body.serviceAccountId()

    /**
     * Role to assign to the service account in this workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceRole(): NoBillingWorkspaceRole = body.workspaceRole()

    /**
     * Returns the raw JSON value of [serviceAccountId].
     *
     * Unlike [serviceAccountId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _serviceAccountId(): JsonField<String> = body._serviceAccountId()

    /**
     * Returns the raw JSON value of [workspaceRole].
     *
     * Unlike [workspaceRole], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _workspaceRole(): JsonField<NoBillingWorkspaceRole> = body._workspaceRole()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ServiceAccountAddParams].
         *
         * The following fields are required:
         * ```java
         * .serviceAccountId()
         * .workspaceRole()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ServiceAccountAddParams]. */
    class Builder internal constructor() {

        private var workspaceId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(serviceAccountAddParams: ServiceAccountAddParams) = apply {
            workspaceId = serviceAccountAddParams.workspaceId
            body = serviceAccountAddParams.body.toBuilder()
            additionalHeaders = serviceAccountAddParams.additionalHeaders.toBuilder()
            additionalQueryParams = serviceAccountAddParams.additionalQueryParams.toBuilder()
        }

        /** ID of the workspace. */
        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [serviceAccountId]
         * - [workspaceRole]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Tagged service account ID to add. */
        fun serviceAccountId(serviceAccountId: String) = apply {
            body.serviceAccountId(serviceAccountId)
        }

        /**
         * Sets [Builder.serviceAccountId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceAccountId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun serviceAccountId(serviceAccountId: JsonField<String>) = apply {
            body.serviceAccountId(serviceAccountId)
        }

        /** Role to assign to the service account in this workspace. */
        fun workspaceRole(workspaceRole: NoBillingWorkspaceRole) = apply {
            body.workspaceRole(workspaceRole)
        }

        /**
         * Sets [Builder.workspaceRole] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceRole] with a well-typed
         * [NoBillingWorkspaceRole] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun workspaceRole(workspaceRole: JsonField<NoBillingWorkspaceRole>) = apply {
            body.workspaceRole(workspaceRole)
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
         * Returns an immutable instance of [ServiceAccountAddParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .serviceAccountId()
         * .workspaceRole()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ServiceAccountAddParams =
            ServiceAccountAddParams(
                workspaceId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> workspaceId ?: ""
            else -> ""
        }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val serviceAccountId: JsonField<String>,
        private val workspaceRole: JsonField<NoBillingWorkspaceRole>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("service_account_id")
            @ExcludeMissing
            serviceAccountId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("workspace_role")
            @ExcludeMissing
            workspaceRole: JsonField<NoBillingWorkspaceRole> = JsonMissing.of(),
        ) : this(serviceAccountId, workspaceRole, mutableMapOf())

        /**
         * Tagged service account ID to add.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun serviceAccountId(): String = serviceAccountId.getRequired("service_account_id")

        /**
         * Role to assign to the service account in this workspace.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun workspaceRole(): NoBillingWorkspaceRole = workspaceRole.getRequired("workspace_role")

        /**
         * Returns the raw JSON value of [serviceAccountId].
         *
         * Unlike [serviceAccountId], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("service_account_id")
        @ExcludeMissing
        fun _serviceAccountId(): JsonField<String> = serviceAccountId

        /**
         * Returns the raw JSON value of [workspaceRole].
         *
         * Unlike [workspaceRole], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("workspace_role")
        @ExcludeMissing
        fun _workspaceRole(): JsonField<NoBillingWorkspaceRole> = workspaceRole

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
             * .serviceAccountId()
             * .workspaceRole()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var serviceAccountId: JsonField<String>? = null
            private var workspaceRole: JsonField<NoBillingWorkspaceRole>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                serviceAccountId = body.serviceAccountId
                workspaceRole = body.workspaceRole
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** Tagged service account ID to add. */
            fun serviceAccountId(serviceAccountId: String) =
                serviceAccountId(JsonField.of(serviceAccountId))

            /**
             * Sets [Builder.serviceAccountId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.serviceAccountId] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun serviceAccountId(serviceAccountId: JsonField<String>) = apply {
                this.serviceAccountId = serviceAccountId
            }

            /** Role to assign to the service account in this workspace. */
            fun workspaceRole(workspaceRole: NoBillingWorkspaceRole) =
                workspaceRole(JsonField.of(workspaceRole))

            /**
             * Sets [Builder.workspaceRole] to an arbitrary JSON value.
             *
             * You should usually call [Builder.workspaceRole] with a well-typed
             * [NoBillingWorkspaceRole] value instead. This method is primarily for setting the
             * field to an undocumented or not yet supported value.
             */
            fun workspaceRole(workspaceRole: JsonField<NoBillingWorkspaceRole>) = apply {
                this.workspaceRole = workspaceRole
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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .serviceAccountId()
             * .workspaceRole()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("serviceAccountId", serviceAccountId),
                    checkRequired("workspaceRole", workspaceRole),
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

            serviceAccountId()
            workspaceRole().validate()
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
            (if (serviceAccountId.asKnown().isPresent) 1 else 0) +
                (workspaceRole.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                serviceAccountId == other.serviceAccountId &&
                workspaceRole == other.workspaceRole &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(serviceAccountId, workspaceRole, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{serviceAccountId=$serviceAccountId, workspaceRole=$workspaceRole, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ServiceAccountAddParams &&
            workspaceId == other.workspaceId &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(workspaceId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ServiceAccountAddParams{workspaceId=$workspaceId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
