// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

class WebSearchTool20250305
private constructor(
    private val name: JsonValue,
    private val type: JsonValue,
    private val allowedDomains: JsonField<List<String>>,
    private val blockedDomains: JsonField<List<String>>,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val maxUses: JsonField<Long>,
    private val userLocation: JsonField<UserLocation>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("allowed_domains")
        @ExcludeMissing
        allowedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("blocked_domains")
        @ExcludeMissing
        blockedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("max_uses") @ExcludeMissing maxUses: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("user_location")
        @ExcludeMissing
        userLocation: JsonField<UserLocation> = JsonMissing.of(),
    ) : this(
        name,
        type,
        allowedDomains,
        blockedDomains,
        cacheControl,
        maxUses,
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
     * JsonValue.from("web_search_20250305")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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
     * Maximum number of times the tool can be used in the API request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxUses(): Optional<Long> = maxUses.getOptional("max_uses")

    /**
     * Parameters for the user's location. Used to provide more relevant search results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userLocation(): Optional<UserLocation> = userLocation.getOptional("user_location")

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
     * Returns the raw JSON value of [maxUses].
     *
     * Unlike [maxUses], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_uses") @ExcludeMissing fun _maxUses(): JsonField<Long> = maxUses

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

        /** Returns a mutable builder for constructing an instance of [WebSearchTool20250305]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WebSearchTool20250305]. */
    class Builder internal constructor() {

        private var name: JsonValue = JsonValue.from("web_search")
        private var type: JsonValue = JsonValue.from("web_search_20250305")
        private var allowedDomains: JsonField<MutableList<String>>? = null
        private var blockedDomains: JsonField<MutableList<String>>? = null
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var maxUses: JsonField<Long> = JsonMissing.of()
        private var userLocation: JsonField<UserLocation> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(webSearchTool20250305: WebSearchTool20250305) = apply {
            name = webSearchTool20250305.name
            type = webSearchTool20250305.type
            allowedDomains = webSearchTool20250305.allowedDomains.map { it.toMutableList() }
            blockedDomains = webSearchTool20250305.blockedDomains.map { it.toMutableList() }
            cacheControl = webSearchTool20250305.cacheControl
            maxUses = webSearchTool20250305.maxUses
            userLocation = webSearchTool20250305.userLocation
            additionalProperties = webSearchTool20250305.additionalProperties.toMutableMap()
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
         * JsonValue.from("web_search_20250305")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [WebSearchTool20250305].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): WebSearchTool20250305 =
            WebSearchTool20250305(
                name,
                type,
                (allowedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                (blockedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                cacheControl,
                maxUses,
                userLocation,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): WebSearchTool20250305 = apply {
        if (validated) {
            return@apply
        }

        _name().let {
            if (it != JsonValue.from("web_search")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("web_search_20250305")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        allowedDomains()
        blockedDomains()
        cacheControl().ifPresent { it.validate() }
        maxUses()
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
            type.let { if (it == JsonValue.from("web_search_20250305")) 1 else 0 } +
            (allowedDomains.asKnown().getOrNull()?.size ?: 0) +
            (blockedDomains.asKnown().getOrNull()?.size ?: 0) +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (if (maxUses.asKnown().isPresent) 1 else 0) +
            (userLocation.asKnown().getOrNull()?.validity() ?: 0)

    /** Parameters for the user's location. Used to provide more relevant search results. */
    class UserLocation
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

        return other is WebSearchTool20250305 &&
            name == other.name &&
            type == other.type &&
            allowedDomains == other.allowedDomains &&
            blockedDomains == other.blockedDomains &&
            cacheControl == other.cacheControl &&
            maxUses == other.maxUses &&
            userLocation == other.userLocation &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            name,
            type,
            allowedDomains,
            blockedDomains,
            cacheControl,
            maxUses,
            userLocation,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WebSearchTool20250305{name=$name, type=$type, allowedDomains=$allowedDomains, blockedDomains=$blockedDomains, cacheControl=$cacheControl, maxUses=$maxUses, userLocation=$userLocation, additionalProperties=$additionalProperties}"
}
