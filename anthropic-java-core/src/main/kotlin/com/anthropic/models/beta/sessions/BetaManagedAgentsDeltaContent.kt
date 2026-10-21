package com.anthropic.models.beta.sessions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaManagedAgentsDeltaContent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<BetaManagedAgentsTextBlock>,
    private val type: JsonValue,
    private val index: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<BetaManagedAgentsTextBlock> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("index") @ExcludeMissing index: JsonField<Long> = JsonMissing.of(),
    ) : this(content, type, index, mutableMapOf())

    /**
     * A partial element of the content array at index, typed like the element itself — the same
     * shape the buffered agent.message carries in content.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): BetaManagedAgentsTextBlock = content.getRequired("content")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("content_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Which entry in the previewed event's content array this fragment lands in. Insert content as
     * that entry when the index is new; append to the existing entry otherwise.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun index(): Optional<Long> = index.getOptional("index")

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content")
    @ExcludeMissing
    fun _content(): JsonField<BetaManagedAgentsTextBlock> = content

    /**
     * Returns the raw JSON value of [index].
     *
     * Unlike [index], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("index") @ExcludeMissing fun _index(): JsonField<Long> = index

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
         * [BetaManagedAgentsDeltaContent].
         *
         * The following fields are required:
         * ```java
         * .content()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsDeltaContent] with the required
         * [content] set to the given value.
         */
        @JvmStatic fun of(content: BetaManagedAgentsTextBlock) = builder().content(content).build()
    }

    /** A builder for [BetaManagedAgentsDeltaContent]. */
    class Builder internal constructor() {

        private var content: JsonField<BetaManagedAgentsTextBlock>? = null
        private var type: JsonValue = JsonValue.from("content_delta")
        private var index: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsDeltaContent: BetaManagedAgentsDeltaContent) = apply {
            content = betaManagedAgentsDeltaContent.content
            type = betaManagedAgentsDeltaContent.type
            index = betaManagedAgentsDeltaContent.index
            additionalProperties = betaManagedAgentsDeltaContent.additionalProperties.toMutableMap()
        }

        /**
         * A partial element of the content array at index, typed like the element itself — the same
         * shape the buffered agent.message carries in content.
         */
        fun content(content: BetaManagedAgentsTextBlock) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [BetaManagedAgentsTextBlock]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun content(content: JsonField<BetaManagedAgentsTextBlock>) = apply {
            this.content = content
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("content_delta")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Which entry in the previewed event's content array this fragment lands in. Insert content
         * as that entry when the index is new; append to the existing entry otherwise.
         */
        fun index(index: Long) = index(JsonField.of(index))

        /**
         * Sets [Builder.index] to an arbitrary JSON value.
         *
         * You should usually call [Builder.index] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun index(index: JsonField<Long>) = apply { this.index = index }

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
         * Returns an immutable instance of [BetaManagedAgentsDeltaContent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsDeltaContent =
            BetaManagedAgentsDeltaContent(
                checkRequired("content", content),
                type,
                index,
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
    fun validate(): BetaManagedAgentsDeltaContent = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        _type().let {
            if (it != JsonValue.from("content_delta")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        index()
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
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("content_delta")) 1 else 0 } +
            (if (index.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsDeltaContent &&
            content == other.content &&
            type == other.type &&
            index == other.index &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(content, type, index, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsDeltaContent{content=$content, type=$type, index=$index, additionalProperties=$additionalProperties}"
}
