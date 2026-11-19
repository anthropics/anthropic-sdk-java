package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkKnown
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
 * Change the organization's Claude Code managed settings mode, the RBAC Group order, or both, in
 * one atomic write.
 *
 * To change the order, send `rbac_group_order` with the `rbac_group_order_version` from the read
 * the change is based on. If the order has changed since that read, the request returns `409`: read
 * it again and retry. To switch modes only if the order has not changed, send the current
 * `rbac_group_order` and `rbac_group_order_version` with the new `mode`.
 *
 * A switch to `rbac_groups` mode with no RBAC Group settings and no default settings copies the
 * organization settings into a new default, in the same write, so members keep the settings they
 * had. The copy does not follow later changes to the organization settings. If the organization
 * settings cannot be copied, the request returns `400` and changes nothing. If they cannot be read,
 * are over 1 MiB or are not valid, setting the default settings first avoids the copy.
 *
 * After a switch to `rbac_groups` mode, a member in no listed RBAC Group receives the default
 * settings, or no settings if no default is set, not the organization settings.
 *
 * **Accepted credentials:** an organization-level Admin API key with the
 * `write:claude_code_managed_settings` scope.
 *
 * Every request must include the beta header `anthropic-beta:
 * ce-claude-code-managed-settings-2026-10-06`. A request without it, or from an organization not
 * enabled for this beta, returns `404`. The Claude Code Managed Settings API is in beta and is
 * available to Claude Enterprise organizations only.
 *
 * A write that returns `503` may have applied. Retrying it is safe.
 */
class ConfigurationUpdateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * This endpoint is in beta: requests must send `ce-claude-code-managed-settings-2026-10-06` in
     * this header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The new mode, as an object such as `{"type": "rbac_groups"}`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mode(): Optional<BetaClaudeCodeManagedSettingsModeParam> = body.mode()

    /**
     * Every RBAC Group that has settings, each listed once, in the new order, highest precedence
     * first. Requires `rbac_group_order_version`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rbacGroupOrder(): Optional<List<String>> = body.rbacGroupOrder()

    /**
     * The `rbac_group_order_version` from the read this change is based on. Requires
     * `rbac_group_order`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rbacGroupOrderVersion(): Optional<String> = body.rbacGroupOrderVersion()

    /**
     * Returns the raw JSON value of [mode].
     *
     * Unlike [mode], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _mode(): JsonField<BetaClaudeCodeManagedSettingsModeParam> = body._mode()

    /**
     * Returns the raw JSON value of [rbacGroupOrder].
     *
     * Unlike [rbacGroupOrder], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _rbacGroupOrder(): JsonField<List<String>> = body._rbacGroupOrder()

    /**
     * Returns the raw JSON value of [rbacGroupOrderVersion].
     *
     * Unlike [rbacGroupOrderVersion], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _rbacGroupOrderVersion(): JsonField<String> = body._rbacGroupOrderVersion()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): ConfigurationUpdateParams = builder().build()

        /**
         * Returns a mutable builder for constructing an instance of [ConfigurationUpdateParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ConfigurationUpdateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(configurationUpdateParams: ConfigurationUpdateParams) = apply {
            betas = configurationUpdateParams.betas?.toMutableList()
            body = configurationUpdateParams.body.toBuilder()
            additionalHeaders = configurationUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = configurationUpdateParams.additionalQueryParams.toBuilder()
        }

        /**
         * This endpoint is in beta: requests must send `ce-claude-code-managed-settings-2026-10-06`
         * in this header.
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
         * - [mode]
         * - [rbacGroupOrder]
         * - [rbacGroupOrderVersion]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** The new mode, as an object such as `{"type": "rbac_groups"}`. */
        fun mode(mode: BetaClaudeCodeManagedSettingsModeParam) = apply { body.mode(mode) }

        /**
         * Sets [Builder.mode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mode] with a well-typed
         * [BetaClaudeCodeManagedSettingsModeParam] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun mode(mode: JsonField<BetaClaudeCodeManagedSettingsModeParam>) = apply {
            body.mode(mode)
        }

        /**
         * Alias for calling [mode] with
         * `BetaClaudeCodeManagedSettingsModeParam.ofOrganization(organization)`.
         */
        fun mode(organization: BetaClaudeCodeManagedSettingsOrganizationModeParam) = apply {
            body.mode(organization)
        }

        /**
         * Alias for calling [mode] with
         * `BetaClaudeCodeManagedSettingsModeParam.ofRbacGroups(rbacGroups)`.
         */
        fun mode(rbacGroups: BetaClaudeCodeManagedSettingsRbacGroupsModeParam) = apply {
            body.mode(rbacGroups)
        }

        /**
         * Every RBAC Group that has settings, each listed once, in the new order, highest
         * precedence first. Requires `rbac_group_order_version`.
         */
        fun rbacGroupOrder(rbacGroupOrder: List<String>) = apply {
            body.rbacGroupOrder(rbacGroupOrder)
        }

        /**
         * Sets [Builder.rbacGroupOrder] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupOrder] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun rbacGroupOrder(rbacGroupOrder: JsonField<List<String>>) = apply {
            body.rbacGroupOrder(rbacGroupOrder)
        }

        /**
         * Adds a single [String] to [Builder.rbacGroupOrder].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRbacGroupOrder(rbacGroupOrder: String) = apply {
            body.addRbacGroupOrder(rbacGroupOrder)
        }

        /**
         * The `rbac_group_order_version` from the read this change is based on. Requires
         * `rbac_group_order`.
         */
        fun rbacGroupOrderVersion(rbacGroupOrderVersion: String) = apply {
            body.rbacGroupOrderVersion(rbacGroupOrderVersion)
        }

        /**
         * Sets [Builder.rbacGroupOrderVersion] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupOrderVersion] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun rbacGroupOrderVersion(rbacGroupOrderVersion: JsonField<String>) = apply {
            body.rbacGroupOrderVersion(rbacGroupOrderVersion)
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
         * Returns an immutable instance of [ConfigurationUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ConfigurationUpdateParams =
            ConfigurationUpdateParams(
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
                betas?.let {
                    if (it.isNotEmpty()) {
                        put(
                            "anthropic-beta",
                            it.map { it.toString() }
                                .let {
                                    it + (listOf("ce-claude-code-managed-settings-2026-10-06") - it)
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
            name = "anthropic.organization.claude_code.managed_settings.configuration.update",
            route = "/v1/organizations/claude_code/managed_settings/configuration",
        )

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val mode: JsonField<BetaClaudeCodeManagedSettingsModeParam>,
        private val rbacGroupOrder: JsonField<List<String>>,
        private val rbacGroupOrderVersion: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("mode")
            @ExcludeMissing
            mode: JsonField<BetaClaudeCodeManagedSettingsModeParam> = JsonMissing.of(),
            @JsonProperty("rbac_group_order")
            @ExcludeMissing
            rbacGroupOrder: JsonField<List<String>> = JsonMissing.of(),
            @JsonProperty("rbac_group_order_version")
            @ExcludeMissing
            rbacGroupOrderVersion: JsonField<String> = JsonMissing.of(),
        ) : this(mode, rbacGroupOrder, rbacGroupOrderVersion, mutableMapOf())

        /**
         * The new mode, as an object such as `{"type": "rbac_groups"}`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun mode(): Optional<BetaClaudeCodeManagedSettingsModeParam> = mode.getOptional("mode")

        /**
         * Every RBAC Group that has settings, each listed once, in the new order, highest
         * precedence first. Requires `rbac_group_order_version`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun rbacGroupOrder(): Optional<List<String>> =
            rbacGroupOrder.getOptional("rbac_group_order")

        /**
         * The `rbac_group_order_version` from the read this change is based on. Requires
         * `rbac_group_order`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun rbacGroupOrderVersion(): Optional<String> =
            rbacGroupOrderVersion.getOptional("rbac_group_order_version")

        /**
         * Returns the raw JSON value of [mode].
         *
         * Unlike [mode], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("mode")
        @ExcludeMissing
        fun _mode(): JsonField<BetaClaudeCodeManagedSettingsModeParam> = mode

        /**
         * Returns the raw JSON value of [rbacGroupOrder].
         *
         * Unlike [rbacGroupOrder], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("rbac_group_order")
        @ExcludeMissing
        fun _rbacGroupOrder(): JsonField<List<String>> = rbacGroupOrder

        /**
         * Returns the raw JSON value of [rbacGroupOrderVersion].
         *
         * Unlike [rbacGroupOrderVersion], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("rbac_group_order_version")
        @ExcludeMissing
        fun _rbacGroupOrderVersion(): JsonField<String> = rbacGroupOrderVersion

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

            private var mode: JsonField<BetaClaudeCodeManagedSettingsModeParam> = JsonMissing.of()
            private var rbacGroupOrder: JsonField<MutableList<String>>? = null
            private var rbacGroupOrderVersion: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                mode = body.mode
                rbacGroupOrder =
                    body.rbacGroupOrder.map { it.toMutableList() }.takeUnless { it.isMissing() }
                rbacGroupOrderVersion = body.rbacGroupOrderVersion
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** The new mode, as an object such as `{"type": "rbac_groups"}`. */
            fun mode(mode: BetaClaudeCodeManagedSettingsModeParam) = mode(JsonField.of(mode))

            /**
             * Sets [Builder.mode] to an arbitrary JSON value.
             *
             * You should usually call [Builder.mode] with a well-typed
             * [BetaClaudeCodeManagedSettingsModeParam] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun mode(mode: JsonField<BetaClaudeCodeManagedSettingsModeParam>) = apply {
                this.mode = mode
            }

            /**
             * Alias for calling [mode] with
             * `BetaClaudeCodeManagedSettingsModeParam.ofOrganization(organization)`.
             */
            fun mode(organization: BetaClaudeCodeManagedSettingsOrganizationModeParam) =
                mode(BetaClaudeCodeManagedSettingsModeParam.ofOrganization(organization))

            /**
             * Alias for calling [mode] with
             * `BetaClaudeCodeManagedSettingsModeParam.ofRbacGroups(rbacGroups)`.
             */
            fun mode(rbacGroups: BetaClaudeCodeManagedSettingsRbacGroupsModeParam) =
                mode(BetaClaudeCodeManagedSettingsModeParam.ofRbacGroups(rbacGroups))

            /**
             * Every RBAC Group that has settings, each listed once, in the new order, highest
             * precedence first. Requires `rbac_group_order_version`.
             */
            fun rbacGroupOrder(rbacGroupOrder: List<String>) =
                rbacGroupOrder(JsonField.of(rbacGroupOrder))

            /**
             * Sets [Builder.rbacGroupOrder] to an arbitrary JSON value.
             *
             * You should usually call [Builder.rbacGroupOrder] with a well-typed `List<String>`
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun rbacGroupOrder(rbacGroupOrder: JsonField<List<String>>) = apply {
                this.rbacGroupOrder = rbacGroupOrder.map { it.toMutableList() }
            }

            /**
             * Adds a single [String] to [Builder.rbacGroupOrder].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addRbacGroupOrder(rbacGroupOrder: String) = apply {
                this.rbacGroupOrder =
                    (this.rbacGroupOrder ?: JsonField.of(mutableListOf())).also {
                        checkKnown("rbacGroupOrder", it).add(rbacGroupOrder)
                    }
            }

            /**
             * The `rbac_group_order_version` from the read this change is based on. Requires
             * `rbac_group_order`.
             */
            fun rbacGroupOrderVersion(rbacGroupOrderVersion: String) =
                rbacGroupOrderVersion(JsonField.of(rbacGroupOrderVersion))

            /**
             * Sets [Builder.rbacGroupOrderVersion] to an arbitrary JSON value.
             *
             * You should usually call [Builder.rbacGroupOrderVersion] with a well-typed [String]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun rbacGroupOrderVersion(rbacGroupOrderVersion: JsonField<String>) = apply {
                this.rbacGroupOrderVersion = rbacGroupOrderVersion
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
             */
            fun build(): Body =
                Body(
                    mode,
                    (rbacGroupOrder ?: JsonMissing.of()).map { it.toImmutable() },
                    rbacGroupOrderVersion,
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

            mode().ifPresent { it.validate() }
            rbacGroupOrder()
            rbacGroupOrderVersion()
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
            (mode.asKnown().getOrNull()?.validity() ?: 0) +
                (rbacGroupOrder.asKnown().getOrNull()?.size ?: 0) +
                (if (rbacGroupOrderVersion.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                mode == other.mode &&
                rbacGroupOrder == other.rbacGroupOrder &&
                rbacGroupOrderVersion == other.rbacGroupOrderVersion &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(mode, rbacGroupOrder, rbacGroupOrderVersion, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{mode=$mode, rbacGroupOrder=$rbacGroupOrder, rbacGroupOrderVersion=$rbacGroupOrderVersion, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ConfigurationUpdateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ConfigurationUpdateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
