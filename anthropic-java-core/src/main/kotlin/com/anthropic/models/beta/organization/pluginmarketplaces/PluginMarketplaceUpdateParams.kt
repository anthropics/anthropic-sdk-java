package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.Enum
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

/**
 * Set the default installation setting of one of the organization's own plugin marketplaces. Every
 * Plugin in it without a setting of its own gets this default as its organization-wide setting,
 * including Plugins added later.
 *
 * Pass it as `default_installation_preference`. A member's personal marketplace cannot be updated
 * here (403).
 *
 * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
 *
 * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
 * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in beta
 * and is available to Claude Enterprise organizations only. It is not available to Claude Platform
 * (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
 */
class PluginMarketplaceUpdateParams
private constructor(
    private val marketplaceId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the plugin marketplace (prefixed `marketplace_`). */
    fun marketplaceId(): Optional<String> = Optional.ofNullable(marketplaceId)

    /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The organization-wide installation setting every Plugin in the marketplace without one of its
     * own gets: one of `required`, `auto_install`, `available`, `not_available`. Once set it can be
     * changed but not removed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun defaultInstallationPreference(): DefaultInstallationPreference =
        body.defaultInstallationPreference()

    /**
     * Returns the raw JSON value of [defaultInstallationPreference].
     *
     * Unlike [defaultInstallationPreference], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    fun _defaultInstallationPreference(): JsonField<DefaultInstallationPreference> =
        body._defaultInstallationPreference()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [PluginMarketplaceUpdateParams].
         *
         * The following fields are required:
         * ```java
         * .defaultInstallationPreference()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [PluginMarketplaceUpdateParams]. */
    class Builder internal constructor() {

        private var marketplaceId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(pluginMarketplaceUpdateParams: PluginMarketplaceUpdateParams) = apply {
            marketplaceId = pluginMarketplaceUpdateParams.marketplaceId
            betas = pluginMarketplaceUpdateParams.betas?.toMutableList()
            body = pluginMarketplaceUpdateParams.body.toBuilder()
            additionalHeaders = pluginMarketplaceUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = pluginMarketplaceUpdateParams.additionalQueryParams.toBuilder()
        }

        /** ID of the plugin marketplace (prefixed `marketplace_`). */
        fun marketplaceId(marketplaceId: String?) = apply { this.marketplaceId = marketplaceId }

        /** Alias for calling [Builder.marketplaceId] with `marketplaceId.orElse(null)`. */
        fun marketplaceId(marketplaceId: Optional<String>) =
            marketplaceId(marketplaceId.getOrNull())

        /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
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
         * - [defaultInstallationPreference]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * The organization-wide installation setting every Plugin in the marketplace without one of
         * its own gets: one of `required`, `auto_install`, `available`, `not_available`. Once set
         * it can be changed but not removed.
         */
        fun defaultInstallationPreference(
            defaultInstallationPreference: DefaultInstallationPreference
        ) = apply { body.defaultInstallationPreference(defaultInstallationPreference) }

        /**
         * Sets [Builder.defaultInstallationPreference] to an arbitrary JSON value.
         *
         * You should usually call [Builder.defaultInstallationPreference] with a well-typed
         * [DefaultInstallationPreference] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun defaultInstallationPreference(
            defaultInstallationPreference: JsonField<DefaultInstallationPreference>
        ) = apply { body.defaultInstallationPreference(defaultInstallationPreference) }

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
         * Returns an immutable instance of [PluginMarketplaceUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .defaultInstallationPreference()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): PluginMarketplaceUpdateParams =
            PluginMarketplaceUpdateParams(
                marketplaceId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> marketplaceId ?: ""
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
                                .let { it + (listOf("ce-plugins-2026-09-01") - it) }
                                .joinToString(","),
                        )
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    /**
     * The plugin marketplace's default installation setting: its one settable field. Once a
     * marketplace has a default it keeps one, so `null` is refused.
     */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val defaultInstallationPreference: JsonField<DefaultInstallationPreference>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("default_installation_preference")
            @ExcludeMissing
            defaultInstallationPreference: JsonField<DefaultInstallationPreference> =
                JsonMissing.of()
        ) : this(defaultInstallationPreference, mutableMapOf())

        /**
         * The organization-wide installation setting every Plugin in the marketplace without one of
         * its own gets: one of `required`, `auto_install`, `available`, `not_available`. Once set
         * it can be changed but not removed.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun defaultInstallationPreference(): DefaultInstallationPreference =
            defaultInstallationPreference.getRequired("default_installation_preference")

        /**
         * Returns the raw JSON value of [defaultInstallationPreference].
         *
         * Unlike [defaultInstallationPreference], this method doesn't throw if the JSON field has
         * an unexpected type.
         */
        @JsonProperty("default_installation_preference")
        @ExcludeMissing
        fun _defaultInstallationPreference(): JsonField<DefaultInstallationPreference> =
            defaultInstallationPreference

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
             * .defaultInstallationPreference()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required
             * [defaultInstallationPreference] set to the given value.
             */
            @JvmStatic
            fun of(defaultInstallationPreference: DefaultInstallationPreference) =
                builder().defaultInstallationPreference(defaultInstallationPreference).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var defaultInstallationPreference: JsonField<DefaultInstallationPreference>? =
                null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                defaultInstallationPreference = body.defaultInstallationPreference
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The organization-wide installation setting every Plugin in the marketplace without
             * one of its own gets: one of `required`, `auto_install`, `available`, `not_available`.
             * Once set it can be changed but not removed.
             */
            fun defaultInstallationPreference(
                defaultInstallationPreference: DefaultInstallationPreference
            ) = defaultInstallationPreference(JsonField.of(defaultInstallationPreference))

            /**
             * Sets [Builder.defaultInstallationPreference] to an arbitrary JSON value.
             *
             * You should usually call [Builder.defaultInstallationPreference] with a well-typed
             * [DefaultInstallationPreference] value instead. This method is primarily for setting
             * the field to an undocumented or not yet supported value.
             */
            fun defaultInstallationPreference(
                defaultInstallationPreference: JsonField<DefaultInstallationPreference>
            ) = apply { this.defaultInstallationPreference = defaultInstallationPreference }

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
             * .defaultInstallationPreference()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("defaultInstallationPreference", defaultInstallationPreference),
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

            defaultInstallationPreference().validate()
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
            (defaultInstallationPreference.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                defaultInstallationPreference == other.defaultInstallationPreference &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(defaultInstallationPreference, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{defaultInstallationPreference=$defaultInstallationPreference, additionalProperties=$additionalProperties}"
    }

    /**
     * The organization-wide installation setting every Plugin in the marketplace without one of its
     * own gets: one of `required`, `auto_install`, `available`, `not_available`. Once set it can be
     * changed but not removed.
     */
    class DefaultInstallationPreference private constructor(private val value: JsonField<String>) :
        Enum {

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

            @JvmField val AUTO_INSTALL = DefaultInstallationPreference(JsonField.of("auto_install"))

            @JvmField val AVAILABLE = DefaultInstallationPreference(JsonField.of("available"))

            @JvmField
            val NOT_AVAILABLE = DefaultInstallationPreference(JsonField.of("not_available"))

            @JvmField val REQUIRED = DefaultInstallationPreference(JsonField.of("required"))

            @JvmStatic
            fun of(value: String): DefaultInstallationPreference =
                // Intern known values so `==` works
                when (value) {
                    "auto_install" -> AUTO_INSTALL
                    "available" -> AVAILABLE
                    "not_available" -> NOT_AVAILABLE
                    "required" -> REQUIRED
                    else -> DefaultInstallationPreference(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): DefaultInstallationPreference =
                value.asString().getOrNull()?.let { of(it) } ?: DefaultInstallationPreference(value)
        }

        /** An enum containing [DefaultInstallationPreference]'s known values. */
        enum class Known {
            AUTO_INSTALL,
            AVAILABLE,
            NOT_AVAILABLE,
            REQUIRED,
        }

        /**
         * An enum containing [DefaultInstallationPreference]'s known values, as well as an
         * [_UNKNOWN] member.
         *
         * An instance of [DefaultInstallationPreference] can contain an unknown value in a couple
         * of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AUTO_INSTALL,
            AVAILABLE,
            NOT_AVAILABLE,
            REQUIRED,
            /**
             * An enum member indicating that [DefaultInstallationPreference] was instantiated with
             * an unknown value.
             */
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
                AUTO_INSTALL -> Value.AUTO_INSTALL
                AVAILABLE -> Value.AVAILABLE
                NOT_AVAILABLE -> Value.NOT_AVAILABLE
                REQUIRED -> Value.REQUIRED
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
                AUTO_INSTALL -> Known.AUTO_INSTALL
                AVAILABLE -> Known.AVAILABLE
                NOT_AVAILABLE -> Known.NOT_AVAILABLE
                REQUIRED -> Known.REQUIRED
                else ->
                    throw AnthropicInvalidDataException(
                        "Unknown DefaultInstallationPreference: $value"
                    )
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
        fun validate(): DefaultInstallationPreference = apply {
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

            return other is DefaultInstallationPreference && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PluginMarketplaceUpdateParams &&
            marketplaceId == other.marketplaceId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(marketplaceId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "PluginMarketplaceUpdateParams{marketplaceId=$marketplaceId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
