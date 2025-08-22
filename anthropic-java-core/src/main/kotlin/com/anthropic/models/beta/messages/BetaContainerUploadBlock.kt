// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects

/** Response model for a file uploaded to the container. */
class BetaContainerUploadBlock
private constructor(
    private val fileId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("file_id") @ExcludeMissing fileId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(fileId, type, mutableMapOf())

    fun toParam(): BetaContainerUploadBlockParam =
        BetaContainerUploadBlockParam.builder().fileId(_fileId()).build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun fileId(): String = fileId.getRequired("file_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("container_upload")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [fileId].
     *
     * Unlike [fileId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_id") @ExcludeMissing fun _fileId(): JsonField<String> = fileId

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
         * Returns a mutable builder for constructing an instance of [BetaContainerUploadBlock].
         *
         * The following fields are required:
         * ```java
         * .fileId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaContainerUploadBlock]. */
    class Builder internal constructor() {

        private var fileId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("container_upload")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaContainerUploadBlock: BetaContainerUploadBlock) = apply {
            fileId = betaContainerUploadBlock.fileId
            type = betaContainerUploadBlock.type
            additionalProperties = betaContainerUploadBlock.additionalProperties.toMutableMap()
        }

        fun fileId(fileId: String) = fileId(JsonField.of(fileId))

        /**
         * Sets [Builder.fileId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun fileId(fileId: JsonField<String>) = apply { this.fileId = fileId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("container_upload")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaContainerUploadBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .fileId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaContainerUploadBlock =
            BetaContainerUploadBlock(
                checkRequired("fileId", fileId),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaContainerUploadBlock = apply {
        if (validated) {
            return@apply
        }

        fileId()
        _type().let {
            if (it != JsonValue.from("container_upload")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (fileId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("container_upload")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContainerUploadBlock &&
            fileId == other.fileId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(fileId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaContainerUploadBlock{fileId=$fileId, type=$type, additionalProperties=$additionalProperties}"
}
