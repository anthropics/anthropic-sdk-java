// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
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

/**
 * Configures the transformations the server applies to this image before the model observes it.
 * Each key names a condition the server transforms images for; its value selects the transformation
 * applied. Omitted keys keep their default behavior, and an empty object is equivalent to omitting
 * the field.
 */
class BetaImageTransformationsParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val oversizedImage: JsonField<OversizedImage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("oversized_image")
        @ExcludeMissing
        oversizedImage: JsonField<OversizedImage> = JsonMissing.of()
    ) : this(oversizedImage, mutableMapOf())

    /**
     * What the server does when this image exceeds the model's maximum image size. `"downsize"`
     * (the default) scales the image down to fit, which changes the dimensions the model observes
     * without telling you. `"error"` instead rejects the request with a 400 error naming the
     * image's dimensions and the largest dimensions that fit, so you can scale the image
     * deliberately — your image is never silently scaled down.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun oversizedImage(): Optional<OversizedImage> = oversizedImage.getOptional("oversized_image")

    /**
     * Returns the raw JSON value of [oversizedImage].
     *
     * Unlike [oversizedImage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("oversized_image")
    @ExcludeMissing
    fun _oversizedImage(): JsonField<OversizedImage> = oversizedImage

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
         * Returns a mutable builder for constructing an instance of
         * [BetaImageTransformationsParam].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaImageTransformationsParam]. */
    class Builder internal constructor() {

        private var oversizedImage: JsonField<OversizedImage> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaImageTransformationsParam: BetaImageTransformationsParam) = apply {
            oversizedImage = betaImageTransformationsParam.oversizedImage
            additionalProperties = betaImageTransformationsParam.additionalProperties.toMutableMap()
        }

        /**
         * What the server does when this image exceeds the model's maximum image size. `"downsize"`
         * (the default) scales the image down to fit, which changes the dimensions the model
         * observes without telling you. `"error"` instead rejects the request with a 400 error
         * naming the image's dimensions and the largest dimensions that fit, so you can scale the
         * image deliberately — your image is never silently scaled down.
         */
        fun oversizedImage(oversizedImage: OversizedImage) =
            oversizedImage(JsonField.of(oversizedImage))

        /**
         * Sets [Builder.oversizedImage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oversizedImage] with a well-typed [OversizedImage] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun oversizedImage(oversizedImage: JsonField<OversizedImage>) = apply {
            this.oversizedImage = oversizedImage
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
         * Returns an immutable instance of [BetaImageTransformationsParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaImageTransformationsParam =
            BetaImageTransformationsParam(oversizedImage, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaImageTransformationsParam = apply {
        if (validated) {
            return@apply
        }

        oversizedImage().ifPresent { it.validate() }
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
    internal fun validity(): Int = (oversizedImage.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * What the server does when this image exceeds the model's maximum image size. `"downsize"`
     * (the default) scales the image down to fit, which changes the dimensions the model observes
     * without telling you. `"error"` instead rejects the request with a 400 error naming the
     * image's dimensions and the largest dimensions that fit, so you can scale the image
     * deliberately — your image is never silently scaled down.
     */
    class OversizedImage @JsonCreator private constructor(private val value: JsonField<String>) :
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

            @JvmField val DOWNSIZE = of("downsize")

            @JvmField val ERROR = of("error")

            @JvmStatic fun of(value: String) = OversizedImage(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): OversizedImage =
                value.asString().getOrNull()?.let { of(it) } ?: OversizedImage(value)
        }

        /** An enum containing [OversizedImage]'s known values. */
        enum class Known {
            DOWNSIZE,
            ERROR,
        }

        /**
         * An enum containing [OversizedImage]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [OversizedImage] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            DOWNSIZE,
            ERROR,
            /**
             * An enum member indicating that [OversizedImage] was instantiated with an unknown
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
                DOWNSIZE -> Value.DOWNSIZE
                ERROR -> Value.ERROR
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
                DOWNSIZE -> Known.DOWNSIZE
                ERROR -> Known.ERROR
                else -> throw AnthropicInvalidDataException("Unknown OversizedImage: $value")
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
        fun validate(): OversizedImage = apply {
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

            return other is OversizedImage && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaImageTransformationsParam &&
            oversizedImage == other.oversizedImage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(oversizedImage, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaImageTransformationsParam{oversizedImage=$oversizedImage, additionalProperties=$additionalProperties}"
}
