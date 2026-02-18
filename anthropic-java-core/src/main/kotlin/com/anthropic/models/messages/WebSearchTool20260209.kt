// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
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

class WebSearchTool20260209
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val name: JsonValue,
    private val type: JsonValue,
    private val allowedCallers: JsonField<List<AllowedCaller>>,
    private val allowedDomains: JsonField<List<String>>,
    private val blockedDomains: JsonField<List<String>>,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val deferLoading: JsonField<Boolean>,
    private val maxUses: JsonField<Long>,
    private val strict: JsonField<Boolean>,
    private val userLocation: JsonField<UserLocation>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("allowed_callers")
        @ExcludeMissing
        allowedCallers: JsonField<List<AllowedCaller>> = JsonMissing.of(),
        @JsonProperty("allowed_domains")
        @ExcludeMissing
        allowedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("blocked_domains")
        @ExcludeMissing
        blockedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("defer_loading")
        @ExcludeMissing
        deferLoading: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("max_uses") @ExcludeMissing maxUses: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("strict") @ExcludeMissing strict: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("user_location")
        @ExcludeMissing
        userLocation: JsonField<UserLocation> = JsonMissing.of(),
    ) : this(
        name,
        type,
        allowedCallers,
        allowedDomains,
        blockedDomains,
        cacheControl,
        deferLoading,
        maxUses,
        strict,
        userLocation,
        mutableMapOf(),
    )

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in `tool_use` blocks.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_search")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_search_20260209")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun allowedCallers(): Optional<List<AllowedCaller>> =
        allowedCallers.getOptional("allowed_callers")

    /**
     * If provided, only these domains will be included in results. Cannot be used alongside
     * `blocked_domains`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun allowedDomains(): Optional<List<String>> = allowedDomains.getOptional("allowed_domains")

    /**
     * If provided, these domains will never appear in results. Cannot be used alongside
     * `allowed_domains`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun blockedDomains(): Optional<List<String>> = blockedDomains.getOptional("blocked_domains")

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<CacheControlEphemeral> = cacheControl.getOptional("cache_control")

    /**
     * If true, tool will not be included in initial system prompt. Only loaded when returned via
     * tool_reference from tool search.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun deferLoading(): Optional<Boolean> = deferLoading.getOptional("defer_loading")

    /**
     * Maximum number of times the tool can be used in the API request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxUses(): Optional<Long> = maxUses.getOptional("max_uses")

    /**
     * When true, guarantees schema validation on tool names and inputs
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun strict(): Optional<Boolean> = strict.getOptional("strict")

    /**
     * Parameters for the user's location. Used to provide more relevant search results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userLocation(): Optional<UserLocation> = userLocation.getOptional("user_location")

    /**
     * Returns the raw JSON value of [allowedCallers].
     *
     * Unlike [allowedCallers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_callers")
    @ExcludeMissing
    fun _allowedCallers(): JsonField<List<AllowedCaller>> = allowedCallers

    /**
     * Returns the raw JSON value of [allowedDomains].
     *
     * Unlike [allowedDomains], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_domains")
    @ExcludeMissing
    fun _allowedDomains(): JsonField<List<String>> = allowedDomains

    /**
     * Returns the raw JSON value of [blockedDomains].
     *
     * Unlike [blockedDomains], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("blocked_domains")
    @ExcludeMissing
    fun _blockedDomains(): JsonField<List<String>> = blockedDomains

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [deferLoading].
     *
     * Unlike [deferLoading], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("defer_loading")
    @ExcludeMissing
    fun _deferLoading(): JsonField<Boolean> = deferLoading

    /**
     * Returns the raw JSON value of [maxUses].
     *
     * Unlike [maxUses], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_uses") @ExcludeMissing fun _maxUses(): JsonField<Long> = maxUses

    /**
     * Returns the raw JSON value of [strict].
     *
     * Unlike [strict], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("strict") @ExcludeMissing fun _strict(): JsonField<Boolean> = strict

    /**
     * Returns the raw JSON value of [userLocation].
     *
     * Unlike [userLocation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_location")
    @ExcludeMissing
    fun _userLocation(): JsonField<UserLocation> = userLocation

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

        /** Returns a mutable builder for constructing an instance of [WebSearchTool20260209]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WebSearchTool20260209]. */
    class Builder internal constructor() {

        private var name: JsonValue = JsonValue.from("web_search")
        private var type: JsonValue = JsonValue.from("web_search_20260209")
        private var allowedCallers: JsonField<MutableList<AllowedCaller>>? = null
        private var allowedDomains: JsonField<MutableList<String>>? = null
        private var blockedDomains: JsonField<MutableList<String>>? = null
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var deferLoading: JsonField<Boolean> = JsonMissing.of()
        private var maxUses: JsonField<Long> = JsonMissing.of()
        private var strict: JsonField<Boolean> = JsonMissing.of()
        private var userLocation: JsonField<UserLocation> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(webSearchTool20260209: WebSearchTool20260209) = apply {
            name = webSearchTool20260209.name
            type = webSearchTool20260209.type
            allowedCallers = webSearchTool20260209.allowedCallers.map { it.toMutableList() }
            allowedDomains = webSearchTool20260209.allowedDomains.map { it.toMutableList() }
            blockedDomains = webSearchTool20260209.blockedDomains.map { it.toMutableList() }
            cacheControl = webSearchTool20260209.cacheControl
            deferLoading = webSearchTool20260209.deferLoading
            maxUses = webSearchTool20260209.maxUses
            strict = webSearchTool20260209.strict
            userLocation = webSearchTool20260209.userLocation
            additionalProperties = webSearchTool20260209.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_search")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun name(name: JsonValue) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_search_20260209")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun allowedCallers(allowedCallers: List<AllowedCaller>) =
            allowedCallers(JsonField.of(allowedCallers))

        /**
         * Sets [Builder.allowedCallers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedCallers] with a well-typed `List<AllowedCaller>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun allowedCallers(allowedCallers: JsonField<List<AllowedCaller>>) = apply {
            this.allowedCallers = allowedCallers.map { it.toMutableList() }
        }

        /**
         * Adds a single [AllowedCaller] to [allowedCallers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedCaller(allowedCaller: AllowedCaller) = apply {
            allowedCallers =
                (allowedCallers ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedCallers", it).add(allowedCaller)
                }
        }

        /**
         * If provided, only these domains will be included in results. Cannot be used alongside
         * `blocked_domains`.
         */
        fun allowedDomains(allowedDomains: List<String>?) =
            allowedDomains(JsonField.ofNullable(allowedDomains))

        /** Alias for calling [Builder.allowedDomains] with `allowedDomains.orElse(null)`. */
        fun allowedDomains(allowedDomains: Optional<List<String>>) =
            allowedDomains(allowedDomains.getOrNull())

        /**
         * Sets [Builder.allowedDomains] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedDomains] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowedDomains(allowedDomains: JsonField<List<String>>) = apply {
            this.allowedDomains = allowedDomains.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [allowedDomains].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedDomain(allowedDomain: String) = apply {
            allowedDomains =
                (allowedDomains ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedDomains", it).add(allowedDomain)
                }
        }

        /**
         * If provided, these domains will never appear in results. Cannot be used alongside
         * `allowed_domains`.
         */
        fun blockedDomains(blockedDomains: List<String>?) =
            blockedDomains(JsonField.ofNullable(blockedDomains))

        /** Alias for calling [Builder.blockedDomains] with `blockedDomains.orElse(null)`. */
        fun blockedDomains(blockedDomains: Optional<List<String>>) =
            blockedDomains(blockedDomains.getOrNull())

        /**
         * Sets [Builder.blockedDomains] to an arbitrary JSON value.
         *
         * You should usually call [Builder.blockedDomains] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun blockedDomains(blockedDomains: JsonField<List<String>>) = apply {
            this.blockedDomains = blockedDomains.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [blockedDomains].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBlockedDomain(blockedDomain: String) = apply {
            blockedDomains =
                (blockedDomains ?: JsonField.of(mutableListOf())).also {
                    checkKnown("blockedDomains", it).add(blockedDomain)
                }
        }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        /**
         * If true, tool will not be included in initial system prompt. Only loaded when returned
         * via tool_reference from tool search.
         */
        fun deferLoading(deferLoading: Boolean) = deferLoading(JsonField.of(deferLoading))

        /**
         * Sets [Builder.deferLoading] to an arbitrary JSON value.
         *
         * You should usually call [Builder.deferLoading] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun deferLoading(deferLoading: JsonField<Boolean>) = apply {
            this.deferLoading = deferLoading
        }

        /** Maximum number of times the tool can be used in the API request. */
        fun maxUses(maxUses: Long?) = maxUses(JsonField.ofNullable(maxUses))

        /**
         * Alias for [Builder.maxUses].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxUses(maxUses: Long) = maxUses(maxUses as Long?)

        /** Alias for calling [Builder.maxUses] with `maxUses.orElse(null)`. */
        fun maxUses(maxUses: Optional<Long>) = maxUses(maxUses.getOrNull())

        /**
         * Sets [Builder.maxUses] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxUses] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun maxUses(maxUses: JsonField<Long>) = apply { this.maxUses = maxUses }

        /** When true, guarantees schema validation on tool names and inputs */
        fun strict(strict: Boolean) = strict(JsonField.of(strict))

        /**
         * Sets [Builder.strict] to an arbitrary JSON value.
         *
         * You should usually call [Builder.strict] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun strict(strict: JsonField<Boolean>) = apply { this.strict = strict }

        /** Parameters for the user's location. Used to provide more relevant search results. */
        fun userLocation(userLocation: UserLocation?) =
            userLocation(JsonField.ofNullable(userLocation))

        /** Alias for calling [Builder.userLocation] with `userLocation.orElse(null)`. */
        fun userLocation(userLocation: Optional<UserLocation>) =
            userLocation(userLocation.getOrNull())

        /**
         * Sets [Builder.userLocation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userLocation] with a well-typed [UserLocation] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun userLocation(userLocation: JsonField<UserLocation>) = apply {
            this.userLocation = userLocation
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
         * Returns an immutable instance of [WebSearchTool20260209].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): WebSearchTool20260209 =
            WebSearchTool20260209(
                name,
                type,
                (allowedCallers ?: JsonMissing.of()).map { it.toImmutable() },
                (allowedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                (blockedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                cacheControl,
                deferLoading,
                maxUses,
                strict,
                userLocation,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): WebSearchTool20260209 = apply {
        if (validated) {
            return@apply
        }

        _name().let {
            if (it != JsonValue.from("web_search")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("web_search_20260209")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        allowedCallers().ifPresent { it.forEach { it.validate() } }
        allowedDomains()
        blockedDomains()
        cacheControl().ifPresent { it.validate() }
        deferLoading()
        maxUses()
        strict()
        userLocation().ifPresent { it.validate() }
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
        name.let { if (it == JsonValue.from("web_search")) 1 else 0 } +
            type.let { if (it == JsonValue.from("web_search_20260209")) 1 else 0 } +
            (allowedCallers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (allowedDomains.asKnown().getOrNull()?.size ?: 0) +
            (blockedDomains.asKnown().getOrNull()?.size ?: 0) +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (if (deferLoading.asKnown().isPresent) 1 else 0) +
            (if (maxUses.asKnown().isPresent) 1 else 0) +
            (if (strict.asKnown().isPresent) 1 else 0) +
            (userLocation.asKnown().getOrNull()?.validity() ?: 0)

    class AllowedCaller @JsonCreator private constructor(private val value: JsonField<String>) :
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

            @JvmField val DIRECT = of("direct")

            @JvmField val CODE_EXECUTION_20250825 = of("code_execution_20250825")

            @JvmField val CODE_EXECUTION_20260120 = of("code_execution_20260120")

            @JvmStatic fun of(value: String) = AllowedCaller(JsonField.of(value))
        }

        /** An enum containing [AllowedCaller]'s known values. */
        enum class Known {
            DIRECT,
            CODE_EXECUTION_20250825,
            CODE_EXECUTION_20260120,
        }

        /**
         * An enum containing [AllowedCaller]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [AllowedCaller] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            DIRECT,
            CODE_EXECUTION_20250825,
            CODE_EXECUTION_20260120,
            /**
             * An enum member indicating that [AllowedCaller] was instantiated with an unknown
             * value.
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
                DIRECT -> Value.DIRECT
                CODE_EXECUTION_20250825 -> Value.CODE_EXECUTION_20250825
                CODE_EXECUTION_20260120 -> Value.CODE_EXECUTION_20260120
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
                DIRECT -> Known.DIRECT
                CODE_EXECUTION_20250825 -> Known.CODE_EXECUTION_20250825
                CODE_EXECUTION_20260120 -> Known.CODE_EXECUTION_20260120
                else -> throw AnthropicInvalidDataException("Unknown AllowedCaller: $value")
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

        fun validate(): AllowedCaller = apply {
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

            return other is AllowedCaller && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Parameters for the user's location. Used to provide more relevant search results. */
    class UserLocation
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val type: JsonValue,
        private val city: JsonField<String>,
        private val country: JsonField<String>,
        private val region: JsonField<String>,
        private val timezone: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
            @JsonProperty("city") @ExcludeMissing city: JsonField<String> = JsonMissing.of(),
            @JsonProperty("country") @ExcludeMissing country: JsonField<String> = JsonMissing.of(),
            @JsonProperty("region") @ExcludeMissing region: JsonField<String> = JsonMissing.of(),
            @JsonProperty("timezone") @ExcludeMissing timezone: JsonField<String> = JsonMissing.of(),
        ) : this(type, city, country, region, timezone, mutableMapOf())

        /**
         * Expected to always return the following:
         * ```java
         * JsonValue.from("approximate")
         * ```
         *
         * However, this method can be useful for debugging and logging (e.g. if the server
         * responded with an unexpected value).
         */
        @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

        /**
         * The city of the user.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun city(): Optional<String> = city.getOptional("city")

        /**
         * The two letter [ISO country code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) of
         * the user.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun country(): Optional<String> = country.getOptional("country")

        /**
         * The region of the user.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun region(): Optional<String> = region.getOptional("region")

        /**
         * The [IANA timezone](https://nodatime.org/TimeZones) of the user.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun timezone(): Optional<String> = timezone.getOptional("timezone")

        /**
         * Returns the raw JSON value of [city].
         *
         * Unlike [city], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("city") @ExcludeMissing fun _city(): JsonField<String> = city

        /**
         * Returns the raw JSON value of [country].
         *
         * Unlike [country], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("country") @ExcludeMissing fun _country(): JsonField<String> = country

        /**
         * Returns the raw JSON value of [region].
         *
         * Unlike [region], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("region") @ExcludeMissing fun _region(): JsonField<String> = region

        /**
         * Returns the raw JSON value of [timezone].
         *
         * Unlike [timezone], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("timezone") @ExcludeMissing fun _timezone(): JsonField<String> = timezone

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

            /** Returns a mutable builder for constructing an instance of [UserLocation]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [UserLocation]. */
        class Builder internal constructor() {

            private var type: JsonValue = JsonValue.from("approximate")
            private var city: JsonField<String> = JsonMissing.of()
            private var country: JsonField<String> = JsonMissing.of()
            private var region: JsonField<String> = JsonMissing.of()
            private var timezone: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(userLocation: UserLocation) = apply {
                type = userLocation.type
                city = userLocation.city
                country = userLocation.country
                region = userLocation.region
                timezone = userLocation.timezone
                additionalProperties = userLocation.additionalProperties.toMutableMap()
            }

            /**
             * Sets the field to an arbitrary JSON value.
             *
             * It is usually unnecessary to call this method because the field defaults to the
             * following:
             * ```java
             * JsonValue.from("approximate")
             * ```
             *
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun type(type: JsonValue) = apply { this.type = type }

            /** The city of the user. */
            fun city(city: String?) = city(JsonField.ofNullable(city))

            /** Alias for calling [Builder.city] with `city.orElse(null)`. */
            fun city(city: Optional<String>) = city(city.getOrNull())

            /**
             * Sets [Builder.city] to an arbitrary JSON value.
             *
             * You should usually call [Builder.city] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun city(city: JsonField<String>) = apply { this.city = city }

            /**
             * The two letter [ISO country code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
             * of the user.
             */
            fun country(country: String?) = country(JsonField.ofNullable(country))

            /** Alias for calling [Builder.country] with `country.orElse(null)`. */
            fun country(country: Optional<String>) = country(country.getOrNull())

            /**
             * Sets [Builder.country] to an arbitrary JSON value.
             *
             * You should usually call [Builder.country] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun country(country: JsonField<String>) = apply { this.country = country }

            /** The region of the user. */
            fun region(region: String?) = region(JsonField.ofNullable(region))

            /** Alias for calling [Builder.region] with `region.orElse(null)`. */
            fun region(region: Optional<String>) = region(region.getOrNull())

            /**
             * Sets [Builder.region] to an arbitrary JSON value.
             *
             * You should usually call [Builder.region] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun region(region: JsonField<String>) = apply { this.region = region }

            /** The [IANA timezone](https://nodatime.org/TimeZones) of the user. */
            fun timezone(timezone: String?) = timezone(JsonField.ofNullable(timezone))

            /** Alias for calling [Builder.timezone] with `timezone.orElse(null)`. */
            fun timezone(timezone: Optional<String>) = timezone(timezone.getOrNull())

            /**
             * Sets [Builder.timezone] to an arbitrary JSON value.
             *
             * You should usually call [Builder.timezone] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun timezone(timezone: JsonField<String>) = apply { this.timezone = timezone }

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
             * Returns an immutable instance of [UserLocation].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): UserLocation =
                UserLocation(
                    type,
                    city,
                    country,
                    region,
                    timezone,
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): UserLocation = apply {
            if (validated) {
                return@apply
            }

            _type().let {
                if (it != JsonValue.from("approximate")) {
                    throw AnthropicInvalidDataException("'type' is invalid, received $it")
                }
            }
            city()
            country()
            region()
            timezone()
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
            type.let { if (it == JsonValue.from("approximate")) 1 else 0 } +
                (if (city.asKnown().isPresent) 1 else 0) +
                (if (country.asKnown().isPresent) 1 else 0) +
                (if (region.asKnown().isPresent) 1 else 0) +
                (if (timezone.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is UserLocation &&
                type == other.type &&
                city == other.city &&
                country == other.country &&
                region == other.region &&
                timezone == other.timezone &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(type, city, country, region, timezone, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "UserLocation{type=$type, city=$city, country=$country, region=$region, timezone=$timezone, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebSearchTool20260209 &&
            name == other.name &&
            type == other.type &&
            allowedCallers == other.allowedCallers &&
            allowedDomains == other.allowedDomains &&
            blockedDomains == other.blockedDomains &&
            cacheControl == other.cacheControl &&
            deferLoading == other.deferLoading &&
            maxUses == other.maxUses &&
            strict == other.strict &&
            userLocation == other.userLocation &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            name,
            type,
            allowedCallers,
            allowedDomains,
            blockedDomains,
            cacheControl,
            deferLoading,
            maxUses,
            strict,
            userLocation,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WebSearchTool20260209{name=$name, type=$type, allowedCallers=$allowedCallers, allowedDomains=$allowedDomains, blockedDomains=$blockedDomains, cacheControl=$cacheControl, deferLoading=$deferLoading, maxUses=$maxUses, strict=$strict, userLocation=$userLocation, additionalProperties=$additionalProperties}"
}
