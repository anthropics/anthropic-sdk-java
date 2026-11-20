package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * The longest side of one or more images in the request exceeds the image size limit. Scale each
 * image in `paths` so that neither side exceeds `max_side_length_pixels`, or remove it, then retry.
 */
class ImageDimensionsTooLargeErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val maxSideLengthPixels: JsonField<Long>,
    private val paths: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("max_side_length_pixels")
        @ExcludeMissing
        maxSideLengthPixels: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("paths") @ExcludeMissing paths: JsonField<List<String>> = JsonMissing.of(),
    ) : this(errorCode, maxSideLengthPixels, paths, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("image_dimensions_too_large")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * The limit the images exceeded: the largest width or height, in pixels, that an image on this
     * request may have.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun maxSideLengthPixels(): Long = maxSideLengthPixels.getRequired("max_side_length_pixels")

    /**
     * The position of every image over the limit, in the order you sent them, nested blocks
     * included, such as `messages.0.content.1`, or `messages.0.content.1.content.0` for an image in
     * a `tool_result` block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun paths(): List<String> = paths.getRequired("paths")

    /**
     * Returns the raw JSON value of [maxSideLengthPixels].
     *
     * Unlike [maxSideLengthPixels], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("max_side_length_pixels")
    @ExcludeMissing
    fun _maxSideLengthPixels(): JsonField<Long> = maxSideLengthPixels

    /**
     * Returns the raw JSON value of [paths].
     *
     * Unlike [paths], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("paths") @ExcludeMissing fun _paths(): JsonField<List<String>> = paths

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
         * [ImageDimensionsTooLargeErrorDetails].
         *
         * The following fields are required:
         * ```java
         * .maxSideLengthPixels()
         * .paths()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ImageDimensionsTooLargeErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("image_dimensions_too_large")
        private var maxSideLengthPixels: JsonField<Long>? = null
        private var paths: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            imageDimensionsTooLargeErrorDetails: ImageDimensionsTooLargeErrorDetails
        ) = apply {
            errorCode = imageDimensionsTooLargeErrorDetails.errorCode
            maxSideLengthPixels = imageDimensionsTooLargeErrorDetails.maxSideLengthPixels
            paths =
                imageDimensionsTooLargeErrorDetails.paths
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties =
                imageDimensionsTooLargeErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("image_dimensions_too_large")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        /**
         * The limit the images exceeded: the largest width or height, in pixels, that an image on
         * this request may have.
         */
        fun maxSideLengthPixels(maxSideLengthPixels: Long) =
            maxSideLengthPixels(JsonField.of(maxSideLengthPixels))

        /**
         * Sets [Builder.maxSideLengthPixels] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxSideLengthPixels] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun maxSideLengthPixels(maxSideLengthPixels: JsonField<Long>) = apply {
            this.maxSideLengthPixels = maxSideLengthPixels
        }

        /**
         * The position of every image over the limit, in the order you sent them, nested blocks
         * included, such as `messages.0.content.1`, or `messages.0.content.1.content.0` for an
         * image in a `tool_result` block.
         */
        fun paths(paths: List<String>) = paths(JsonField.of(paths))

        /**
         * Sets [Builder.paths] to an arbitrary JSON value.
         *
         * You should usually call [Builder.paths] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun paths(paths: JsonField<List<String>>) = apply {
            this.paths = paths.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [paths].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPath(path: String) = apply {
            paths =
                (paths ?: JsonField.of(mutableListOf())).also { checkKnown("paths", it).add(path) }
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
         * Returns an immutable instance of [ImageDimensionsTooLargeErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .maxSideLengthPixels()
         * .paths()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ImageDimensionsTooLargeErrorDetails =
            ImageDimensionsTooLargeErrorDetails(
                errorCode,
                checkRequired("maxSideLengthPixels", maxSideLengthPixels),
                checkRequired("paths", paths).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): ImageDimensionsTooLargeErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("image_dimensions_too_large")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        maxSideLengthPixels()
        paths()
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
        errorCode.let { if (it == JsonValue.from("image_dimensions_too_large")) 1 else 0 } +
            (if (maxSideLengthPixels.asKnown().isPresent) 1 else 0) +
            (paths.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ImageDimensionsTooLargeErrorDetails &&
            errorCode == other.errorCode &&
            maxSideLengthPixels == other.maxSideLengthPixels &&
            paths == other.paths &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(errorCode, maxSideLengthPixels, paths, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ImageDimensionsTooLargeErrorDetails{errorCode=$errorCode, maxSideLengthPixels=$maxSideLengthPixels, paths=$paths, additionalProperties=$additionalProperties}"
}
