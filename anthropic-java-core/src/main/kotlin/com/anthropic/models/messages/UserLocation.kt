// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

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
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The city of the user.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun city(): Optional<String> = city.getOptional("city")

    /**
     * The two letter [ISO country code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) of the
     * user.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun country(): Optional<String> = country.getOptional("country")

    /**
     * The region of the user.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun region(): Optional<String> = region.getOptional("region")

    /**
     * The [IANA timezone](https://nodatime.org/TimeZones) of the user.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
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
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
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
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun city(city: JsonField<String>) = apply { this.city = city }

        /**
         * The two letter [ISO country code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) of
         * the user.
         */
        fun country(country: String?) = country(JsonField.ofNullable(country))

        /** Alias for calling [Builder.country] with `country.orElse(null)`. */
        fun country(country: Optional<String>) = country(country.getOrNull())

        /**
         * Sets [Builder.country] to an arbitrary JSON value.
         *
         * You should usually call [Builder.country] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun country(country: JsonField<String>) = apply { this.country = country }

        /** The region of the user. */
        fun region(region: String?) = region(JsonField.ofNullable(region))

        /** Alias for calling [Builder.region] with `region.orElse(null)`. */
        fun region(region: Optional<String>) = region(region.getOrNull())

        /**
         * Sets [Builder.region] to an arbitrary JSON value.
         *
         * You should usually call [Builder.region] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun region(region: JsonField<String>) = apply { this.region = region }

        /** The [IANA timezone](https://nodatime.org/TimeZones) of the user. */
        fun timezone(timezone: String?) = timezone(JsonField.ofNullable(timezone))

        /** Alias for calling [Builder.timezone] with `timezone.orElse(null)`. */
        fun timezone(timezone: Optional<String>) = timezone(timezone.getOrNull())

        /**
         * Sets [Builder.timezone] to an arbitrary JSON value.
         *
         * You should usually call [Builder.timezone] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
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
            UserLocation(type, city, country, region, timezone, additionalProperties.toMutableMap())
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
     * Returns a score indicating how many valid values are contained in this object recursively.
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
