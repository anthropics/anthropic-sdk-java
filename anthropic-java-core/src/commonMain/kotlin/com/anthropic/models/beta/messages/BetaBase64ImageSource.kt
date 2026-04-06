// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.Enum
import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.errors.ApiInvalidDataException
import kotlinx.kmp.util.core.json.JsonAnyGetter
import kotlinx.kmp.util.core.json.JsonAnySetter
import kotlinx.kmp.util.core.json.JsonCreator
import kotlinx.kmp.util.core.json.JsonCreatorMode
import kotlinx.kmp.util.core.json.JsonProperty
import kotlinx.kmp.util.core.contentHash

class BetaBase64ImageSource
@JsonCreator(mode = JsonCreatorMode.DISABLED)
private constructor(
    private val data: JsonField<String>,
    private val mediaType: JsonField<MediaType>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("data") @ExcludeMissing data: JsonField<String> = JsonMissing.of(),
        @JsonProperty("media_type")
        @ExcludeMissing
        mediaType: JsonField<MediaType> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(data, mediaType, type, mutableMapOf())

    /**
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun data(): String = data.getRequired("data")

    /**
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mediaType(): MediaType = mediaType.getRequired("media_type")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("base64")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

    /**
     * Returns the raw JSON value of [mediaType].
     *
     * Unlike [mediaType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("media_type") @ExcludeMissing fun _mediaType(): JsonField<MediaType> = mediaType

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        additionalProperties.toMap()

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaBase64ImageSource].
         *
         * The following fields are required:
         * ```java
         * .data()
         * .mediaType()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBase64ImageSource]. */
    class Builder internal constructor() {

        private var data: JsonField<String>? = null
        private var mediaType: JsonField<MediaType>? = null
        private var type: JsonValue = JsonValue.from("base64")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(betaBase64ImageSource: BetaBase64ImageSource) = apply {
            data = betaBase64ImageSource.data
            mediaType = betaBase64ImageSource.mediaType
            type = betaBase64ImageSource.type
            additionalProperties = betaBase64ImageSource.additionalProperties.toMutableMap()
        }

        fun data(data: String) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun data(data: JsonField<String>) = apply { this.data = data }

        fun mediaType(mediaType: MediaType) = mediaType(JsonField.of(mediaType))

        /**
         * Sets [Builder.mediaType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mediaType] with a well-typed [MediaType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mediaType(mediaType: JsonField<MediaType>) = apply { this.mediaType = mediaType }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("base64")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaBase64ImageSource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .data()
         * .mediaType()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaBase64ImageSource =
            BetaBase64ImageSource(
                checkRequired("data", data),
                checkRequired("mediaType", mediaType),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaBase64ImageSource = apply {
        if (validated) {
            return@apply
        }

        data()
        mediaType().validate()
        _type().let {
            if (it != JsonValue.from("base64")) {
                throw ApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: ApiInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    internal fun validity(): Int =
        (if (data.asKnown() != null) 1 else 0) +
            (mediaType.asKnown()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("base64")) 1 else 0 }

    class MediaType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            val IMAGE_JPEG = of("image/jpeg")

            val IMAGE_PNG = of("image/png")

            val IMAGE_GIF = of("image/gif")

            val IMAGE_WEBP = of("image/webp")

            @JvmStatic fun of(value: String) = MediaType(JsonField.of(value))
        }

        /** An enum containing [MediaType]'s known values. */
        enum class Known {
            IMAGE_JPEG,
            IMAGE_PNG,
            IMAGE_GIF,
            IMAGE_WEBP,
        }

        /**
         * An enum containing [MediaType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [MediaType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            IMAGE_JPEG,
            IMAGE_PNG,
            IMAGE_GIF,
            IMAGE_WEBP,
            /**
             * An enum member indicating that [MediaType] was instantiated with an unknown value.
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
                IMAGE_JPEG -> Value.IMAGE_JPEG
                IMAGE_PNG -> Value.IMAGE_PNG
                IMAGE_GIF -> Value.IMAGE_GIF
                IMAGE_WEBP -> Value.IMAGE_WEBP
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws ApiInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                IMAGE_JPEG -> Known.IMAGE_JPEG
                IMAGE_PNG -> Known.IMAGE_PNG
                IMAGE_GIF -> Known.IMAGE_GIF
                IMAGE_WEBP -> Known.IMAGE_WEBP
                else -> throw ApiInvalidDataException("Unknown MediaType: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws ApiInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString() ?: throw ApiInvalidDataException("Value is not a String")

        private var validated: Boolean = false

        fun validate(): MediaType = apply {
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
            } catch (e: ApiInvalidDataException) {
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

            return other is MediaType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBase64ImageSource &&
            data == other.data &&
            mediaType == other.mediaType &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { contentHash(data, mediaType, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBase64ImageSource{data=$data, mediaType=$mediaType, type=$type, additionalProperties=$additionalProperties}"
}
