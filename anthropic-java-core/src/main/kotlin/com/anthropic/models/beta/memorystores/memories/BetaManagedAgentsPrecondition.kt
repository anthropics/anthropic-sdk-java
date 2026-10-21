package com.anthropic.models.beta.memorystores.memories

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

/**
 * Optional condition that must hold for an update to apply. When omitted, the update is
 * unconditional. Asserts the current state of the memory being updated. When an update changes
 * `path`, the precondition still refers to the memory's current content, not the destination path.
 * Currently the only supported variant is `content_sha256`.
 */
class BetaManagedAgentsPrecondition
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val contentSha256: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("content_sha256")
        @ExcludeMissing
        contentSha256: JsonField<String> = JsonMissing.of(),
    ) : this(type, contentSha256, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("content_sha256")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Expected `content_sha256` of the stored memory (64 lowercase hexadecimal characters).
     * Typically the `content_sha256` returned by a prior read or list call. Because the server
     * applies no content normalization, clients can also compute this locally as the SHA-256 of the
     * UTF-8 content bytes.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contentSha256(): Optional<String> = contentSha256.getOptional("content_sha256")

    /**
     * Returns the raw JSON value of [contentSha256].
     *
     * Unlike [contentSha256], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content_sha256")
    @ExcludeMissing
    fun _contentSha256(): JsonField<String> = contentSha256

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
         * [BetaManagedAgentsPrecondition].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsPrecondition]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("content_sha256")
        private var contentSha256: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsPrecondition: BetaManagedAgentsPrecondition) = apply {
            type = betaManagedAgentsPrecondition.type
            contentSha256 = betaManagedAgentsPrecondition.contentSha256
            additionalProperties = betaManagedAgentsPrecondition.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("content_sha256")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Expected `content_sha256` of the stored memory (64 lowercase hexadecimal characters).
         * Typically the `content_sha256` returned by a prior read or list call. Because the server
         * applies no content normalization, clients can also compute this locally as the SHA-256 of
         * the UTF-8 content bytes.
         */
        fun contentSha256(contentSha256: String) = contentSha256(JsonField.of(contentSha256))

        /**
         * Sets [Builder.contentSha256] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentSha256] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun contentSha256(contentSha256: JsonField<String>) = apply {
            this.contentSha256 = contentSha256
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
         * Returns an immutable instance of [BetaManagedAgentsPrecondition].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsPrecondition =
            BetaManagedAgentsPrecondition(type, contentSha256, additionalProperties.toMutableMap())
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
    fun validate(): BetaManagedAgentsPrecondition = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("content_sha256")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        contentSha256()
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
        type.let { if (it == JsonValue.from("content_sha256")) 1 else 0 } +
            (if (contentSha256.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsPrecondition &&
            type == other.type &&
            contentSha256 == other.contentSha256 &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, contentSha256, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsPrecondition{type=$type, contentSha256=$contentSha256, additionalProperties=$additionalProperties}"
}
